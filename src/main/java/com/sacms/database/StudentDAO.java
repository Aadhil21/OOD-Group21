package com.sacms.database;

import com.sacms.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The StudentDAO class is a DAO class for the Student model.
 */
public class StudentDAO extends UserDAO<Student> {
    private static final DBManager dbManager = DBManager.getInstance();

    StudentDAO() {}

    @Override
    public void createTable() {
        final String createStudent = "CREATE TABLE IF NOT EXISTS Student(uid INTEGER PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, " +
                "phone TEXT NOT NULL,email TEXT NOT NULL, password TEXT NOT NULL);";
        dbManager.executeSQLStatement(createStudent);
    }

    @Override
    public Student create(Student student){
        final int username = student.getUid();
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final String phone = student.getPhone();
        final String email = student.getEmail();
        final String password = student.getPassword();

        final String sqlStatement = String.format("INSERT INTO Student(uid, first_name, last_name, phone, email, password) " + "VALUES (%d, '%s', '%s', '%s','%s', '%s');", username, firstName, lastName, phone, email, password);

        dbManager.executeSQLStatement(sqlStatement);
        System.out.println("inserted student");
        return student;
    }

    public static Student read(int uid) {
        final String sqlStatement = String.format("SELECT * FROM Student WHERE uid = %d;", uid);
        System.out.println("Before executing query");

        try (DBManager.ResultContainer resultContainer = dbManager.executeSQLQuery(sqlStatement)) {
            ResultSet resultSet = resultContainer.resultSet;

            System.out.println("Query executed");

            if (resultSet.next()) {
                // Read data from the ResultSet and create a Student object
                int userID = resultSet.getInt("uid");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");

                Student student = new Student(userID, fname, lname, phone, email, pass);
                System.out.println("Student created");
                return student;
            } else {
                System.out.println("No data found for the given UID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void update(Student student) {

    }

    @Override
    public void delete(Student student) {

    }
}
