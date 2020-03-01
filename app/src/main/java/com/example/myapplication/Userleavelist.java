package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.UserLeaveListAdapter;
import com.example.myapplication.model.UserLeaveInfoVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class Userleavelist extends AppCompatActivity implements DataInterface {
    Webservice_Volley volley;

    RecyclerView recv_Leaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        volley = new Webservice_Volley(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave__list);

        recv_Leaves = (RecyclerView) findViewById(R.id.recv_Leaves);
        recv_Leaves.setLayoutManager(new LinearLayoutManager(this));


        String url = Constants.Webserive_Url + "get_leave_info.php";
        HashMap<String, String> params = new HashMap<>();

        params.put("u_id", "1");


        volley.CallVolley(url, params, "get_leave_info");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            UserLeaveInfoVo userleaveInfoVo = new Gson().fromJson(jsonObject.toString(), UserLeaveInfoVo.class);

            if (userleaveInfoVo != null) {

                if (userleaveInfoVo.getResult() != null) {

                    if (userleaveInfoVo.getResult().size() > 0) {

                        UserLeaveListAdapter adapter = new UserLeaveListAdapter(userleaveInfoVo.getResult());
                        recv_Leaves.setAdapter(adapter);

                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
