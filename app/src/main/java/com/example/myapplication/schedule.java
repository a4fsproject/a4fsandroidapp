package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class schedule extends AppCompatActivity implements DataInterface {

    Spinner spsemester;
    Spinner spschedtype;
    EditText edt_schedule_note;
    ImageView img_schedule;
    TextView txt_schedule_file;
    Button btn_submit;

    Webservice_Volley volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        spsemester = (Spinner) findViewById(R.id.spsemester);
        spschedtype = (Spinner) findViewById(R.id.spschedtype);
        edt_schedule_note = (EditText) findViewById(R.id.edt_schedule_note);
        img_schedule = (ImageView) findViewById(R.id.img_schedule);
        txt_schedule_file = (TextView) findViewById(R.id.txt_schedule_file);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this, this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select semester", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spschedtype.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select Schedule Type", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (txt_schedule_file == null) {
                    Snackbar.make(view, "Please Select Schedule File", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (img_schedule == null) {
                    Snackbar.make(view, "Please Select Image of Schedule", Snackbar.LENGTH_LONG).show();
                    return;
                }

                String url = Constants.Webserive_Url + "schedule.php";
                HashMap<String, String> params = new HashMap<>();
                params.put("s_semester", spsemester.getSelectedItem().toString());
                params.put("schedule_type", spschedtype.getSelectedItem().toString());
                params.put("schedule_file", txt_schedule_file.getText().toString());
                params.put("schedule_note",edt_schedule_note.getText().toString());


                volley.CallVolley(url, params, "schedule");

            }
        });
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


