package com.sacms.database;

import com.sacms.models.EventAttendee;
import com.sacms.models.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * The StudentDAO class is a DAO class for the Student model.
 */
public class StudentDAO extends UserDAO<Student> {
    private static final DBManager dbManager = DBManager.getInstance();

    /**
     * Insert a new {@link Student} into the database.
     * @param student The {@link Student} to insert.
     */
    @Override
    public void create(Student student) {
        final int username = student.getUid();
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final String phone = student.getPhone();
        final String password = student.getPassword();
        final LocalDate birthday = student.getBirthday();

        final long birthdayEpoch = birthday.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        final String sqlStatement = String.format(
                "INSERT INTO Users(uid, first_name, last_name, phone, password, birthday, role) " +
                        "VALUES (%d, '%s', '%s', '%s', '%s', %d, 'student');",
                username, firstName, lastName, phone, password, birthdayEpoch
        );

        dbManager.executeSQLStatement(sqlStatement);
    }

    public Student retrieveStudentByID(int uid) {
        Student student = null;

        final String selectQuery = String.format("SELECT * FROM Users WHERE role = 'student' AND uid = %d", uid);

        try (ResultSet resultSet = dbManager.executeSQLQuery(selectQuery)) {

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                long birthdayEpoch = resultSet.getLong("birthday");

                // Convert epoch time to LocalDate
                LocalDate birthday = LocalDateTime.ofEpochSecond(birthdayEpoch, 0, ZoneOffset.UTC).toLocalDate();

                student = new Student(uid, password, firstName, lastName, phone, birthday);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
