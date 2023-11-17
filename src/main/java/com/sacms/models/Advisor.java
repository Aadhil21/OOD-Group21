package com.sacms.models;

public class Advisor extends User {
    public Advisor(int uid, String password, String phone, String firstName, String lastName) {
        super(uid, password, phone, firstName, lastName);
    }

    public void addToDatabase() {
//        DBManager.getInstance().addAdvisor(username, firstName, lastName, phone, password);
    }

    public void updateDatabase() {

    }

    public void deleteFromDatabase() {

    }
}