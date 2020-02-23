package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public abstract class upload_assignment extends AppCompatActivity implements DataInterface {

    EditText edt_sub_code;
    TextView sub_name;
    EditText edt_issue_date;
    EditText edt_last_date;
    ImageView img_upload_assignment;
    EditText edt_details_of_assignment;
    Button btn_upload;

    Calendar calfrom,calto;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_assignment);

        edt_sub_code = (EditText) findViewById(R.id.edt_sub_code);
        sub_name = (TextView) findViewById(R.id.sub_name);
        edt_issue_date = (EditText) findViewById(R.id.edt_issue_date);
        edt_last_date = (EditText) findViewById(R.id.edt_last_date);
        img_upload_assignment= (ImageView) findViewById(R.id.img_uplaod_assignment);
        edt_details_of_assignment = (EditText) findViewById(R.id.edt_details_of_assignment);
        btn_upload = (Button)findViewById(R.id.btn_upload);


        volley = new Webservice_Volley(this, this);

        calfrom = Calendar.getInstance();
        calto =Calendar.getInstance();

        edt_issue_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(upload_assignment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_issue_date.setText(year + "-" + (day+ 1) + "-" + day);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(upload_assignment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_last_date.setText(year + "-" + (day + 1) + "-" + day);
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

                long msDiff = calfrom.getTimeInMillis() - calto.getTimeInMillis();
                long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

                String url = Constants.Webserive_Url + "assignment.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("s_enroll_number","");
                params.put("issue_date", edt_issue_date.getText().toString());
                params.put("last_date", edt_last_date.getText().toString());
                params.put("assign_file", "");
                params.put("sub_code",edt_sub_code .getText().toString());
                params.put("Sub_name",sub_name.getText().toString());
                params.put("assign_details",edt_details_of_assignment.getText().toString());
                params.put("s_branch","");
                params.put("s_semester","");

                volley.CallVolley(url, params, "assignment");

            }

        });
    }


    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {
            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
