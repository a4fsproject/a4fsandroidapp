package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.filepicker.controller.DialogSelectionListener;
import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.model.SubjectInfoVo;
import com.example.myapplication.model.SubjectResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class Upload_assignment extends AppCompatActivity implements DataInterface {

    Spinner spbranch,spsemester;
    EditText edt_sub_code;
    TextView sub_name,txt_file_name,txt_upload_file;
    EditText edt_issue_date;
    EditText edt_last_date;
    ImageView img_upload_assignment;
    EditText edt_details_of_assignment;
    Button btn_upload;

    Calendar calfrom,calto;

    File selectedFile = null;

    Webservice_Volley volley;

    ArrayList<String> branchList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();

    SubjectResultVo mainSubjectResultVo = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignment);

        spbranch = (Spinner) findViewById(R.id.spbranch);
        spsemester = (Spinner) findViewById(R.id.spsemester);
        edt_sub_code = (EditText) findViewById(R.id.edt_sub_code);
        sub_name = (TextView) findViewById(R.id.sub_name);
        txt_upload_file = (TextView) findViewById(R.id.txt_upload_file);
        txt_file_name = (TextView) findViewById(R.id.txt_file_name);
        edt_issue_date = (EditText) findViewById(R.id.edt_issue_date);
        edt_last_date = (EditText) findViewById(R.id.edt_last_date);
        img_upload_assignment= (ImageView) findViewById(R.id.img_uplaod_assignment);
        edt_details_of_assignment = (EditText) findViewById(R.id.edt_details_of_assignment);
        btn_upload = (Button)findViewById(R.id.btn_upload);


        volley = new Webservice_Volley(this, this);

        calfrom = Calendar.getInstance();
        calto =Calendar.getInstance();

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

        ArrayAdapter<String> da = new ArrayAdapter<String>(Upload_assignment.this,android.R.layout.simple_spinner_dropdown_item,branchList);
        spbranch.setAdapter(da);

        ArrayAdapter<String> da1 = new ArrayAdapter<String>(Upload_assignment.this,android.R.layout.simple_spinner_dropdown_item,semList);
        spsemester.setAdapter(da1);


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



        txt_upload_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogProperties properties = new DialogProperties();

                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = new String[]{"pdf"};
                properties.show_hidden_files = false;

                FilePickerDialog dialog = new FilePickerDialog(Upload_assignment.this,properties);
                dialog.setTitle("Select a File");

                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        //files is the array of the paths of files selected by the Application User.

                        if (files.length > 0) {
                            selectedFile = new File(files[0]);
                            txt_file_name.setText((selectedFile == null) ? "No File Selected." : selectedFile.getName());
                        }
                    }
                });

                dialog.show();


            }
        });

        edt_issue_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Upload_assignment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_issue_date.setText(year + "-" + (month+ 1) + "-" + day);
                        calfrom.set(Calendar.DAY_OF_MONTH,day);
                        calfrom.set(Calendar.YEAR,year);
                        calfrom.set(Calendar.MONTH,month);


                    }
                },calfrom.get(Calendar.YEAR),calfrom.get(Calendar.MONTH),calfrom.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();

            }
        });

        edt_last_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Upload_assignment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_last_date.setText(year + "-" + (month + 1) + "-" + day);
                        calto.set(Calendar.DAY_OF_MONTH,day);
                        calto.set(Calendar.YEAR,year);
                        calto.set(Calendar.MONTH,month);


                    }
                },calto.get(Calendar.YEAR),calto.get(Calendar.MONTH),calto.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(calfrom.getTimeInMillis());
                datePickerDialog.show();

            }
        });


        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spbranch.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Select Branch",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Select Semester",Snackbar.LENGTH_LONG).show();
                    return;
                }


                if (!Commonfunction.checkString(edt_issue_date.getText().toString())) {
                    edt_issue_date.setError("Please Select Issue Date");
                    return;
                }

                if (!Commonfunction.checkString(edt_last_date.getText().toString())) {
                    edt_last_date.setError("Please Select Last Date");
                    return;
                }

                if (!Commonfunction.checkString( edt_sub_code.getText().toString())) {
                    edt_sub_code.setError("Please Subject Code");
                    return;
                }

                if (mainSubjectResultVo == null) {
                    Snackbar.make(view,"Please Select Subject For Assignment.",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (selectedFile == null) {
                    Snackbar.make(view,"Please select file for assignment",Snackbar.LENGTH_LONG).show();
                    return;
                }

                String encodeFileToBase64Binary = encodeFileToBase64Binary(selectedFile);


                String url = Constants.Webserive_Url + "assignment.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("issue_date", edt_issue_date.getText().toString());
                params.put("last_date", edt_last_date.getText().toString());
                params.put("assign_file", encodeFileToBase64Binary);
                params.put("sub_code",mainSubjectResultVo.getSubCode());
                params.put("sub_name",mainSubjectResultVo.getSubName());
                params.put("assign_details",edt_details_of_assignment.getText().toString());
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());

                volley.CallVolley(url, params, "assignment");

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
            else {

                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private String encodeFileToBase64Binary(File yourFile) {
        int size = (int) yourFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(yourFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String encoded = Base64.encodeToString(bytes,Base64.NO_WRAP);
        return encoded;
    }
}
