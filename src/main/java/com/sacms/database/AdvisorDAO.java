package com.sacms.database;

import com.sacms.models.Advisor;
import com.sacms.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdvisorDAO extends UserDAO<Advisor> {

    private static final DBManager dbManager = DBManager.getInstance();
    private static AdvisorDAO instance = null;

    private AdvisorDAO() {
    }

    // Method to get the singleton instance of AdvisorDAO
    public static synchronized AdvisorDAO getInstance() {

        if (instance == null) {
            instance = new AdvisorDAO();
        }
        return instance;
    }

    @Override
    public void createTable() {
        final String createSql = "CREATE TABLE IF NOT EXISTS Advisor(uid INTEGER PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, " +
                "phone TEXT NOT NULL,email TEXT NOT NULL, password TEXT NOT NULL);";
        dbManager.executeSQLStatement(createSql);
    }

    /**
     * Inserts a new {@link Advisor} into the database.
     *
     * @param advisor The {@link Advisor} to be inserted.
     */
    @Override
    public void create(Advisor advisor) {
        final int username = advisor.getUid();
        final String firstName = advisor.getFirstName();
        final String lastName = advisor.getLastName();
        final String phone = advisor.getPhone();
        final String email = advisor.getEmail();
        final String password = advisor.getPassword();

        final String sqlStatement = String.format("INSERT INTO advisor(uid, first_name, last_name, phone, email, password) " + "VALUES (%d, '%s', '%s', '%s','%s', '%s');", username, firstName, lastName, phone, email, password);

        dbManager.executeSQLStatement(sqlStatement);
    }

    @Override
    public Advisor read(int uid) {
        final String sqlStatement = String.format("SELECT * FROM Advisor WHERE uid = %d;", uid);
        System.out.println("Before executing query");

        try {
            ResultSet resultSet = dbManager.executeSQLQuery(sqlStatement);
            System.out.println("Query executed");

            if (resultSet.next()) {
                // Read data from the ResultSet and create an Advisor object
                int userID = resultSet.getInt("uid");
                String fname = resultSet.getString("first_name");
                String lname = resultSet.getString("last_name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");

                Advisor advisor = new Advisor(userID, fname, lname, phone, email, pass);
                System.out.println("Advisor created");
                return advisor;
            } else {
                System.out.println("No data found for the given UID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Advisor advisor) {

    }

    @Override
    public void delete(Advisor advisor) {

    }
}
