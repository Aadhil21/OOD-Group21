package com.sacms.database;

import com.sacms.util.AppDataDir;

import java.sql.*;

public class DBManager {
    private static DBManager instance = null;
    private final String url;

    private DBManager() {
        url = "jdbc:sqlite:" + AppDataDir.getAppDataDir() + "/scam-ood-cw-test-2.db";
    }

    /**
     * Returns the singleton instance of the DBManager.
     * @return The singleton instance of the DBManager.
     */
    static DBManager getInstance() {
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
     * @return The {@link ResultContainer} containing the {#{@link ResultSet} of the executed query.
     * @throws SQLException If an error occurred while executing the query.
     * @see ResultContainer
     */
    ResultContainer executeSQLQuery(String sqlStatement) throws SQLException {
        Connection con = getConnection();
        if (con == null) return null;
        Statement stmt =  con.createStatement();
        return new ResultContainer(stmt.executeQuery(sqlStatement));
    }

    /**
     * Executes an SQL statement. If a key was generated automatically, it will be returned.
     *
     * @param sqlStatement The SQL statement to execute.
     * @return The key generated by the database, or -1 if no key was generated. -2 if an error occurred.
     */
     int executeSQLStatement(String sqlStatement) {
        Connection con = getConnection();
        if (con == null) return -2;

        int key = -1;
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sqlStatement);
            stmt.close();
            key = getGeneratedKey(con);
            con.close();
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement: " + e);
        }

        return key;
    }

    private int getGeneratedKey(Connection connection) throws SQLException {
        if (connection == null) return -1;
        Statement stmt =  connection.createStatement();
        final int key = stmt.executeQuery("SELECT last_insert_rowid();").getInt(1);
        stmt.close();
        return key;
    }

    /**
     * A wrapper class to expose the {@link ResultSet} and close the resources when done.
     * This class has methods to close the {@link ResultSet} and the resources used by it,
     * {@link Statement} and {@link Connection}. This class implements {@link AutoCloseable}
     * allowing to use it in a try-with-resources block.
     */
    static class ResultContainer implements AutoCloseable {
        final ResultSet resultSet;
        ResultContainer(ResultSet rs) {
            this.resultSet = rs;
        }

        @Override
        public void close() {
            try {
                Statement stmt = resultSet.getStatement();
                Connection con = stmt.getConnection();

                resultSet.close();
                stmt.close();
                if (con != null && !con.isClosed()) con.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
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
