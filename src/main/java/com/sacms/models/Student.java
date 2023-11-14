package com.sacms.models;

import com.sacms.database.StudentDAO;

import java.util.HashMap;

public class Student {

    private int idNumber;
    private String name;
    private String password;

    public Student(int idNumber, String name, String password) {
        this.idNumber = idNumber;
        this.name = name;
        this.password = password;
    }

}
