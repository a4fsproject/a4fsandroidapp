package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Student_Home_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__home__page);
    }

    public void CLickOnassignment(View view) {

        Intent i = new Intent(Student_Home_Page.this,Show_assignment.class);
        startActivity(i);

    }

    public void CLickOnLeave(View view) {

        Intent i = new Intent(Student_Home_Page.this,Leave_List.class);
        startActivity(i);

    }

    public void CLickOnfeedback(View view) {

        Intent i = new Intent(Student_Home_Page.this,Feedback.class);
        startActivity(i);

    }

    public void CLickOnschedule(View view) {

        Intent i = new Intent(Student_Home_Page.this,Show_Schedule.class);
        startActivity(i);

    }


}
