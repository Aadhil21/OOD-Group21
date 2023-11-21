package com.sacms.database;

import com.sacms.util.AppDataDir;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private final String url;

    private DBManager() {
        url = "jdbc:sqlite:" + AppDataDir.getAppDataDir() + "/scam-ood-cw.db";
    }

    /**
     * Returns the singleton instance of the DBManager.
     * @return The singleton instance of the DBManager.
     */
    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }

        return instance;
    }

    Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
            return null;
        }
    }

    /**
     * Executes an SQL query and returns the result set.
     * @param sqlStatement The SQL statement to execute.
     * @return The result set of the query.
     */
    ResultSet executeSQLQuery(String sqlStatement) throws SQLException {
        Connection con = getConnection();
        if (con == null) return null;
        Statement stmt =  con.createStatement();
        return stmt.executeQuery(sqlStatement);
    }

    /**
     * Executes an SQL statement.
     * @param sqlStatement The SQL statement to execute.
     */
    void executeSQLStatement(String sqlStatement) {
        Connection con = getConnection();
        if (con == null) return;

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e);
        }
    }
}

/*
CREATE TABLE Users(
uid INTEGER PRIMARY KEY,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL,
phone TEXT NOT NULL,
password TEXT NOT NULL,
birthday INTEGER,
role TEXT NOT NULL,
);

CREATE TABLE Clubs(
name TEXT PRIMARY KEY,
advisor INTEGER NOT NULL,
FOREIGN KEY (advisor) REFERENCES users(uid)
);

CREATE TABLE Members(
club TEXT,
student INTEGER
FOREIGN KEY (club) REFERENCES Clubs(name),
FOREIGN KEY (student) REFERENCES Users(uid),
PRIMARY KEY (club, student)
);

CREATE TABLE Events(
e_id INTEGER PRIMARY KEY,
club TEXT NOT NULL,
title TEXT NOT NULL,
start_date INTEGER NOT NULL,
end_date INTEGER NOT NULL,
start_time INTEGER,
end_time INTEGER,
FOREIGN KEY (club) REFERENCES Clubs(name),
);

CREATE TABLE EventAttendance(
event INTEGER,
participant INTEGER,
FOREIGN KEY (event) REFERENCES Events(e_id),
FOREIGN KEY (participant) REFERENCES Users(uid),
PRIMARY KEY (event, participant)
);

* */
