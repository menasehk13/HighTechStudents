package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    TextView username,studentid,departemnt;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth auth;
    String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       firebaseFirestore=FirebaseFirestore.getInstance();
       auth=FirebaseAuth.getInstance();
       userid=auth.getCurrentUser().getUid();
       DocumentReference documentReference=firebaseFirestore.collection("studentPro").document(userid);
         documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
              if (task.isSuccessful()){
                  String user,id,depart;
                  DocumentSnapshot documentSnapshot= task.getResult();
                  if (documentSnapshot.exists()){
                      Log.d("TAG","Document of students"+documentSnapshot.getData());
                      user = (String) documentSnapshot.get("firstName");
                      id=(String) documentSnapshot.get("ID");
                      depart=(String) documentSnapshot.get("Department");
                      username.setText(user);
                      studentid.setText(id);
                      departemnt.setText(depart);
                  }else{
                      Log.d("TAG","no such Document");
                  }

              }else{
                  Log.d("TAG","get failed with "+task.getException());
              }

             }

         });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View header=navigationView.getHeaderView(0);
        username=header.findViewById(R.id.user);
        departemnt=header.findViewById(R.id.student_view_departemnt);
        studentid=header.findViewById(R.id.studentviewid);
        SelectedItem(R.id.homeNav);
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
               @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                   SelectedItem(item.getItemId());
             return true;
            }
         });
    }
      public void SelectedItem(int Items){
          Fragment fragment=null;
        switch (Items){
            case R.id.homeNav:
                fragment=new HomeFragment();
                break;
            case R.id.gradeNav:
                fragment=new GradeFragment();
                break;
            case R.id.courseNav:
                fragment=new CourseFragment();
                break;
            case R.id.settingNav:
                fragment=new SettingFragment();
                break;
            default:
        }
          FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
        drawerLayout.closeDrawer(GravityCompat.START);
      }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}