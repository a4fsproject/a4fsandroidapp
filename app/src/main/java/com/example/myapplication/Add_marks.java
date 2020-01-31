package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Utility.Commonfunction;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;

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

    Webservice_Volley volley;

    ArrayList<String> branchList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        spbranch=(Spinner) findViewById(R.id.spbranch);
        spsemester=(Spinner) findViewById(R.id.spsemester);
        edt_sub_code=(EditText)findViewById(R.id.edt_sub_code);
        sub_name=(TextView)findViewById(R.id.sub_name);
        btn_upload=(Button)findViewById(R.id.btn_upload);

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

        String url = Constants.Webserive_Url+"get_studentlist_branch_sem.php";
        HashMap<String,String> params = new HashMap<>();
        params.put("s_branch",spbranch.getSelectedItem().toString());
        params.put("s_semester",spsemester.getSelectedItem().toString());
        volley.CallVolley(url,params,"get_studentlist_branch_sem");
    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {
        try{

            Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
