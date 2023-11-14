package com.sacms.services;

import com.sacms.database.StudentDAO;
import com.sacms.models.Student;
import java.util.HashMap;

public class StudentService {
    private static Student activeStudent;
    private static final HashMap<Integer, Student> studentRecord = new HashMap<>(10000);

    public Student StudentRegistered(int IDNumber) {
        if (studentRecord.containsKey(IDNumber)) {
            return studentRecord.get(IDNumber);
        }
        return null;
    }

    public void RegisterStudent(int IDNumber, String name, String password) {
        Student newStudent = new Student(IDNumber, name, password);
        StudentDAO.create(IDNumber, name, password);
        studentRecord.put(IDNumber, newStudent);
    }

}
