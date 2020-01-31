package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class Feedback extends AppCompatActivity implements DataInterface {

   TextView select_image;
    RatingBar ratingBar;
    EditText edt_comments;
    Button btn_feedback_submit;


    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        select_image=(TextView) findViewById(R.id.select_image);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        edt_comments=(EditText) findViewById(R.id.edt_comments);
        btn_feedback_submit=(Button)findViewById(R.id.btn_feedback_submit);

        volley = new Webservice_Volley(this,this);

        btn_feedback_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!Commonfunction.checkString(edt_comments.getText().toString())) {
                    edt_comments.setError("Please Give Your Comments.");
                    return;
                }


                String url = Constants.Webserive_Url+"feedback.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("f_details",edt_comments.getText().toString());
                params.put("ratings",""+ratingBar.getRating());
                params.put("f_date","");
                params.put("u_id","");
                params.put("s_enroll_number","");

                volley.CallVolley(url,params,"feedback");

                    }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try{

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

            Intent i=new Intent(Feedback.this, Student_Home_Page.class);
            startActivity(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
