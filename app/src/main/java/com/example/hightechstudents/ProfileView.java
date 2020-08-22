package com.example.hightechstudents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ProfileView extends AppCompatActivity {
    TextView proDep,proSec,proId,proName,proFullName,proPhone,proRegYear;
    DocumentReference proReference;
    FirebaseFirestore proFirestore;
    FirebaseAuth firebaseAuth;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        proDep = findViewById(R.id.proDep);
        proSec = findViewById(R.id.proSec);
        proId = findViewById(R.id.proId);
        proFullName = findViewById(R.id.proFullName);
        proPhone = findViewById(R.id.proPhone);
        proRegYear = findViewById(R.id.proRegisteredYear);
        proName = findViewById(R.id.proName);
        proFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        proReference = proFirestore.collection("studentPro").document(userId);
        proReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String fullName = "Full Name:  "+documentSnapshot.getString("FirstName")+" "+documentSnapshot.getString("Lastname")+" "+documentSnapshot.getString("surename");
                String section = "Section: "+ documentSnapshot.getString("section");
                String departement = "Department: "+documentSnapshot.getString("Department");
                String id = "ID: "+documentSnapshot.getString("Id");
                String year = "Registered Year: "+documentSnapshot.getString("Year Registered");
                String phone = "Phone: "+documentSnapshot.getString("phone");
                String name =documentSnapshot.getString("Firstname")+" "+documentSnapshot.getString("Lastname");
                proDep.setText(departement);
                proSec.setText(section);
                proId.setText(id);
                proFullName.setText(fullName);
                proName.setText(name);
                proRegYear.setText(year);
                proPhone.setText(phone);
            }
        });



    }
}