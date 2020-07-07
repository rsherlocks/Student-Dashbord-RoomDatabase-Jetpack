package com.example.androidshaper.studentdashboardbyjetpackroomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Student {

    @PrimaryKey (autoGenerate = true)
    int id;
    String studentName;
    String studentId;
    String studentSemester;

    public Student(String studentName, String studentId, String studentSemester) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentSemester = studentSemester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentSemester() {
        return studentSemester;
    }

    public void setStudentSemester(String studentSemester) {
        this.studentSemester = studentSemester;
    }
}
