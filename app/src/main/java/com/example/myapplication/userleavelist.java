package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class userleavelist extends AppCompatActivity implements DataInterface {
    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        volley = new Webservice_Volley(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave__list);

        String url = Constants.Webserive_Url + "get_leave_info.php";
        HashMap<String, String> params = new HashMap<>();

        params.put("s_enroll_number","123456789100");


        volley.CallVolley(url, params, "get_leave_info");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

    }
}
