package com.example.hightechstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.google.common.escape.ArrayBasedCharEscaper;

public class CoursePerSemister extends AppCompatActivity {
    ArrayAdapter Depa,semi,year;
    EditText departemnent, semister,yearr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_per_semister);







    }
}