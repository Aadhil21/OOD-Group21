package com.sacms.services;

import com.sacms.database.DAO;
import com.sacms.database.StudentDAO;
import com.sacms.models.Student;

import java.util.HashMap;
import java.time.LocalDate;

public class StudentService {
    private static Student activeStudent;
    private static final HashMap<Integer, Student> studentRecord = new HashMap<>(10000);

    public Student StudentRegistered(int IDNumber) {
        if (studentRecord.containsKey(IDNumber)) {
            return studentRecord.get(IDNumber);
        }
        return null;
    }

    public void RegisterStudent(int id, String firstName, String lastName, String phone, String password) {
        Student newStudent = new Student(id, password, firstName, lastName, phone, LocalDate.of(2000, 1, 1));
        DAO<Student> studentDAO = new StudentDAO();
        studentDAO.create(newStudent);
        studentRecord.put(id, newStudent);
    }

}
