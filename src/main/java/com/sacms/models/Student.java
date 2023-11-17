package com.sacms.models;

import com.sacms.database.StudentDAO;

import java.time.LocalDate;

public class Student extends User {
    private LocalDate birthday;
    public Student(int id, String password, String firstName, String lastName, String phone, LocalDate birthday) {
        super(id, password, phone, firstName, lastName);
        this.birthday = birthday;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
