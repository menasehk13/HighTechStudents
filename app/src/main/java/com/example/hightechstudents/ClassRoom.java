package com.example.hightechstudents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClassRoom extends AppCompatActivity {
BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room);
         navigationView=findViewById(R.id.topNavigationView);
         Selecteditems(R.id.upcomingclass);
         navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 Selecteditems(item.getItemId());
                 return true;
             }
         });
    }
    public boolean Selecteditems(int items){
        Fragment fragment=null;
        switch (items){
            case R.id.upcomingclass:
                fragment=new upcomingclassFragment();
                break;
            case R.id.attendance:
                fragment=new attendanceFragment();
                break;
            default:
        }
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout,fragment);
        transaction.commit();
        return true;
    }
}