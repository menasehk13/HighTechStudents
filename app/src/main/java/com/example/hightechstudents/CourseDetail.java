package com.example.hightechstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CourseDetail extends AppCompatActivity {
TextView courseTitle,CreditHour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        courseTitle=findViewById(R.id.CourseTitle);
        CreditHour=findViewById(R.id.CreditHour);
        String coursetit=getIntent().getStringExtra("CourseTitle");
        String credit=getIntent().getStringExtra("CreditHour");
        courseTitle.setText(coursetit);
        CreditHour.setText(credit);
    }
}