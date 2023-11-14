package com.scam.scam.model;

import com.scam.scam.util.AppDataDir;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private final String url;

    private DBManager() {
        url = "jdbc:sqlite:" + AppDataDir.getAppDataDir() + "/scam-ood-cw.db";
        populateDB();
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

    private ResultSet executeSQLQuery(String sqlStatement) {
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

    private void executeSQLStatement(String sqlStatement) {
        Connection con = getConnection();
        if (con == null) return;

        try {
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e);
        }
    }

    private void populateDB() {
        final String createUsers = "CREATE TABLE IF NOT EXISTS Users(username TEXT PRIMARY KEY, first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL, phone TEXT NOT NULL, password TEXT NOT NULL, is_advisor INTEGER NOT NULL DEFAULT 0, " +
                "is_student INTEGER NOT NULL DEFAULT 1);";
        final String createClubs = "CREATE TABLE IF NOT EXISTS Clubs(name TEXT PRIMARY KEY, advisor TEXT NOT NULL, " +
                "FOREIGN KEY (advisor) REFERENCES users(username));";
        final String createPositions = "CREATE TABLE IF NOT EXISTS Positions(club TEXT, position TEXT, " +
                "FOREIGN KEY (club) REFERENCES Clubs(name), PRIMARY KEY (club, position));";
        final String createMembers = "CREATE TABLE IF NOT EXISTS Members(club TEXT, student TEXT, start_date INTEGER, " +
                "end_date INTEGER, position TEXT NOT NULL, FOREIGN KEY (club) REFERENCES Clubs(name), " +
                "FOREIGN KEY (student) REFERENCES Users(username), PRIMARY KEY (club, student, start_date));";
        final String createEvents = "CREATE TABLE IF NOT EXISTS Events(e_id INTEGER PRIMARY KEY, club TEXT NOT NULL, " +
                "title TEXT NOT NULL, start_date INTEGER NOT NULL, end_date INTEGER NOT NULL, start_time INTEGER," +
                "end_time INTEGER, FOREIGN KEY (club) REFERENCES Clubs(name));";
        final String createEventAttendance = "CREATE TABLE IF NOT EXISTS EventAttendance(event INTEGER, participant TEXT, " +
                "FOREIGN KEY (event) REFERENCES Events(e_id), FOREIGN KEY (participant) REFERENCES Users(username), " +
                "PRIMARY KEY (event, participant));";
        final String createEventInvitation = "CREATE TABLE IF NOT EXISTS EventInvitation(event INTEGER, recipient TEXT, " +
                "message TEXT, FOREIGN KEY (event) REFERENCES Events(e_id), FOREIGN KEY (recipient) REFERENCES Users(username), " +
                "PRIMARY KEY (event, recipient));";

        executeSQLStatement(createUsers);
        executeSQLStatement(createClubs);
        executeSQLStatement(createPositions);
        executeSQLStatement(createMembers);
        executeSQLStatement(createEvents);
        executeSQLStatement(createEventAttendance);
        executeSQLStatement(createEventInvitation);

        System.out.println("Created table");
    }

    private void addUser(String username, String firstName, String lastName, String phone, String password, boolean isAdvisor, boolean isStudent) {
        final String sqlStatement = "INSERT INTO Users(username, first_name, last_name, phone, password, is_advisor, is_student) " +
                "VALUES ('" + username + "', '" + firstName + "', '" + lastName + "', '" + phone + "', '" + password + "', " +
                (isAdvisor ? 1 : 0) + ", " + (isStudent ? 1 : 0) + ");";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Adds a student to the database.
     * @param username The username of the student.
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param phone The phone number of the student.
     * @param password The password of the student.
     */
    public void addStudent(String username, String firstName, String lastName, String phone, String password) {
        addUser(username, firstName, lastName, phone, password, false, true);
    }

    /**
     * Adds an advisor to the database.
     * @param username The username of the advisor.
     * @param firstName The first name of the advisor.
     * @param lastName The last name of the advisor.
     * @param phone The phone number of the advisor.
     * @param password The password of the advisor.
     */
    public void addAdvisor(String username, String firstName, String lastName, String phone, String password) {
        addUser(username, firstName, lastName, phone, password, true, false);
    }

    /**
     * Adds a club to the database.
     * @param name The name of the club.
     * @param advisor The username of the advisor of the club.
     */
    public void addClub(String name, String advisor) {
        final String sqlStatement = "INSERT INTO Clubs(name, advisor) VALUES ('" + name + "', '" + advisor + "');";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Adds a position of a club to the database. Note that the club must already exist in the database.
     * @param club The name of the club.
     * @param position The name of the position.
     */
    public void addPosition(String club, String position) {
        final String sqlStatement = "INSERT INTO Positions(club, position) VALUES ('" + club + "', '" + position + "');";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Adds a member of a club to the database. Note that the student and club must already exist in the database.
     * @param club The name of the club.
     * @param student The username of the student.
     * @param startDate The start date of the membership.
     * @param endDate The end date of the membership.
     * @param position The position of the member.
     */
    public void addMember(String club, String student, int startDate, int endDate, String position) {
        final String sqlStatement = "INSERT INTO Members(club, student, start_date, end_date, position) " +
                "VALUES ('" + club + "', '" + student + "', " + startDate + ", " + endDate + ", '" + position + "');";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Adds an event to the database.
     * @param title The title of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    public void addEvent(String club, String title,int startDate, int endDate, int startTime, int endTime) {
        final String sqlStatement = "INSERT INTO Events(club, title, start_date, end_date, start_time, end_time) " +
                "VALUES ('" + club + "', " + title + "', " + startDate + ", " + endDate + ", " + startTime + ", " + endTime + ");";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Marks a user as attending an event by adding a record to the EventAttendance table.
     * @param event_id The ID of the event to mark attendance for.
     * @param participant The username of the user to mark as attending.
     */
    public void markAttendance(int event_id, String participant) {
        final String sqlStatement = "INSERT INTO EventAttendance(event, participant) VALUES ("
                + event_id + ", '" + participant + "');";
        executeSQLStatement(sqlStatement);
    }

    /**
     * Marks a user as invited to an event by adding a record to the EventInvitation table.
     * @param event_id The ID of the event to mark attendance for.
     * @param recipient The username of the user to mark as invited.
     * @param message The message to send to the user.
     */
    public void markInvitation(int event_id, String recipient, String message) {
        final String sqlStatement = "INSERT INTO EventInvitation(event, recipient, message) VALUES (" +
                event_id + ", '" + recipient + "', '" + message + "');";
        executeSQLStatement(sqlStatement);
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

CREATE TABLE EventInvitation(
event INTEGER,
recipient TEXT,
message TEXT,
FOREIGN KEY (event) REFERENCES Events(e_id),
FOREIGN KEY (recipient) REFERENCES Users(username),
PRIMARY KEY (event, recipient)
);

* */
