package com.sacms.database;

import com.sacms.models.Student;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

public class StudentDAO extends UserDAO<Student> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void create(Student student) {
        final int username = student.getUid();
        final String password = student.getPassword();
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final String phone = student.getPhone();
        final LocalDate birthday = student.getBirthday();

        final long birthday_epoch = birthday.toEpochSecond(LocalTime.MIDNIGHT, ZoneOffset.UTC);

        final String sqlStatement = String.format(
            "INSERT INTO Users(uid, first_name, last_name, phone, password, birthday, role) " +
            "VALUES (%d, '%s', '%s', '%s', '%s', '%s', 'student');",
            username, firstName, lastName, phone, password, birthday_epoch
        );

        dbManager.executeSQLStatement(sqlStatement);
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

