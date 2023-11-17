package com.sacms.database;

import com.sacms.models.ClubMembership;

public class ClubMembershipDAO implements DAO<ClubMembership> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void createTable() {
        final String sqlStatement = "CREATE TABLE Members(club TEXT, student INTEGER FOREIGN KEY (club) REFERENCES Clubs(name), " +
                "FOREIGN KEY (student) REFERENCES Users(uid), PRIMARY KEY (club, student));";
        dbManager.executeSQLStatement(sqlStatement);
    }

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
