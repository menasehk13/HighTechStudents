package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    Spinner countrycode;
    ArrayAdapter councode;
    EditText phonenum;
    String codes[]={"+251","+252","+001"};
    String userInput,finalinput;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        phonenum=findViewById(R.id.phonenum);
        countrycode=findViewById(R.id.spinnercode);
        councode=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,codes);
        countrycode.setAdapter(councode);

    }

    public void Signup_clicked(View view) {
      userInput=phonenum.getText().toString().trim();
      finalinput=countrycode.getSelectedItem()+userInput;
      Intent intent=new Intent(getApplicationContext(),PhoneVerification.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
      intent.putExtra("Phone",finalinput);
      startActivity(intent);
      overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

    }
}