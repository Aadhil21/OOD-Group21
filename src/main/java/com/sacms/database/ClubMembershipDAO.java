package com.sacms.database;

import com.sacms.models.ClubMembership;

/**
 * This class is responsible for handling the database operations for the ClubMembership model.
 */
public class ClubMembershipDAO implements DAO<ClubMembership> {
    private static final DBManager dbManager = DBManager.getInstance();


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

    @Override
    public ClubMembership read(int i) {
        return null;
    }

    @Override
    public void update(ClubMembership clubMembership) {

    }

    @Override
    public void delete(ClubMembership clubMembership) {

    }
}
