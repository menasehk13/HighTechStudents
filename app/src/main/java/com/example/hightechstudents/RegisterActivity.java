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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Year;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.hightechstudents.R.drawable.icons8_female_profile_100;

public class RegisterActivity extends AppCompatActivity{
    EditText firstName, lastName, sureName, studentId, regYear;
    Spinner  depSpinner, secSpinner;
    FirebaseAuth proFirebaseAuth;
    FirebaseFirestore proFirestore;
    String user;
    String fname,lname,sname,id,yearreg,deprtm,sect,phone;
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
        depSpinner=findViewById(R.id.departement);
        secSpinner=findViewById(R.id.Section);
        proFirebaseAuth=FirebaseAuth.getInstance();
        proFirestore=FirebaseFirestore.getInstance();
        user=proFirebaseAuth.getCurrentUser().getUid();
        sectionadapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,secs);
        departementadapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,deprt);
         depSpinner.setAdapter(departementadapter);
         secSpinner.setAdapter(sectionadapter);
        fname=firstName.getText().toString().trim();
        lname=lastName.getText().toString().trim();
        sname=sureName.getText().toString().trim();
        id=studentId.getText().toString().trim();
        deprtm=depSpinner.getSelectedItem().toString().trim();
        sect=secSpinner.getSelectedItem().toString().trim();
        yearreg=regYear.getText().toString().trim();
        phone=getIntent().getStringExtra("PhoneOfUser");
        if (depSpinner.getSelectedItem().equals("--Select Department--")&&secSpinner.getSelectedItem().equals("--Select Section--")){
            Toast.makeText(this, "Please Select a Departement or please Select your Section", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void SubmitProfile(View view) {
        DocumentReference reference=proFirestore.collection(deprtm).document(user);
        if (fname.isEmpty()||lname.isEmpty()||sname.isEmpty()){
            firstName.setError("Fill The Box");
            firstName.setFocusable(true);
            lastName.setError("Fill The Box");
            lastName.setFocusable(true);
            sureName.setError("Fill The Box");
            sureName.setFocusable(true);
            return;
        }

        Map<String,Object> hashmap=new HashMap<>();
        hashmap.put("Firstname",fname);
        hashmap.put("Lastname",lname);
        hashmap.put("surname",sname);
        hashmap.put("Id",id);
        hashmap.put("Department",deprt);
        hashmap.put("section",sect);
        hashmap.put("Year Registered",yearreg);
        hashmap.put("phone",phone);
        reference.set(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if (task.isSuccessful()){
                  Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                  overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                  startActivity(intent);
              }else {
                  Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
              }
            }
        });
    }
}