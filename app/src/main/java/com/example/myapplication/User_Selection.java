package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class User_Selection extends AppCompatActivity {

    LinearLayout student,users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__selection_);

        student=(LinearLayout)findViewById(R.id.student);
        users=(LinearLayout)findViewById(R.id.users);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_Selection.this, Student_Login.class);
                startActivity(i);
            }
        });

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_Selection.this, User_login.class);
                startActivity(i);
            }
        });
    }
}
