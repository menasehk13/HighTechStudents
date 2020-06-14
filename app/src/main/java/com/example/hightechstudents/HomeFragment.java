package com.example.hightechstudents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class HomeFragment extends Fragment {
    TextView firstname,lastname,surname,department,section,year,semister,studentid;
    ImageView imageView;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstname=view.findViewById(R.id.viewfirstname);
        lastname=view.findViewById(R.id.viewlastname);
        surname=view.findViewById(R.id.viewsurname);
        department=view.findViewById(R.id.viewdepartment);
        section=view.findViewById(R.id.viewsection);
        year=view.findViewById(R.id.viewyear);
        semister=view.findViewById(R.id.viewsemister);
        studentid=view.findViewById(R.id.viewstudentid);

        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        firestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firestore.collection("studentPro").document(user.getUid());
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    String first,last,sur,sec,dep,years,id,gender;
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if (documentSnapshot.exists()){
                     first=(String) documentSnapshot.get("firstName");
                     last=(String) documentSnapshot.get("lastName");
                     sur=(String) documentSnapshot.get("sureName");
                     sec=(String) documentSnapshot.get("Section");
                     dep=(String) documentSnapshot.get("Department");
                    years=(String) documentSnapshot.get("registeredYear");
                    id=(String) documentSnapshot.get("ID");
                    gender=(String) documentSnapshot.get("Gender");
                    firstname.setText(first);
                    lastname.setText(last);
                    surname.setText(sur);
                    section.setText(sec);
                    department.setText(dep);
                    year.setText(years);
                    studentid.setText(id);
                    }
                }else{
                    Log.d("Tag","FAILED:"+task.getException());
                }
            }
        });



    }
}