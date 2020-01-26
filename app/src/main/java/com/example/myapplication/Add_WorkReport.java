package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Add_WorkReport extends AppCompatActivity implements DataInterface {

    Spinner spbranch;
    Spinner spsemester;
    EditText edt_sub_code;
    TextView sub_name;
    EditText edt_comments;
    Button btn_upload;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__work_report);
        spbranch=(Spinner) findViewById(R.id.spbranch);
        spsemester=(Spinner) findViewById(R.id.spsemester);
        edt_sub_code=(EditText)findViewById(R.id.edt_sub_code);
        sub_name=(TextView)findViewById(R.id.sub_name);
        edt_comments=(EditText)findViewById(R.id.edt_comments);
        btn_upload=(Button)findViewById(R.id.btn_upload);

        volley = new Webservice_Volley(this,this);

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkEnrollno(edt_sub_code.getText().toString())) {
                    edt_sub_code.setError("Please Enter Valid Subject Code");
                    return;
                }

                if (!Commonfunction.checkPassword(edt_comments.getText().toString())) {
                    edt_comments.setError("Please Enter Proper Details");
                    return;
                }

                String url = Constants.Webserive_Url+"add_work_report.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("sub_code",edt_sub_code.getText().toString());
                params.put("w_details",edt_comments.getText().toString());
                params.put("w_date","");
                params.put("u_id","");

                volley.CallVolley(url,params,"add_work_report");
            }
        });

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            Intent i=new Intent(Add_WorkReport.this, User_homepage.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
