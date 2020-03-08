package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.AssignmentListAdapter;
import com.example.myapplication.adapter.StudentLeaveListAdapter;
import com.example.myapplication.model.AssignmentInfoVo;
import com.example.myapplication.model.AssignmentResultVo;
import com.example.myapplication.model.StudentLeaveInfoVo;
import com.example.myapplication.model.SubjectResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;

public class Show_assignment extends AppCompatActivity implements DataInterface {

    EditText edt_sub_code;

    RecyclerView recv_assignment;

    Webservice_Volley volley;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        volley = new Webservice_Volley(this, this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_assignment);

        edt_sub_code = (EditText) findViewById(R.id.edt_sub_code);
        recv_assignment=(RecyclerView) findViewById(R.id.recv_assignment);
        recv_assignment.setLayoutManager(new LinearLayoutManager(this));

        edt_sub_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    String url = Constants.Webserive_Url + "get_assignment.php";
                    HashMap<String, String> params = new HashMap<>();

                    params.put("sub_code",textView.getText().toString());
                    params.put("s_branch","Computer Engg.");
                    params.put("s_semester","7");

                    volley.CallVolley(url, params, "get_assignment");

                    return true;
                }

                return false;
            }
        });

        String url = Constants.Webserive_Url + "get_assignment.php";
        HashMap<String, String> params = new HashMap<>();

        params.put("sub_code","");
        params.put("s_branch","Computer Engg.");
        params.put("s_semester","7");


        volley.CallVolley(url, params, "get_assignment");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            AssignmentInfoVo assignmentInfoVo = new Gson().fromJson(jsonObject.toString(), AssignmentInfoVo.class);

            if (assignmentInfoVo != null) {

                if (assignmentInfoVo .getResult() != null) {

                    if (assignmentInfoVo.getResult().size() > 0) {

                        AssignmentListAdapter adapter = new AssignmentListAdapter(assignmentInfoVo.getResult());
                        recv_assignment.setAdapter(adapter);

                    }
                    else  {
                        Toast.makeText(this, "No Assignment found for this subject.", Toast.LENGTH_LONG).show();
                    }

                }
                else  {
                    Toast.makeText(this, "No Assignment found for this subject.", Toast.LENGTH_LONG).show();
                }

            }
            else  {
                Toast.makeText(this, "No Assignment found for this subject.", Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
