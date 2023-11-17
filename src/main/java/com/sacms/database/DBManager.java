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

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
            return null;
        }
    }

    ResultSet executeSQLQuery(String sqlStatement) {
        Connection con = getConnection();
        if (con == null) return null;

        try {
            Statement stmt =  con.createStatement();
            return stmt.executeQuery(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e);
            return null;
        }
    }

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
username TEXT PRIMARY KEY,
first_name TEXT NOT NULL,
last_name TEXT NOT NULL,
phone TEXT NOT NULL,
password TEXT NOT NULL,
is_advisor INTEGER NOT NULL DEFAULT 0,
is_student INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE Clubs(
name TEXT PRIMARY KEY,
advisor TEXT NOT NULL,
FOREIGN KEY (advisor) REFERENCES users(username)
);

CREATE TABLE Positions(
club TEXT,
position TEXT,
FOREIGN KEY (club) REFERENCES Clubs(name),
PRIMARY KEY (club, position)
);

CREATE TABLE Members(
club TEXT,
student TEXT,
start_date INTEGER,
end_date INTEGER,
position TEXT NOT NULL,
FOREIGN KEY (club) REFERENCES Clubs(name),
FOREIGN KEY (student) REFERENCES Users(username),
PRIMARY KEY (club, student, start_date)
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
participant TEXT,
FOREIGN KEY (event) REFERENCES Events(e_id),
FOREIGN KEY (participant) REFERENCES Users(username),
PRIMARY KEY (event, participant)
);

* */
