package com.sacms.database;

import com.sacms.models.Advisor;
import com.sacms.models.Club;

import java.sql.ResultSet;

/**
 * This class is responsible for handling all database operations related to the Club model.
 */
public class ClubDAO implements DAO<Club>{
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void createTable(){
        final String createClubs = "CREATE TABLE IF NOT EXISTS Clubs(name TEXT PRIMARY KEY, advisor INTEGER NOT NULL, " +
            "FOREIGN KEY (advisor) REFERENCES Advisor(uid));";
        dbManager.executeSQLStatement(createClubs);
    }

    @Override
    public void create(Club club) {
        final String name = club.getName();
        final int advisorId = club.getAdvisor().getUid();

        final String sqlStatement = String.format(
            "INSERT INTO Clubs(name, description) VALUES ('%s', '%d');",
            name, advisorId
        );

        dbManager.executeSQLStatement(sqlStatement);
    }

    @Override
    public Club read(int i) {
        return null;
    }

    @Override
    public void update(Club club) {
    }

    @Override
    public void delete(Club club) {
    }

    public ResultSet GetMembershipReport(String ClubName){
        final String getMembers = String.format("SELECT student FROM Members WHERE club = %s",ClubName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers);
        return resultSet;
    }

    public static ResultSet GetEvents(String ClubName){
        final String getMembers = String.format("SELECT title FROM Events WHERE club = %s",ClubName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers);
        return resultSet;
    }
}
