package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.filepicker.controller.DialogSelectionListener;
import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.model.SubjectInfoVo;
import com.example.myapplication.model.SubjectResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class schedule extends AppCompatActivity implements DataInterface {

    Spinner spbranch;
    Spinner spsemester;
    Spinner spschedtype;
    EditText edt_schedule_note;
    ImageView img_schedule;
    TextView txt_schedule_file;
    Button btn_submit;

    Webservice_Volley volley;

    ArrayList<String> scheduleTypeList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        spbranch =(Spinner) findViewById(R.id.spbranch);
        spsemester = (Spinner) findViewById(R.id.spsemester);
        spschedtype = (Spinner) findViewById(R.id.spschedtype);
        edt_schedule_note = (EditText) findViewById(R.id.edt_schedule_note);
        img_schedule = (ImageView) findViewById(R.id.img_schedule);
        txt_schedule_file = (TextView) findViewById(R.id.txt_schedule_file);
        btn_submit = (Button) findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this, this);

        branchlist.add("Select Branch");
        branchList.add("Computer Engg.");
        branchList.add("Mechanical Engg.");

        scheduleTypeList.add("Select Schedule");
        scheduleTypeList.add("External Exam");
        scheduleTypeList.add("Internal Exam");
        scheduleTypeList.add("Expert Lectures");

        semList.add("Select Semester");
        semList.add("1");
        semList.add("2");
        semList.add("3");
        semList.add("4");
        semList.add("5");
        semList.add("6");
        semList.add("7");
        semList.add("8");

        ArrayAdapter<String> da = new ArrayAdapter<>(schedule.this,android.R.layout.simple_spinner_dropdown_item,scheduleTypeList);
        spschedtype.setAdapter(da);

        ArrayAdapter<String> da1 = new ArrayAdapter<>(schedule.this,android.R.layout.simple_spinner_dropdown_item,semList);
        spsemester.setAdapter(da1);

        ArrayAdapter<System> da2 = new ArrayAdapter<>(schedule.this,android.R.layout.simple_spinner_dropdown_item,branchList);
        spbranch.setAdapter(da2);

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
                params.put("s_barnch",spbranch.getSelectedItem().toString());
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


