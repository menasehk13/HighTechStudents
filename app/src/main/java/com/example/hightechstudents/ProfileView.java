package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.protobuf.StringValue;

import java.time.Year;
import java.util.Calendar;

import static java.lang.String.*;

public class ProfileView extends AppCompatActivity {
    TextView proDep,proSec,proId,proName,proPhone,proRegYear;
    DocumentReference proReference;
    FirebaseFirestore proFirestore;
    FirebaseAuth firebaseAuth;
    String userId;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        proName=findViewById(R.id.username);
        proDep=findViewById(R.id.userderpartmnet);
        proId=findViewById(R.id.userstudentid);
        proPhone=findViewById(R.id.userstudentphone);
        proSec=findViewById(R.id.sectioncount);
        firebaseAuth=FirebaseAuth.getInstance();
        userId=firebaseAuth.getCurrentUser().getUid();
        proFirestore=FirebaseFirestore.getInstance();
        proReference=proFirestore.collection("StudentPro").document(userId);
        proReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               DocumentSnapshot snapshot=task.getResult();
               if (snapshot.exists()){
                   proName.setText(String.format("%s %s %s", String.valueOf(snapshot.get("Firstname")), String.valueOf(snapshot.get("Lastname")), String.valueOf(snapshot.get("surname"))));
                   proDep.setText(String.valueOf(snapshot.get("Department")));
                   proId.setText(String.valueOf(snapshot.get("Id")));
                   proPhone.setText(String.valueOf(snapshot.get("phone")));
                   proSec.setText(String.valueOf(snapshot.get("section")));

               }else{
                   Log.d("Error",task.getException().getMessage());
               }

            }
        });
    }
}