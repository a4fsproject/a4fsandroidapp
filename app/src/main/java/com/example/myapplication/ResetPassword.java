package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ResetPassword extends AppCompatActivity implements DataInterface {

    TextView forgot_password;
    TextView sub_forgot_password;
    EditText edt_new_password;
    EditText edt_confirm_new_password;
    Button btn_reset;

    Webservice_Volley volley;

    String enroll_no,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        enroll_no= getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        forgot_password=(TextView) findViewById(R.id.forgot_password);
        sub_forgot_password=(TextView) findViewById(R.id.sub_forgot_password);
        edt_new_password=(EditText) findViewById(R.id.edt_new_password);
        edt_confirm_new_password=(EditText) findViewById(R.id.edt_confirm_new_password);
        btn_reset=(Button)findViewById(R.id.btn_reset);

        volley = new Webservice_Volley(this,this);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Commonfunction.checkPassword(edt_new_password.getText().toString())){
                    edt_new_password.setError("please enter your new valid password");
                    return;
                }

                if(!Commonfunction.checkPassword(edt_confirm_new_password.getText().toString())){
                    edt_confirm_new_password.setError("please enter correct new password to confirm");
                    return;
                }

                if (!edt_new_password.getText().toString().equals(edt_confirm_new_password.getText().toString())) {
                    edt_confirm_new_password.setError("Both password not match.");
                    return;
                }

                if (type.equalsIgnoreCase("student")) {

                    String url = Constants.Webserive_Url + "student_reset.php";
                    HashMap<String, String> params = new HashMap<>();
                    params.put("s_password", edt_new_password.getText().toString());
                    params.put("enroll_number", enroll_no);
                    volley.CallVolley(url, params, "student_reset");
                }
                else {

                    String url = Constants.Webserive_Url + "user_reset.php";
                    HashMap<String, String> params = new HashMap<>();
                    params.put("password", edt_new_password.getText().toString());
                    params.put("u_id", enroll_no);

                    volley.CallVolley(url, params, "user_reset");
                }
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                if (type.equalsIgnoreCase("student")) {

                    Intent i = new Intent(ResetPassword.this, Student_Login.class);
                    startActivity(i);

                    finishAffinity();
                }
                else {
                    Intent i = new Intent(ResetPassword.this, User_login.class);
                    startActivity(i);

                    finishAffinity();
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
