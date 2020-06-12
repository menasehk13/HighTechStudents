package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity{
    ImageView imageView2;
    EditText firstName, lastName, sureName, studentId, regYear;
    Button submitPro;
    Spinner genderSpinner, depSpinner, secSpinner;
    FirebaseAuth proFirebaseAuth;
    FirebaseFirestore proFirestore;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        imageView2 = findViewById(R.id.imageView2);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        sureName = findViewById(R.id.surname);
        studentId = findViewById(R.id.studentid);
        regYear = findViewById(R.id.regYear);
        submitPro = findViewById(R.id.submitBtn);
        genderSpinner = findViewById(R.id.genderspinner);
        depSpinner = findViewById(R.id.departementspinner);
        secSpinner = findViewById(R.id.sectionspinner);
        proFirebaseAuth = FirebaseAuth.getInstance();
        proFirestore = FirebaseFirestore.getInstance();
        userId = proFirebaseAuth.getCurrentUser().getUid();

        submitPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = proFirestore.collection("studentPro").document(userId);
                String putFirstName = firstName.getText().toString();
                String putLastName = lastName.getText().toString();
                String putSureName = sureName.getText().toString();
                String putStudentId = studentId.getText().toString();
                String putRegYear = regYear.getText().toString();
                if(putFirstName.isEmpty())
                {
                    firstName.setError("please enter your first name");
                    firstName.requestFocus();
                }
                else if(putLastName.isEmpty())
                {
                    lastName.setError("please enter your last name");
                    lastName.requestFocus();
                }
                else if(putSureName.isEmpty())
                {
                    sureName.setError("please enter your sure name");
                    sureName.requestFocus();
                }
                else if(putStudentId.isEmpty())
                {
                    studentId.setError("please enter your id");
                    studentId.requestFocus();
                }
                else if(putRegYear.isEmpty())
                {
                    regYear.setError("please enter the year you registered");
                    regYear.requestFocus();
                }
                else{
                    Map<String, Object> profile = new HashMap<>();
                    profile.put("firstName", putFirstName);
                    profile.put("lastName", putLastName);
                    profile.put("sureName", putSureName);
                    profile.put("ID", putStudentId);
                    profile.put("registeredYear", putRegYear);
                    documentReference.set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                registerIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(registerIntent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "data is not inserted", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }



            }
        });
    }

}