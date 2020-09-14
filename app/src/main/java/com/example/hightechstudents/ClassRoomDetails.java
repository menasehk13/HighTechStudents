package com.example.hightechstudents;

public class ClassRoomDetails {
    String Coursename;
    String Time;
    int Room;
public ClassRoomDetails(){

}
    public ClassRoomDetails(String coursename, String time, int room) {
        Coursename = coursename;
        Time = time;
        Room = room;
    }

    public String getCoursename() {
        return Coursename;
    }

    public void setCoursename(String coursename) {
        Coursename = coursename;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getRoom() {
        return Room;
    }

    public void setRoom(int room) {
        Room = room;
    }
}
