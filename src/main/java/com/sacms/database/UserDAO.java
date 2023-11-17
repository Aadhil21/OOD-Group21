package com.sacms.database;

import com.sacms.models.User;

public abstract class UserDAO<T> implements DAO<T> {
    @Override
    public void createTable() {
        final String usersQuery =
            "CREATE TABLE IF NOT EXISTS Users(uid INTEGER PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, " +
            "phone TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL DEFAULT 'student', birthday TEXT);";
        DBManager.getInstance().executeSQLStatement(usersQuery);
    }
}
