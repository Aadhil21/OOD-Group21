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

    public void create(Event event) {
        final int id = event.getId();
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
            "INSERT INTO Events(e_id, club, title, start, end) " +
            "VALUES (%d, '%s', '%s', '%d', '%d');",
            id, club, title, start, end
        );

        dbManager.executeSQLStatement(sqlStatement);
    }

    @Override
    public Event read(int i) {
        return null;
    }



    @Override
    public void update(Event event) {

    }

    @Override
    public void delete(Event event) {

    }
    public static ResultSet GetAttendanceReport(String eventName) throws SQLException {
        final String getMembers = String.format("SELECT student FROM EventAttendance WHERE title = %s",eventName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers);
        return resultSet;
    }
}
