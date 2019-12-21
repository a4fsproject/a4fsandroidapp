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

public class Verification extends AppCompatActivity {

    TextView forgot_password;
    TextView sub_forgot_password;
    EditText edt_num1;
    EditText edt_num2;
    EditText edt_num3;
    EditText edt_num4;
    Button btn_verify;

    String id,verify_code;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        id = getIntent().getStringExtra("id");
        verify_code = getIntent().getStringExtra("code");
        type = getIntent().getStringExtra("type");


        forgot_password=(TextView) findViewById(R.id.forgot_password);
        sub_forgot_password=(TextView) findViewById(R.id.sub_forgot_password);
        edt_num1=(EditText) findViewById(R.id.edt_num1);
        edt_num2=(EditText) findViewById(R.id.edt_num2);
        edt_num3=(EditText) findViewById(R.id.edt_num3);
        edt_num4=(EditText) findViewById(R.id.edt_num4);
        btn_verify=(Button)findViewById(R.id.btn_verify);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = edt_num1.getText().toString() + edt_num2.getText().toString() + edt_num3.getText().toString() + edt_num4.getText().toString();

                if(!Commonfunction.checkVerification(code)){
                    Toast.makeText(Verification.this, "Enter 4 digit verification code", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (code.equals(verify_code)) {
                    Intent i=new Intent(Verification.this, ResetPassword.class);
                    i.putExtra("id",id);
                    i.putExtra("type",type);
                    startActivity(i);
                }
                else {
                    Toast.makeText(Verification.this, "Invalid verification code.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
