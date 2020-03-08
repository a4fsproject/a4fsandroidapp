package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication.adapter.AssignmentListAdapter;
import com.example.myapplication.adapter.ScheduleListAdapter;
import com.example.myapplication.model.AssignmentInfoVo;
import com.example.myapplication.model.ScheduleInfoVo;
import com.example.myapplication.model.ScheduleResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class Show_Schedule extends AppCompatActivity implements DataInterface {

    RecyclerView recv_Show_Schedule;

    Webservice_Volley volley;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        volley = new Webservice_Volley(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__schedule);

        recv_Show_Schedule=(RecyclerView) findViewById(R.id.recv_Show_Schedule);
        recv_Show_Schedule.setLayoutManager(new LinearLayoutManager(this));

        String url = Constants.Webserive_Url + "get_schedule.php";
        HashMap<String, String> params = new HashMap<>();

        params.put("s_branch","Computer Engg.");
        params.put("s_semester","7");


        volley.CallVolley(url, params, "get_schedule");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            ScheduleInfoVo scheduleInfoVo = new Gson().fromJson(jsonObject.toString(), ScheduleInfoVo.class);

            if (scheduleInfoVo != null) {

                if (scheduleInfoVo .getResult() != null) {

                    if (scheduleInfoVo.getResult().size() > 0) {

                        ScheduleListAdapter adapter = new ScheduleListAdapter(scheduleInfoVo.getResult());
                        recv_Show_Schedule.setAdapter(adapter);

                    }

                }

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}