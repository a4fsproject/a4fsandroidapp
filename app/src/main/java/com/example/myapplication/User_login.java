package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class User_login extends AppCompatActivity implements DataInterface {
    EditText edt_mobile_number;
    EditText edt_password;
    Button btn_login;
    Button signup_button;
    TextView privacy_policy,forgot_password;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        edt_mobile_number=(EditText) findViewById(R.id.edt_mobile_number);
        edt_password=(EditText) findViewById(R.id.edt_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        signup_button=(Button)findViewById(R.id.signup_button);
        privacy_policy=(TextView) findViewById(R.id.privacy_policy);
        forgot_password=(TextView) findViewById(R.id.forgot_password);

        volley = new Webservice_Volley(this,this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(User_login.this, User_homepage.class);
                startActivity(i);

                if (!Commonfunction.checkMobileNo(edt_mobile_number.getText().toString())) {
                    edt_mobile_number.setError("Please Enter Valid mobile No.");
                    return;
                }

                if (!Commonfunction.checkPassword(edt_password.getText().toString())) {
                    edt_password.setError("Please Enter Valid password");
                    return;
                }


                String url = Constants.Webserive_Url+"user_login.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("u_mob_number",edt_mobile_number.getText().toString());
                params.put("password",edt_password.getText().toString());


                volley.CallVolley(url,params,"user_login");

            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_login.this, User_signup.class);
                startActivity(i);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_login.this, ForgotPassword.class);
                i.putExtra("type","user");
                startActivity(i);
            }
        });

        privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

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
