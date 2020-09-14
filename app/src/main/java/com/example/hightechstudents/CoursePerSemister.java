package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.escape.ArrayBasedCharEscaper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;

public class CoursePerSemister extends AppCompatActivity {
    ArrayList<Courseview> coursev=new ArrayList<>();
    CourseAdapter courseAdapter;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
     DatabaseReference def;
     FirebaseAuth auth;
     RecyclerView recyclerView;
     String year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_per_semister);
         recyclerView=findViewById(R.id.recyclecourse);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        courseAdapter=new CourseAdapter(coursev);
        recyclerView.setAdapter(courseAdapter);
        Sharedpref sharedpref=new Sharedpref(getApplicationContext());
        auth=FirebaseAuth.getInstance();
        String user=auth.getCurrentUser().getUid();
        final String dep=sharedpref.getDepartment();
        firebaseFirestore=FirebaseFirestore.getInstance();
        documentReference=firebaseFirestore.collection(dep).document(user);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   DocumentSnapshot snapshot=task.getResult();
                   if (snapshot.exists()){
                       year=String.valueOf(snapshot.get("Year Registered"));
                       int yearreg=Integer.parseInt(year);
                       int currentYear= Calendar.getInstance().get(Calendar.YEAR);
                       if (yearreg==currentYear){

                       }
                       if (yearreg==currentYear-1){
                           FirebaseDatabase database=FirebaseDatabase.getInstance();
                           def =database.getReference().child("Department").child(dep).child("Year2").child("Semister2");
                           def.addValueEventListener(new ValueEventListener() {
                               @Override
                               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                   for (DataSnapshot result:dataSnapshot.getChildren()){
                                       Courseview courseview=result.getValue(Courseview.class);
                                       coursev.add(courseview);
                                   }
                                   courseAdapter=new CourseAdapter(coursev);
                                   recyclerView.setAdapter(courseAdapter);
                                   courseAdapter.notifyDataSetChanged();
                               }

                               @Override
                               public void onCancelled(@NonNull DatabaseError databaseError) {

                               }
                           });
                       }
                   }
            }
        });


    }
}