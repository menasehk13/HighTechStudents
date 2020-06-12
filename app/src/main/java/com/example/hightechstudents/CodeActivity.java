package com.example.hightechstudents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CodeActivity extends AppCompatActivity {
EditText codeInput;
String inputCode="hightech123";
Button ContinueTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        codeInput=findViewById(R.id.CodeInput);
        ContinueTo=findViewById(R.id.continueto);
        ContinueTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputCode=codeInput.getText().toString();
                if (inputCode.isEmpty()){
                    codeInput.setError("PLEASE ENTER THE CODE");
                    codeInput.requestFocus();
                    ContinueTo.setClickable(false);
                }  else {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });


    }
}