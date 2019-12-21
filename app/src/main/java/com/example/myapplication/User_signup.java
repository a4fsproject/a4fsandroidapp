package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class User_signup extends AppCompatActivity implements DataInterface {

    EditText edt_username;
    EditText edt_mobile_number;
    EditText edt_password;
    EditText edt_address;
    EditText edt_designation;
    EditText edt_DOB;
    EditText edt_email;
    CheckBox terms_conditions;
    TextView Already_Account;
    Button btn_signup;

    Webservice_Volley volley;

    Calendar calendar ;
    int mY,mD,mM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);

        edt_username=(EditText) findViewById(R.id.edt_username);
        edt_mobile_number=(EditText) findViewById(R.id.edt_mobile_number);
        edt_password=(EditText) findViewById(R.id.edt_password);
        edt_address=(EditText) findViewById(R.id.edt_address);
        edt_designation=(EditText) findViewById(R.id.edt_designation);
        edt_DOB=(EditText) findViewById(R.id.edt_DOB);
        edt_email=(EditText) findViewById(R.id.edt_email);
        btn_signup=(Button) findViewById(R.id.btn_signup);

        terms_conditions=(CheckBox) findViewById(R.id.terms_conditions);

        Already_Account=(TextView) findViewById(R.id.Already_Account);

        volley = new Webservice_Volley(this,this);

        calendar = Calendar.getInstance();
        mY = calendar.get(Calendar.YEAR);
        mM = calendar.get(Calendar.MONTH);
        mD = calendar.get(Calendar.DAY_OF_MONTH);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkString(edt_username.getText().toString())) {
                    edt_username.setError("Please Enter Your Name");
                    return;
                }

                if (!Commonfunction.checkMobileNo(edt_mobile_number.getText().toString())) {
                    edt_mobile_number.setError("Please Enter Valid mobile No.");
                    return;
                }

                if (!Commonfunction.checkPassword(edt_password.getText().toString())) {
                    edt_password.setError("Please Enter Valid password");
                    return;
                }

                if (!Commonfunction.checkString(edt_address.getText().toString())) {
                    edt_address.setError("Please Enter Your address");
                    return;
                }

                if (!Commonfunction.checkString(edt_designation.getText().toString())) {
                    edt_designation.setError("Please Enter Your Designation");
                    return;
                }

                if (!Commonfunction.checkString(edt_DOB.getText().toString())) {
                    edt_DOB.setError("Please Enter Valid DOB");
                    return;
                }

                if (!Commonfunction.checkEmail(edt_email.getText().toString())) {
                    edt_email.setError("Please Enter Your Valid Email-id");
                    return;
                }

                String url = Constants.Webserive_Url+"user_registration.php";
                HashMap<String,String> params = new HashMap<>();

                params.put("u_name",edt_username.getText().toString());
                params.put("u_mob_number",edt_mobile_number.getText().toString());
                params.put("password",edt_password.getText().toString());
                params.put("u_address",edt_address.getText().toString());
                params.put("u_designation",edt_designation.getText().toString());
                params.put("u_dob",edt_DOB.getText().toString());
                params.put("u_email_id",edt_email.getText().toString());
                params.put("u_photo","");

                volley.CallVolley(url,params,"user_registration");

            }
        });

        Already_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        edt_DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(User_signup.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        edt_DOB.setText(year + "-" + (month+1) + "-" + day);
                        mY= year;
                        mM = month;
                        mD = day;

                    }
                },mY,mM,mD);

                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();

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