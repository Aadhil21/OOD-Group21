package com.sacms.models;

import com.sacms.database.StudentDAO;

public class Student extends User {

    static StudentDAO DAO = new StudentDAO();

    public Student(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);
    }

    public static Student getStudent(int uid, String pass) {
        try {
            Student student = DAO.read(uid);
            if (student.getPassword().equals(pass)) {
                return student;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    public static Student setStudent(Student student) {
        try {
            DAO.create(student);
            return student;
        }catch (Exception e){
            return null;
        }
    }

}
