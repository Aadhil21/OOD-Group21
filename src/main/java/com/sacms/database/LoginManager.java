package com.sacms.database;

import com.sacms.models.User;

public class LoginManager {
    private static LoginManager instance = null;
    private User currentUser;

    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }

        return instance;
    }
    private LoginManager() {}

    public void login(User user) {
        this.currentUser = user;
    }

    public void logout() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }
}
