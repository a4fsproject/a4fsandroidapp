package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.filepicker.controller.DialogSelectionListener;
import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Schedule extends AppCompatActivity implements DataInterface {

    Spinner spbranch;
    Spinner spsemester;
    Spinner spschedtype;
    EditText edt_schedule_note;

    TextView txt_file_name,txt_upload_file;

    Button btn_submit;

    Webservice_Volley volley;

    ArrayList<String> scheduleTypeList = new ArrayList<>();
    ArrayList<String> branchList = new ArrayList<>();
    ArrayList<String> semList = new ArrayList<>();

    File selectedFile = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        spbranch =(Spinner) findViewById(R.id.spbranch);
        spsemester = (Spinner) findViewById(R.id.spsemester);
        spschedtype = (Spinner) findViewById(R.id.spschedtype);
        edt_schedule_note = (EditText) findViewById(R.id.edt_schedule_note);

        txt_upload_file = (TextView) findViewById(R.id.txt_upload_file);
        txt_file_name = (TextView) findViewById(R.id.txt_file_name);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        volley = new Webservice_Volley(this, this);

        branchList.add("Select Branch");
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

        ArrayAdapter<String> da = new ArrayAdapter<>(Schedule.this,android.R.layout.simple_spinner_dropdown_item,scheduleTypeList);
        spschedtype.setAdapter(da);

        ArrayAdapter<String> da1 = new ArrayAdapter<>(Schedule.this,android.R.layout.simple_spinner_dropdown_item,semList);
        spsemester.setAdapter(da1);

        ArrayAdapter<String> da2 = new ArrayAdapter<>(Schedule.this,android.R.layout.simple_spinner_dropdown_item,branchList);
        spbranch.setAdapter(da2);

        txt_upload_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogProperties properties = new DialogProperties();

                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = new String[]{"pdf"};
                properties.show_hidden_files = false;

                FilePickerDialog dialog = new FilePickerDialog(Schedule.this,properties);
                dialog.setTitle("Select a File");

                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                        //files is the array of the paths of files selected by the Application User.

                        if (files.length > 0) {
                            selectedFile = new File(files[0]);
                            txt_file_name.setText((selectedFile == null) ? "No File Selected." : selectedFile.getName());
                        }
                    }
                });

                dialog.show();


            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (spbranch.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select Branch", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spsemester.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select semester", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (spschedtype.getSelectedItemPosition() <= 0) {
                    Snackbar.make(view, "Please Select Schedule Type", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (selectedFile == null) {
                    Snackbar.make(view, "Please Select Schedule File", Snackbar.LENGTH_LONG).show();
                    return;
                }

                String encodeFileToBase64Binary = encodeFileToBase64Binary(selectedFile);


                String url = Constants.Webserive_Url + "Schedule.php";
                HashMap<String, String> params = new HashMap<>();
                params.put("s_branch",spbranch.getSelectedItem().toString());
                params.put("s_semester", spsemester.getSelectedItem().toString());
                params.put("schedule_type", spschedtype.getSelectedItem().toString());
                params.put("schedule_file", encodeFileToBase64Binary);
                params.put("schedule_note",edt_schedule_note.getText().toString());


                volley.CallVolley(url, params, "Schedule");

            }
        });
    }

    private String encodeFileToBase64Binary(File yourFile) {
        int size = (int) yourFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(yourFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String encoded = Base64.encodeToString(bytes,Base64.NO_WRAP);
        return encoded;
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


