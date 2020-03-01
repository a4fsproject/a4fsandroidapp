package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class User_homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);
    }

    public void CLickOnadd_marks(View view) {

        Intent i = new Intent(User_homepage.this, Add_marks.class);
        startActivity(i);

    }

    public void CLickOnassignment(View view) {

        Intent i = new Intent(User_homepage.this, Upload_assignment.class);
        startActivity(i);

    }

    public void CLickOnadd_workreport(View view) {

        Intent i = new Intent(User_homepage.this, Add_WorkReport.class);
        startActivity(i);

    }

    public void CLickOnschedule(View view) {

        Intent i = new Intent(User_homepage.this, Schedule.class);
        startActivity(i);

    }
}

