package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.executor.TaskExecutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerification extends AppCompatActivity {
EditText addcode;
Button verfiy;
FirebaseAuth auth;
TextView textView;
String Phonerecived,VerficationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);
        addcode=findViewById(R.id.codeview);
        verfiy=findViewById(R.id.verifiy);
        auth=FirebaseAuth.getInstance();
        textView=findViewById(R.id.phonenumenterd);
        Phonerecived=getIntent().getStringExtra("Phone");
        textView.setText(Phonerecived);
        SendVerficationCode(Phonerecived);
        verfiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Phonerecived.isEmpty()||Phonerecived.length()<6){
                    addcode.setError("Enter The Code we Have Sent");
                    addcode.isFocusable();
                }else{

                    VerfiyCode(Phonerecived);

                }
            }
        });
    }
    public void SendVerficationCode(String number){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBacks
        );
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            VerficationId=s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code=phoneAuthCredential.getSmsCode();
            if (code!=null){
                addcode.setText(code);
                VerfiyCode(code);

            }
        }
        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(PhoneVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };
    public  void VerfiyCode(String code){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(VerficationId,code);
        SignInWithCredential(credential);
    }

    private void SignInWithCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   Intent intent=new Intent(PhoneVerification.this,RegisterActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   intent.putExtra("PhoneOfUser",Phonerecived);
                   startActivity(intent);
                   overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);


               }else{
                   Toast.makeText(PhoneVerification.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}