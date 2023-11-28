package com.sacms.database;

import com.sacms.models.Club;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
    public Club create(Club club) {
        final String name = club.getName();
        final int advisorId = club.getAdvisor().getUid();

        final String sqlStatement = String.format(
            "INSERT INTO Clubs(name, description) VALUES ('%s', '%d');",
            name, advisorId
        );

        dbManager.executeSQLStatement(sqlStatement);
        return null;
    }

    public List<Club> read() {

        return null;
    }

    @Override
    public void update(Club club) {
    }

    @Override
    public void delete(Club club) {
    }

}
