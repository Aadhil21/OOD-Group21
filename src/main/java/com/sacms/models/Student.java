package com.sacms.models;

import com.sacms.database.DAOFactory;
import com.sacms.database.StudentDAO;

public class Student extends User {

    static StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);

    public Student(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);
    }
}
