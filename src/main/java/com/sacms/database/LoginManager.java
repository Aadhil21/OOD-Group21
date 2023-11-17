package com.sacms.database;

import com.sacms.models.User;

/**
 * Singleton class to manage the currently logged-in user.
 * This class is used to keep track of the currently logged-in user.
 */
public class LoginManager {
    private static LoginManager instance = null;
    private User currentUser;

    /**
     * Returns the singleton instance of the LoginManager.
     * @return The singleton instance of the LoginManager.
     */
    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }

        return instance;
    }
    private LoginManager() {}

    /**
     * Sets the currently logged-in user.
     * @param user The user to set as the currently logged-in user.
     */
    public void login(User user) {
        this.currentUser = user;
    }

    /**
     * Logs out the currently logged-in user. Thereby, removes the
     * currently logged-in user and sets it to null.
     */
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Returns the currently logged-in user.
     * @return The currently logged-in user.
     */
    public User getCurrentUser() {
        return this.currentUser;
    }
}
