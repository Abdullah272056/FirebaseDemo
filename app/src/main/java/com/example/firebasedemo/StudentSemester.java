package com.example.firebasedemo;

public class StudentSemester {
    String id,semester,cgpa;

    public StudentSemester() {
    }

    public StudentSemester(String id, String semester, String cgpa) {
        this.id = id;
        this.semester = semester;
        this.cgpa = cgpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }
}
