package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.MyListAdapter;
import com.example.myapplication.model.SubjectInfoVo;
import com.example.myapplication.model.SubjectResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Generate_QR extends AppCompatActivity implements DataInterface {

    Spinner spbranch;
    Spinner spsemester;
    EditText edt_sub_code;
    TextView sub_name;
    Button btn_generate_qr_code;
    ImageView img_qr;

    SubjectResultVo mainSubjectResultVo = null;

    Webservice_Volley volley;

    ArrayList<String> branchList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();

    String U_id = "1";

    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate__qr);

        spbranch=(Spinner) findViewById(R.id.spbranch);
        spsemester=(Spinner) findViewById(R.id.spsemester);
        edt_sub_code=(EditText)findViewById(R.id.edt_sub_code);
        sub_name=(TextView)findViewById(R.id.sub_name);
        btn_generate_qr_code=(Button)findViewById(R.id.btn_generate_qr_code);
        img_qr=(ImageView) findViewById(R.id.img_qr);

        volley = new Webservice_Volley(this,this);

        branchList.add("Select Branch");
        branchList.add("Computer Engg.");
        branchList.add("Mechanical Engg.");

        semList.add("Select Semester");
        semList.add("1");
        semList.add("2");
        semList.add("3");
        semList.add("4");
        semList.add("5");
        semList.add("6");
        semList.add("7");
        semList.add("8");

        ArrayAdapter<String> da = new ArrayAdapter<>(Generate_QR.this,android.R.layout.simple_spinner_dropdown_item,branchList);
        spbranch.setAdapter(da);

        ArrayAdapter<String> da1 = new ArrayAdapter<>(Generate_QR.this,android.R.layout.simple_spinner_dropdown_item,semList);
        spsemester.setAdapter(da1);

        spbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String url = Constants.Webserive_Url+"get_studentlist_branch_sem.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                volley.CallVolley(url,params,"get_studentlist_branch_sem");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spsemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String url = Constants.Webserive_Url+"get_studentlist_branch_sem.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                volley.CallVolley(url,params,"get_studentlist_branch_sem");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edt_sub_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    String url = Constants.Webserive_Url+"get_subject_details.php";
                    HashMap<String,String> params = new HashMap<>();
                    params.put("sub_code",edt_sub_code.getText().toString());
                    volley.CallVolley(url,params,"get_subject_details");

                    return true;
                }

                return false;
            }
        });

        btn_generate_qr_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spbranch.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select Branch", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select Semester", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (mainSubjectResultVo == null) {
                    Snackbar.make(view, "Please Select Subject For Code.", Snackbar.LENGTH_LONG).show();
                    return;
                }



                try {

                    String QR_data = U_id + "~~" + spbranch.getSelectedItem().toString() + "~~" + spsemester.getSelectedItem().toString() + "~~" + mainSubjectResultVo.getSubCode()+ "~~" + mainSubjectResultVo.getSubName();

                    TextToImageEncode(QR_data);


                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try {

            if (tag.equalsIgnoreCase("get_subject_details")) {

                SubjectInfoVo subjectInfoVo = new Gson().fromJson(jsonObject.toString(), SubjectInfoVo.class);
                if (subjectInfoVo != null) {
                    if (subjectInfoVo.getResult() != null) {
                        if (subjectInfoVo.getResult().size() > 0) {

                            mainSubjectResultVo = subjectInfoVo.getResult().get(0);

                            sub_name.setText(mainSubjectResultVo.getSubName());

                        }
                        else {
                            Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                            sub_name.setText("");
                            mainSubjectResultVo = null;
                        }
                    }
                    else {
                        Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                        sub_name.setText("");
                        mainSubjectResultVo = null;
                    }
                }
                else {
                    Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                    sub_name.setText("");
                    mainSubjectResultVo = null;
                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    300, 300, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        Color.BLACK :Color.WHITE;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 300, 0, 0, bitMatrixWidth, bitMatrixHeight);
        img_qr.setImageBitmap(bitmap);
        return bitmap;
    }
}
