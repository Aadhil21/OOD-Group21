package com.sacms.database;

import com.sacms.models.ClubMembership;

/**
 * This class is responsible for handling the database operations for the ClubMembership model.
 */
public class ClubMembershipDAO implements DAO<ClubMembership> {
    private static final DBManager dbManager = DBManager.getInstance();

    /**
     * Creates the table for the ClubMembership model if it's not already exist.
     */
    @Override
    public void createTable() {
        final String sqlStatement = "CREATE TABLE IF NOT EXISTS Members(club TEXT, student INTEGER, " +
                "FOREIGN KEY (club) REFERENCES Clubs(name), FOREIGN KEY (student) REFERENCES Users(uid), " +
                "PRIMARY KEY (club, student));";
        dbManager.executeSQLStatement(sqlStatement);
    }

    /**
     * Inserts a new {@link ClubMembership} record into the database.
     * @param membership The {@link ClubMembership} object to be inserted.
     */
    @Override
    public void create(ClubMembership membership) {
        final String club = membership.club().getName();
        final int student = membership.student().getUid();

        final String sqlStatement = String.format(
            "INSERT INTO Members(club, student) VALUES ('%s', %d);",
            club, student
        );

        dbManager.executeSQLStatement(sqlStatement);
    }
}
