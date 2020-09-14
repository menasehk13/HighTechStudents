package com.example.hightechstudents;

public class Courseview {
    String Coursename;
     int Credithour;
    public Courseview(){

    }
    public Courseview(String Corsename,int Credithour){
        this.Coursename=Corsename;
        this.Credithour=Credithour;
    }

    public void setCoursename(String coursename) {
        Coursename = coursename;
    }

    public void setCredithour(int credithour) {
        Credithour = credithour;
    }

    public String getCoursename() {
        return Coursename;
    }

    public int getCredithour() {
        return Credithour;
    }
}
