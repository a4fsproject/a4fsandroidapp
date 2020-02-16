package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.adapter.MyListAdapter;
import com.example.myapplication.adapter.StudentLeaveListAdapter;
import com.example.myapplication.model.StudentleaveInfoVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class Leave_List extends AppCompatActivity implements DataInterface {
    Webservice_Volley volley;

    RecyclerView recv_Leaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        volley = new Webservice_Volley(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave__list);

        recv_Leaves = (RecyclerView)findViewById(R.id.recv_Leaves);
        recv_Leaves.setLayoutManager(new LinearLayoutManager(this));

        String url = Constants.Webserive_Url + "get_student_leave.php";
        HashMap<String, String> params = new HashMap<>();

        params.put("s_enroll_number","123456789100");


        volley.CallVolley(url, params, "get_student_leave");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            StudentleaveInfoVo studentleaveInfoVo = new Gson().fromJson(jsonObject.toString(),StudentleaveInfoVo.class);

            if (studentleaveInfoVo != null) {

                if (studentleaveInfoVo .getResult() != null) {

                    if (studentleaveInfoVo.getResult().size() > 0) {

                        StudentLeaveListAdapter adapter = new StudentLeaveListAdapter(studentleaveInfoVo.getResult());
                        recv_Leaves.setAdapter(adapter);

                    }

                }

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
