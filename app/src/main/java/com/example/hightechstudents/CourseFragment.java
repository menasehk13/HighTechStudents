package com.example.hightechstudents;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Year;


public class CourseFragment extends Fragment {

TextView a,b,c;
DatabaseReference reff;
Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //textview=view.findbyid()
        a = view.findViewById(R.id.textView6);
        b = view.findViewById(R.id.textView7);
        c= view.findViewById(R.id.textView8);
        btn = view.findViewById(R.id.buttondis);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("hightechteacher-bc465").child("Department").child("Computer Engineering").child("Year1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String mydatabase = dataSnapshot.child("higtechteacher-bc465").getValue().toString();

                       String Department = dataSnapshot.child("Department").getValue().toString();
                        String computer_Engineering = dataSnapshot.child("Computer Engineering").getValue().toString();
                        String Year1 = dataSnapshot.child("Year1").getValue().toString();

                        a.setText(Department);
                        b.setText(computer_Engineering);
                        c.setText(Year1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}