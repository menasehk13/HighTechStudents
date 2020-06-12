package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String valiedemail=email.getText().toString().trim();
                String passvalid=password.getText().toString().trim();
                if (TextUtils.isEmpty(valiedemail)){
                    email.setError("Please enter your email");
                }
                else if (TextUtils.isEmpty(passvalid)){
                    password.setError("Password is empty please enter your password");
                }
                else if (passvalid.length()<8){
                    password.setError("password is to short ");
                }else {
                    auth.signInWithEmailAndPassword(valiedemail, passvalid).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(getApplicationContext(),CodeActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}