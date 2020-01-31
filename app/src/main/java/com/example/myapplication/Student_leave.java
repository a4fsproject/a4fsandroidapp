package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;

public class Student_leave extends AppCompatActivity implements DataInterface {
    EditText edt_from_date;
    EditText edt_to_date;
    TextView txt_select_image;
    ImageView img_leave;
    TextView txt_upload_photo;
    EditText edt_reason;
    Button btn_submit;

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

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkEnrollno(edt_from_date.getText().toString())) {
                    edt_from_date.setError("Please Enter Valid Date.");
                    return;
                }

                if (!Commonfunction.checkPassword(edt_to_date.getText().toString())) {
                    edt_to_date.setError("Please Enter Valid Date");
                    return;
                }

                if (!Commonfunction.checkPassword(edt_reason.getText().toString())) {
                    edt_reason.setError("Please Enter Reason");
                    return;
                }

                String url = Constants.Webserive_Url + "student_leave.php";
                HashMap<String, String> params = new HashMap<>();
                params.put("edt_from_date", edt_from_date.getText().toString());
                params.put("edt_to_date", edt_to_date.getText().toString());
                params.put("edt_reason0", edt_reason.getText().toString());

                volley.CallVolley(url, params, "student_leave");

            }

        });
    }


    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            Intent i=new Intent(Student_leave.this, Leave_List.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
