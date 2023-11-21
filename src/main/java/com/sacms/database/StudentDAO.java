package com.sacms.database;

import com.sacms.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The StudentDAO class is a DAO class for the Student model.
 */
public class StudentDAO extends UserDAO<Student> {
    private static final DBManager dbManager = DBManager.getInstance();
    private static StudentDAO instance = null;

    private StudentDAO() {
    }

    // Method to get the singleton instance of AdvisorDAO
    public static synchronized StudentDAO getInstance() {

        if (instance == null) {
            instance = new StudentDAO();
        }
        return instance;
    }

    @Override
    public void create(Student student) {
        final int username = student.getUid();
        final String firstName = student.getFirstName();
        final String lastName = student.getLastName();
        final String phone = student.getPhone();
        final String email = student.getEmail();
        final String password = student.getPassword();

        final String sqlStatement = String.format("INSERT INTO Student(uid, first_name, last_name, phone, email, password) " + "VALUES (%d, '%s', '%s', '%s','%s', '%s');", username, firstName, lastName, phone, email, password);

        dbManager.executeSQLStatement(sqlStatement);
        System.out.println("inserted student");

    }

    @Override
    public Student read(int uid) {
        final String sqlStatement = String.format("SELECT * FROM Student WHERE uid = %d;", uid);
        System.out.println("Before executing query");

        try {
            ResultSet resultSet = dbManager.executeSQLQuery(sqlStatement);
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
