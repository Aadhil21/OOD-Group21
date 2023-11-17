package com.sacms.database;

import com.sacms.models.Advisor;
import com.sacms.models.Club;

/**
 * This class is responsible for handling all database operations related to the Club model.
 */
public class ClubDAO implements DAO<Club>{
    private static final DBManager dbManager = DBManager.getInstance();

    /**
     * Creates the Clubs table in the database if it does not already exist.
     */
    @Override
    public void createTable() {
        final String sqlStatement = "CREATE TABLE IF NOT EXISTS Clubs(name TEXT PRIMARY KEY, advisor INTEGER NOT NULL, " +
                "FOREIGN KEY (advisor) REFERENCES users(uid));";
        dbManager.executeSQLStatement(sqlStatement);
    }

    /**
     * Creates a new {@link Club} in the database.
     * @param club The {@link Club} to be created.
     */
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
}
