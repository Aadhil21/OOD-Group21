package com.sacms.database;

import com.sacms.models.Advisor;

public class AdvisorDAO extends UserDAO<Advisor> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void create(Advisor advisor) {
        final int username = advisor.getUid();
        final String password = advisor.getPassword();
        final String firstName = advisor.getFirstName();
        final String lastName = advisor.getLastName();
        final String phone = advisor.getPhone();

        final String sqlStatement = String.format(
            "INSERT INTO Users(uid, first_name, last_name, phone, password, role) " +
            "VALUES (%d, '%s', '%s', '%s', '%s', 'advisor');",
            username, firstName, lastName, phone, password
        );

        dbManager.executeSQLStatement(sqlStatement);
    }
}
