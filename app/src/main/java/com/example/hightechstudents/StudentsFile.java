package com.example.hightechstudents;

public class StudentsFile  {
   public String studentname;
    public String studentlastname;
   public String studentsurname;
    public String studentDepartemnt;
    public String studentSection;
    public String StudentYear;
    public String studentPhone;
    public String studentGender;
    String studentcurrentGrade;
    String studentLastGrade;
    public String studentid;

    public StudentsFile(){

    }
    public StudentsFile(String studentname, String studentlastname, String studentsurname,String studentDepartemnt,String studentSection,String studentYear,String studentid,String studentPhone){
        this.studentname=studentname;
        this.studentlastname=studentlastname;
        this.studentsurname=studentsurname;
        this.studentDepartemnt=studentDepartemnt;
        this.studentSection=studentSection;
        this.StudentYear=studentYear;
        this.studentid=studentid;
        this.studentPhone=studentPhone;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public void setStudentlastname(String studentlastname) {
        this.studentlastname = studentlastname;
    }

    public void setStudentsurname(String studentsurname) {
        this.studentsurname = studentsurname;
    }

    public void setStudentDepartemnt(String studentDepartemnt) {
        this.studentDepartemnt = studentDepartemnt;
    }

    public void setStudentSection(String studentSection) {
        this.studentSection = studentSection;
    }

    public void setStudentYear(String studentYear) {
        StudentYear = studentYear;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getStudentlastname() {
        return studentlastname;
    }

    public String getStudentsurname() {
        return studentsurname;
    }


    public String getStudentDepartemnt() {
        return studentDepartemnt;
    }

    public String getStudentSection() {
        return studentSection;
    }

    public String getStudentYear() {
        return StudentYear;
    }

    public String getStudentid() {
        return studentid;
    }

    public String getStudentPhone() {
        return studentPhone;
    }
}
