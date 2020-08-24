package com.example.hightechstudents;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharedpref {
    Context context;
    Sharedpref(Context context){
        this.context=context;
    }
    public void SaveUserCollection(String department){
        SharedPreferences preferences=context.getSharedPreferences("saveData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("department",department);
        editor.apply();
        editor.commit();
    }
    public String getDepartment(){
        SharedPreferences preferences=context.getSharedPreferences("saveData",Context.MODE_PRIVATE);
        return preferences.getString("department",null);
    }
}
