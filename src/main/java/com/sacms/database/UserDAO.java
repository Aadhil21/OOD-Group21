package com.sacms.database;


/**
 * This class is the base class for all user DAOs.
 * @param <T> The type of user to be stored in the database.
 */
public abstract class UserDAO<T> implements DAO<T> {
    UserDAO() {}
    /**
     * This method creates the table for the user type if the table does not already exist.
     */
    @Override
    public void createTable() {
        final String usersQuery =
            "CREATE TABLE IF NOT EXISTS Users(uid INTEGER PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, " +
            "phone TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL DEFAULT 'student', birthday TEXT);";
        DBManager.getInstance().executeSQLStatement(usersQuery);
    }
}
