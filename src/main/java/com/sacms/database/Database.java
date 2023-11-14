package com.sacms.database;

import java.sql.*;

public class Database {
    private static final String url = "jdbc:sqlite:database/SACMS_Database.db";

    public static boolean loadDBDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error loading driver: " + e);
            return false;
        }
    }

    public static Connection getConnection(){
        try(Connection connection = DriverManager.getConnection(url)){
            return connection;
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                CreateStudentTable(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void CreateStudentTable(Connection conn) {

        try (Connection connection = conn;
             Statement statement = connection.createStatement()) {

            // Define your SQL CREATE TABLE statement
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                                    "id INTEGER PRIMARY KEY," +
                                    "name VARCHAR NOT NULL," +
                                    "password VARCHAR NOT NULL)";

            // Execute the CREATE TABLE statement
            statement.execute(createTableSQL);

            System.out.println("Table 'students' created successfully.");

        } catch (SQLException e) {
            System.err.println("Table creation error: " + e.getMessage());
        }
    }
}
