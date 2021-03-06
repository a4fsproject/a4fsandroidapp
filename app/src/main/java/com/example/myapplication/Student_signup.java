package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Student_signup extends AppCompatActivity implements DataInterface {

    EditText edt_enroll_number;
    EditText edt_student_name;
    EditText edt_mobile_number;
    EditText edt_password;
    EditText edt_address;
    EditText edt_DOB;
    EditText edt_email;
    /*EditText edt_branch;
    EditText edt_semester;*/

    Spinner spsemester,spbranch;

    CheckBox terms_conditions;
    TextView Already_Account;
    TextView privacy_policy;

    Button btn_signup;

    Webservice_Volley volley;

    Calendar calendar ;
    int mY,mD,mM;

    ArrayList<String> branchLIst = new ArrayList<>();
    ArrayList<String> SemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);

        branchLIst.add("Select Your Branch");
        branchLIst.add("Computer Engineering");
        branchLIst.add("Mechanical Engineering");
        branchLIst.add("Automobile Engineering");
        branchLIst.add("Civil Engineering");
        branchLIst.add("Electrical Engineering");
        branchLIst.add("IT Engineering");

        SemList.add("Select Your Semester");
        SemList.add("1");
        SemList.add("2");
        SemList.add("3");
        SemList.add("4");
        SemList.add("5");
        SemList.add("6");
        SemList.add("7");
        SemList.add("8");

        edt_enroll_number=(EditText) findViewById(R.id.edt_enroll_number);
        edt_student_name=(EditText) findViewById(R.id.edt_student_name);
        edt_mobile_number=(EditText) findViewById(R.id.edt_mobile_number);
        edt_password=(EditText) findViewById(R.id.edt_password);
        edt_address=(EditText) findViewById(R.id.edt_address);
        edt_DOB=(EditText) findViewById(R.id.edt_DOB);
        edt_email=(EditText) findViewById(R.id.edt_email);

        /*edt_semester=(EditText) findViewById(R.id.edt_semester);
        edt_branch=(EditText) findViewById(R.id.edt_branch);*/

        spbranch=(Spinner) findViewById(R.id.spbranch);
        spsemester=(Spinner) findViewById(R.id.spsemester);

        btn_signup=(Button) findViewById(R.id.btn_signup);

        terms_conditions=(CheckBox) findViewById(R.id.terms_conditions);

        Already_Account=(TextView) findViewById(R.id.Already_Account);

        volley = new Webservice_Volley(this,this);

        calendar = Calendar.getInstance();
        mY = calendar.get(Calendar.YEAR);
        mM = calendar.get(Calendar.MONTH);
        mD = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter<String> dab = new ArrayAdapter<>(Student_signup.this,android.R.layout.simple_spinner_dropdown_item,branchLIst);
        spbranch.setAdapter(dab);

        ArrayAdapter<String> das = new ArrayAdapter<>(Student_signup.this,android.R.layout.simple_spinner_dropdown_item,SemList);
        spsemester.setAdapter(das);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkEnrollno(edt_enroll_number.getText().toString())) {
                    edt_enroll_number.setError("Please Enter Valid Enrollment No.");
                    return;
                }

                if (!Commonfunction.checkString(edt_student_name.getText().toString())) {
                    edt_student_name.setError("Please Enter your name");
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
                    edt_address.setError("Please Enter your address");
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

                if (spbranch.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Enter Your Branch",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Enter Your Semester",Snackbar.LENGTH_LONG).show();
                    return;
                }

                String url = Constants.Webserive_Url+"student_registration.php";
                HashMap<String,String> params = new HashMap<>();

                params.put("enroll_number",edt_enroll_number.getText().toString());
                params.put("s_name",edt_student_name.getText().toString());
                params.put("s_mob_number",edt_mobile_number.getText().toString());
                params.put("s_password",edt_password.getText().toString());
                params.put("s_address",edt_address.getText().toString());
                params.put("s_dob",edt_DOB.getText().toString());
                params.put("s_email_id",edt_email.getText().toString());
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                params.put("s_photo","");

                volley.CallVolley(url,params,"student_registration");
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(Student_signup.this, new DatePickerDialog.OnDateSetListener() {
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
