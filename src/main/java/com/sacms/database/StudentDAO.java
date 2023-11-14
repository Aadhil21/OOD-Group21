package com.sacms.database;

import javafx.css.StyleConverter;
import javafx.fxml.Initializable;

import java.sql.*;

public class StudentDAO{

    public static void create(int studentId, String studentName,String studentPassword) {
        Connection connection = Database.getConnection();

        try (connection) {
            // Define the SQL INSERT statement
            String insertSQL = "INSERT INTO students (id, name, password) VALUES (?, ?, ?)";

            // Create a PreparedStatement to execute the INSERT statement
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                // Set values for the parameters in the INSERT statement
                preparedStatement.setInt(1, studentId);
                preparedStatement.setString(2, studentName);
                preparedStatement.setString(3, studentPassword);

                // Execute the INSERT statement
                preparedStatement.executeUpdate();

                System.out.println("Student data inserted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Data insertion error: " + e.getMessage());
        }
    }

    public static void retrieve() {
        Connection connection = Database.getConnection();

        try (connection) {
            // Define the SQL INSERT statement
            String insertSQL = "SELECT * FROM 'students'";

            // Create a PreparedStatement to execute the INSERT statement
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                ResultSet response = preparedStatement.executeQuery();

                // loop through the result set
                while (response.next()) {
                    System.out.println(response.getInt("id") + "\t" +
                            response.getString("name") + "\t" +
                            response.getDouble("password"));
                }

                System.out.println("Student data extracted successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Data extraction error: " + e.getMessage());
        }
    }

    public static void update() {

    }

    public static void delete() {

    }
}

