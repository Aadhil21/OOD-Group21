package com.sacms.models;

import com.sacms.database.StudentDAO;

import java.time.LocalDate;

public class Student extends User {

    private LocalDate birthday;
    private final static StudentDAO DAO= new StudentDAO();

    public Student(int uid, String firstName, String lastName, String phone, String password, LocalDate birthday) {
        super(uid, firstName, lastName, phone, password);
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static Student registeredStudent(int uid, String password){
        Student student = DAO.retrieveStudentByID(uid);
        if (student.getPassword().equals(password)){
            return student;
        }
        else {
            return null;
        }
    }
}
