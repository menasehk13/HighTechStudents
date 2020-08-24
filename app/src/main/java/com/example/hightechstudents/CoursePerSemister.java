package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.escape.ArrayBasedCharEscaper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CoursePerSemister extends AppCompatActivity {

    EditText  textView,textView2;
    TextView viewcourse;
     Button button;
     DatabaseReference def;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_per_semister);
        textView = findViewById(R.id.textinputyear);
        textView2 = findViewById(R.id.inputsemi);
        button = findViewById(R.id.displaybtn);
        viewcourse = findViewById(R.id.planecoursedisplay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(textView.getText()) && TextUtils.isEmpty(textView2.getText())){

                    Toast.makeText(CoursePerSemister.this, "Please Input Values for both Year and Semister!", Toast.LENGTH_SHORT).show();
                }
                else{
                    def = FirebaseDatabase.getInstance().getReference().child("Department").child("Computer Engineering");
                    def.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            String course = dataSnapshot.child("Year").getValue().toString();
                            viewcourse.setText("Year");


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }
        });








    }
}