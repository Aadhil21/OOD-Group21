package com.sacms.models;

public class User {
    private int uid;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    public User(int uid, String firstName, String lastName, String phone, String email, String password) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User user) {
            if (user.getUid() == -1) return dataEqualsTo(user);
            return user.getUid() == uid;
        }

        return false;
    }

    private boolean dataEqualsTo(User user) {
        return user.getFirstName().equals(firstName) &&
            user.getLastName().equals(lastName) &&
            user.getPhone().equals(phone) &&
            user.getEmail().equals(email);
    }

    public int getUid() {
        return uid;
    }

    public void setId(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

