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

public class ForgotPassword extends AppCompatActivity implements DataInterface {

    TextView forgot_password;
    TextView sub_forgot_password;
    EditText edt_mobile_number;
    Button btn_submit;

    Webservice_Volley volley;

    String type="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forgot_password=(TextView) findViewById(R.id.forgot_password);
        sub_forgot_password=(TextView) findViewById(R.id.sub_forgot_password);
        edt_mobile_number=(EditText) findViewById(R.id.edt_mobile_number);
        btn_submit=(Button)findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this,this);

        type = getIntent().getStringExtra("type");

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!Commonfunction.checkMobileNo(edt_mobile_number.getText().toString())){
                    edt_mobile_number.setError("please enter 10 digit mobile number");
                    return;
                }

                if (type.equalsIgnoreCase("student")) {
                    String url = Constants.Webserive_Url + "student_forgot.php";
                    HashMap<String, String> params = new HashMap<>();
                    params.put("s_mob_number", edt_mobile_number.getText().toString());

                    volley.CallVolley(url, params, "student_forgot");
                }
                else {


                    String url = Constants.Webserive_Url + "user_forgot.php";
                    HashMap<String, String> params = new HashMap<>();
                    params.put("u_mob_number", edt_mobile_number.getText().toString());

                    volley.CallVolley(url, params, "user_forgot");
                }
            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                String id = jsonObject.getString("id");
                String code = jsonObject.getString("verificationcode");

                Log.d("##MY_CODE","==> " + code);

                Intent i = new Intent(ForgotPassword.this,Verification.class);
                i.putExtra("id",id);
                i.putExtra("code",code);
                i.putExtra("type",type);
                startActivity(i);

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
