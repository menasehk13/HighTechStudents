package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
EditText email,password,confirm_pass;
Button submit;
FirebaseAuth auth;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirm_pass=findViewById(R.id.confirm_pass);
        submit=findViewById(R.id.loginsignup);
        auth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String validemail=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String confirm=confirm_pass.getText().toString().trim();
                if (validemail.isEmpty()){
                    email.setError("Please Enter your Email");
                } else if(pass.length()<8){
                    password.setError("Password must be >=8");
                } else if (!pass.equalsIgnoreCase(confirm)){
                    confirm_pass.setError("Password Doesn't Match");
                } else {
                    auth.createUserWithEmailAndPassword(validemail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }else{
                                FirebaseUser user=auth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (!task.isSuccessful()){
                                            Toast.makeText(SignUpActivity.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(SignUpActivity.this, "Success please Check your Email for Verification", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                                intent.putExtra("email",validemail);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}