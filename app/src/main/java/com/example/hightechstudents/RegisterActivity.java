package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.hightechstudents.R.drawable.icons8_female_profile_100;

public class RegisterActivity extends AppCompatActivity{
    EditText firstName, lastName, sureName, studentId, regYear;
    Button submitPro;
    Spinner  depSpinner, secSpinner;
    FirebaseAuth proFirebaseAuth;
    FirebaseFirestore proFirestore;
    String userId;
    String deprt[]={"--Select Department--","Computer Engineering","Computer Science","Accounting","TVTE"};
    String secs[]={"--Select Section--","1","2","3"};
    ArrayAdapter sectionadapter,departementadapter;
    int year= Calendar.getInstance().get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firstName=findViewById(R.id.first);
        lastName=findViewById(R.id.last_name);
        sureName=findViewById(R.id.surname);
        studentId=findViewById(R.id.Id);
        regYear=findViewById(R.id.Year_Registered);
        secSpinner=findViewById(R.id.Section);
        depSpinner=findViewById(R.id.departement);
        submitPro=findViewById(R.id.submit);
        sectionadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,secs);
        departementadapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,deprt);
        secSpinner.setAdapter(sectionadapter);
        depSpinner.setAdapter(departementadapter);
        proFirebaseAuth = FirebaseAuth.getInstance();
        proFirestore = FirebaseFirestore.getInstance();
        userId = proFirebaseAuth.getCurrentUser().getUid();

        submitPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = proFirestore.collection("studentPro").document(userId);
                final String putFirstName = firstName.getText().toString();
                final String putLastName = lastName.getText().toString();
                final String putSureName = sureName.getText().toString();
                final String putStudentId = studentId.getText().toString();
                final String putRegYear = regYear.getText().toString();
                final String putDepartment=depSpinner.getSelectedItem().toString().trim();
                final String putSection=secSpinner.getSelectedItem().toString().trim();
                final String putPhoneNumber=getIntent().getStringExtra("PhoneOfUser");
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
                else if(putRegYear.isEmpty()|| putRegYear.length()>year)
                {
                    regYear.setError("please enter the year you registered");
                    regYear.requestFocus();
                }else if(putDepartment.equalsIgnoreCase("Select Department"))
                {
                    Toast.makeText(RegisterActivity.this, "Please select a Department", Toast.LENGTH_SHORT).show();
                }
                else{
                    Map<String, Object> profile = new HashMap<>();
                    profile.put("firstName", putFirstName);
                    profile.put("lastName", putLastName);
                    profile.put("sureName", putSureName);
                    profile.put("ID", putStudentId);
                    profile.put("Department",putDepartment);
                    profile.put("Section",putSection);
                    profile.put("registeredYear", putRegYear);
                    profile.put("Phone",putPhoneNumber);
                    documentReference.set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent registerIntent = new Intent(RegisterActivity.this, CodeActivity.class);
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