package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.adapter.MyListAdapter;
import com.example.myapplication.model.StudentInfoVo;
import com.example.myapplication.model.SubjectInfoVo;
import com.example.myapplication.model.SubjectResultVo;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Add_marks extends AppCompatActivity implements DataInterface {

    Spinner spbranch;
    Spinner spsemester;
    EditText edt_sub_code;
    TextView sub_name;
    Button btn_upload;
    RecyclerView rcv_studentinfo;

    SubjectResultVo mainSubjectResultVo = null;

    Webservice_Volley volley;

    ArrayList<String> branchList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();

    MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        spbranch=(Spinner) findViewById(R.id.spbranch);
        spsemester=(Spinner) findViewById(R.id.spsemester);
        edt_sub_code=(EditText)findViewById(R.id.edt_sub_code);
        sub_name=(TextView)findViewById(R.id.sub_name);
        btn_upload=(Button)findViewById(R.id.btn_upload);
        rcv_studentinfo=(RecyclerView) findViewById(R.id.rcv_studentinfo);

        rcv_studentinfo.setLayoutManager(new LinearLayoutManager(this));

        volley = new Webservice_Volley(this,this);

        branchList.add("Select Branch");
        branchList.add("Computer Engg.");
        branchList.add("Mechanical Engg.");

        semList.add("Select Semester");
        semList.add("1");
        semList.add("2");
        semList.add("3");
        semList.add("4");
        semList.add("5");
        semList.add("6");
        semList.add("7");
        semList.add("8");

        ArrayAdapter<String> da = new ArrayAdapter<>(Add_marks.this,android.R.layout.simple_spinner_dropdown_item,branchList);
        spbranch.setAdapter(da);

        ArrayAdapter<String> da1 = new ArrayAdapter<>(Add_marks.this,android.R.layout.simple_spinner_dropdown_item,semList);
        spsemester.setAdapter(da1);

        spbranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String url = Constants.Webserive_Url+"get_studentlist_branch_sem.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                volley.CallVolley(url,params,"get_studentlist_branch_sem");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spsemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String url = Constants.Webserive_Url+"get_studentlist_branch_sem.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                volley.CallVolley(url,params,"get_studentlist_branch_sem");

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        edt_sub_code.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    String url = Constants.Webserive_Url+"get_subject_details.php";
                    HashMap<String,String> params = new HashMap<>();
                    params.put("sub_code",edt_sub_code.getText().toString());
                    volley.CallVolley(url,params,"get_subject_details");

                    return true;
                }

                return false;
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spbranch.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Select Branch",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view,"Please Select Semester",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (mainSubjectResultVo == null) {
                    Snackbar.make(view,"Please Select Subject For Mark.",Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (adapter == null) {
                    Snackbar.make(view,"No Student records found.",Snackbar.LENGTH_LONG).show();
                    return;
                }

                String url = Constants.Webserive_Url+"add_marks.php";
                HashMap<String,String> params = new HashMap<>();
                params.put("sub_code",mainSubjectResultVo.getSubCode());
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester",spsemester.getSelectedItem().toString());
                params.put("sub_name",mainSubjectResultVo.getSubName());
                params.put("mark_data","" +new Gson().toJsonTree(adapter.getListdata()).getAsJsonArray());


                volley.CallVolley(url,params,"add_marks");

            }
        });


    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try{

            if (tag.equalsIgnoreCase("get_subject_details")) {

                SubjectInfoVo subjectInfoVo = new Gson().fromJson(jsonObject.toString(), SubjectInfoVo.class);
                if (subjectInfoVo != null) {
                    if (subjectInfoVo.getResult() != null) {
                        if (subjectInfoVo.getResult().size() > 0) {

                            mainSubjectResultVo = subjectInfoVo.getResult().get(0);

                            sub_name.setText(mainSubjectResultVo.getSubName());

                        }
                        else {
                            Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                            sub_name.setText("");
                            mainSubjectResultVo = null;
                        }
                    }
                    else {
                        Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                        sub_name.setText("");
                        mainSubjectResultVo = null;
                    }
                }
                else {
                    Toast.makeText(this, "No Subject Found.", Toast.LENGTH_LONG).show();
                    sub_name.setText("");
                    mainSubjectResultVo = null;
                }

            }
            else if (tag.equalsIgnoreCase("add_marks")) {
                Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                if (jsonObject.getString("response").equalsIgnoreCase("1")) {

                    adapter = null;
                    rcv_studentinfo.setAdapter(null);

                    mainSubjectResultVo = null;
                    edt_sub_code.setText("");
                    sub_name.setText("");

                }
            }
            else {

                StudentInfoVo studentInfoVo = new Gson().fromJson(jsonObject.toString(), StudentInfoVo.class);
                if (studentInfoVo != null) {
                    if (studentInfoVo.getResult() != null) {
                        if (studentInfoVo.getResult().size() > 0) {
                            adapter  = new MyListAdapter(studentInfoVo.getResult());
                            rcv_studentinfo.setAdapter(adapter);
                        }
                    }
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
