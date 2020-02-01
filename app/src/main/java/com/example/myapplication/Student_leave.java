package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Student_leave extends AppCompatActivity implements DataInterface {
    EditText edt_from_date;
    EditText edt_to_date;
    TextView txt_select_image;
    ImageView img_leave;
    TextView txt_upload_photo;
    EditText edt_reason;
    Button btn_submit;

    Calendar calfrom,calto;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_leave);
        edt_from_date = (EditText) findViewById(R.id.edt_from_date);
        edt_to_date = (EditText) findViewById(R.id.edt_to_date);
        txt_select_image = (TextView) findViewById(R.id.txt_select_image);
        img_leave = (ImageView) findViewById(R.id.img_leave);
        txt_upload_photo = (TextView) findViewById(R.id.txt_upload_photo);
        edt_reason = (EditText) findViewById(R.id.edt_reason);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this, this);

        calfrom = Calendar.getInstance();
        calto =Calendar.getInstance();

        edt_from_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Student_leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_from_date.setText(year + "-" + (month + 1) + "-" + day);
                        calfrom.set(Calendar.DAY_OF_MONTH,day);
                        calfrom.set(Calendar.YEAR,year);
                        calfrom.set(Calendar.MONTH,month);


                    }
                },calfrom.get(Calendar.YEAR),calfrom.get(Calendar.MONTH),calfrom.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialog.show();

            }
        });

        edt_to_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Student_leave.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_to_date.setText(year + "-" + (month + 1) + "-" + day);
                        calto.set(Calendar.DAY_OF_MONTH,day);
                        calto.set(Calendar.YEAR,year);
                        calto.set(Calendar.MONTH,month);


                    }
                },calto.get(Calendar.YEAR),calto.get(Calendar.MONTH),calto.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMinDate(calfrom.getTimeInMillis());
                datePickerDialog.show();

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkString(edt_from_date.getText().toString())) {
                    edt_from_date.setError("Please Select From Date");
                    return;
                }

                if (!Commonfunction.checkString(edt_to_date.getText().toString())) {
                    edt_to_date.setError("Please Select To Date");
                    return;
                }

                if (!Commonfunction.checkString(edt_reason.getText().toString())) {
                    edt_reason.setError("Please Mention Your Reason");
                    return;
                }

                long msDiff = calfrom.getTimeInMillis() - calto.getTimeInMillis();
                long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);

                String url = Constants.Webserive_Url + "student_leave.php";
                HashMap<String, String> params = new HashMap<>();

                params.put("l_status","pending");
                params.put("u_id","");
                params.put("u_from_date", edt_from_date.getText().toString());
                params.put("u_to_date", edt_to_date.getText().toString());
                params.put("l_reason", edt_reason.getText().toString());
                params.put("s_enroll_number","123456789100");
                params.put("ul_photo","");
                params.put("total_days","" + daysDiff);

                volley.CallVolley(url, params, "student_leave");

            }

        });
    }


    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
