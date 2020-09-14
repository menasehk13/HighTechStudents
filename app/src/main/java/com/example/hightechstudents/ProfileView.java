package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Calendar;



public class ProfileView extends AppCompatActivity implements View.OnClickListener {
    TextView proDep,proSec,proId,proName,proPhone,proRegYear,proSem;
    DocumentReference proReference;
    FirebaseFirestore proFirestore;
    FirebaseAuth firebaseAuth;
    String userId;
    Button backbutton;
    String departement;
    String Year;
    String Semister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        proName=findViewById(R.id.username);
        proDep=findViewById(R.id.userderpartmnet);
        proId=findViewById(R.id.userstudentid);
        proPhone=findViewById(R.id.userstudentphone);
        proSec=findViewById(R.id.sectioncount);
        proRegYear=findViewById(R.id.yearcount);
        proSem=findViewById(R.id.semistercount);
        backbutton=findViewById(R.id.backbutton);
        firebaseAuth=FirebaseAuth.getInstance();
        backbutton.setOnClickListener(this);
        userId=firebaseAuth.getCurrentUser().getUid();
        proFirestore=FirebaseFirestore.getInstance();
        Sharedpref sharedpref=new Sharedpref(getApplicationContext());
        String dep=sharedpref.getDepartment();
        proReference=proFirestore.collection(dep).document(userId);
        proReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               DocumentSnapshot snapshot=task.getResult();
               if (snapshot.exists()) {
                   proName.setText(String.format("%s %s %s", String.valueOf(snapshot.get("Firstname")), String.valueOf(snapshot.get("Lastname")), String.valueOf(snapshot.get("surname"))));
                   departement=String.valueOf(snapshot.get("Department"));
                   proDep.setText(String.valueOf(snapshot.get("Department")));
                   proId.setText(String.valueOf(snapshot.get("Id")));
                   proPhone.setText(String.valueOf(snapshot.get("phone")));
                   proSec.setText(String.valueOf(snapshot.get("section")));
                   String yearnow=String.valueOf(snapshot.get("Year Registered"));
                   int i=Integer.parseInt(yearnow);
                   int CurrentYear=Calendar.getInstance().get(Calendar.YEAR);
                   if (i==CurrentYear){
                       Log.d("Tag",String.valueOf(1));
                       proRegYear.setText(String.valueOf(1));
                       return;
                   }
                   if (i==CurrentYear-1){
                       proRegYear.setText(String.valueOf(2));
                       proSem.setText(String.valueOf(2));
                       Log.d("Tag",String.valueOf(2));

                       return;
                   }
                   if (i==CurrentYear-2){
                       proRegYear.setText(String.valueOf(2));
                       Log.d("Tag",String.valueOf(3));

                       return;
                   }
                   if (i==CurrentYear-3){
                       proRegYear.setText(String.valueOf(2));
                       Log.d("Tag",String.valueOf(4));
                       return;
                   }
                   if (i==CurrentYear-4){
                       proRegYear.setText(String.valueOf(2));
                       Log.d("Tag",String.valueOf(5));
                   }


               }else{
                   Log.d("Error",task.getException().getMessage());
               }

            }
        });
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(ProfileView.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(ProfileView.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        startActivity(intent);
    }

    public void Logout_Clicked(View view) {
        final AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you Sure You Want to LogOut");
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                firebaseAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                return;
            }
        });
       AlertDialog dialog=alertDialog.create();
       dialog.show();
    }
}