package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.gson.Gson;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import org.json.JSONObject;

import java.util.HashMap;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;

public class Show_marks extends AppCompatActivity implements DataInterface {

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_marks);

        volley = new Webservice_Volley(this,this);

        String url = Constants.Webserive_Url+"get_marks.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("s_branch","");
        params.put("s_semester","");

        volley.CallVolley(url,params,"get_marks");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
