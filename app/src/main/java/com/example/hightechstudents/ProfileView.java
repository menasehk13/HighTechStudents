package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
proDep=findViewById(R.id.userdepartemnt);
proSec=findViewById(R.id.sectioncount);
proId=findViewById(R.id.studentviewid);
proName=findViewById(R.id.nameofuser);
proPhone=findViewById(R.id.userphonenumber);
proRegYear=findViewById(R.id.yearcount);
firebaseAuth=FirebaseAuth.getInstance();
proFirestore=FirebaseFirestore.getInstance();
userId=firebaseAuth.getCurrentUser().getUid();
proReference=proFirestore.collection("StudentPro").document(userId);
proReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
    @Override
    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
         if (task.isSuccessful()){
             DocumentSnapshot dataSnapshot=task.getResult();
             if (dataSnapshot.exists()){
              Log.d("User", valueOf(dataSnapshot.getData()));
                 proName.setText(format("%s %s %s", valueOf(dataSnapshot.get("Firstname")), valueOf(dataSnapshot.get("Lastname")), valueOf(dataSnapshot.get("surname"))));
                 proDep.setText(valueOf(dataSnapshot.get("Department")));
                 proPhone.setText(valueOf(dataSnapshot.get("phone")));
                 proSec.setText(valueOf(dataSnapshot.get("section")));
                 String year= (String) dataSnapshot.get("Year Registered");
                 int regyear=Integer.parseInt(year);
                 int currentYear=Calendar.getInstance().get(Calendar.YEAR);
                if (regyear==currentYear){
                    proRegYear.setText("1st");
                }
                if (regyear==regyear+1){
                    proRegYear.setText("2nd");
                }else if (regyear==regyear+2){
                    proRegYear.setText("3rd");
                }else if (regyear==regyear+3){
                    proRegYear.setText("4th");
                }else if (regyear==regyear+4){
                    proRegYear.setText("5th");
                }

             }else {

                 Log.d("Error","nosuchdocuments");
             }
         }else{

             Log.d("Error Task",task.getException().getMessage());

         }
    }
});
    }
}