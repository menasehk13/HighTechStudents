package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView signup;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpassword);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.loginsignup);
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            String valiedemail=email.getText().toString().trim();
            String passvalid=password.getText().toString().trim();
            @Override
            public void onClick(View v) {
                if (valiedemail.isEmpty()){
                    email.setError("Please enter your Email");
                }else if(passvalid.isEmpty()){
                    password.setError("Please enter Your Password");
                }else if(passvalid.length()<8){
                    password.setError("Password to Short");
                }else {
                    auth.signInWithEmailAndPassword(valiedemail,passvalid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                             if (!task.isSuccessful()){
                                 Toast.makeText(LoginActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                               }else{
                                 startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                 finish();
                             }
                        }
                    });
                }
            }
        });
    }
}