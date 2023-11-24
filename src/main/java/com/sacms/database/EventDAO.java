package com.sacms.database;

import com.sacms.models.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;

/**
 * This class is responsible for handling all database operations related to the Events table.
 */
public class EventDAO implements DAO<Event> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void createTable() {
        final String createEvents = "CREATE TABLE IF NOT EXISTS Events(e_id INTEGER PRIMARY KEY, club TEXT NOT NULL, " +
            "title TEXT NOT NULL, start INTEGER NOT NULL, end INTEGER NOT NULL, FOREIGN KEY (club) REFERENCES Clubs(name));";
        dbManager.executeSQLStatement(createEvents);
    }

    /**
     * Inserts a new {@link Event} into the database. The {@link Event} object passed in is not modified.
     * The key of the {@link Event} object passed will be ignored. Therefore, when creating a new event, set the
     * key to an arbitrary value.
     *
     * @param event The {@link Event} to be inserted.
     * @return The {@link Event} object with the generated key.
     */
    public Event create(Event event) {
        final String club = event.getClub().getName();
        final String title = event.getTitle();
        final LocalDate startDate = event.getStartDate();
        final LocalDate endDate = event.getEndDate();
        final LocalTime startTime = event.getStartTime();
        final LocalTime endTime = event.getEndTime();

        // Combining date and time into a single LocalDateTime object.
        final LocalDateTime eventStart = startDate.atTime(startTime);
        final LocalDateTime eventEnd = endDate.atTime(endTime);

        // Converting LocalDateTime to epoch seconds.
        final long start = eventStart.toEpochSecond(ZoneOffset.UTC);
        final long end = eventEnd.toEpochSecond(ZoneOffset.UTC);

        final String sqlStatement = String.format(
            "INSERT INTO Events(club, title, start, end) " +
            "VALUES ('%s', '%s', '%d', '%d');",
            club, title, start, end
        );

        int key = dbManager.executeSQLStatement(sqlStatement);
        return new Event(key, event);
    }

    public Event read(int i) {
        return null;
    }



    @Override
    public void update(Event event) {

    }

    @Override
    public void delete(Event event) {

    }
}
