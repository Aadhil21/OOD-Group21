package com.sacms.database;

import com.sacms.models.Club;
import com.sacms.models.Event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

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

    public List<Event> getEventsByClub(Club club) {
        final String clubName = club.getName();
        final List<Event> events = new ArrayList<>();

        final String sqlStatement = String.format(
            "SELECT * FROM Events WHERE club = '%s';",
            clubName
        );

        try (ResultSet resultSet = dbManager.executeSQLQuery(sqlStatement)) {
            while (resultSet.next()) {
                // Read data from the ResultSet and create an Event object
                int key = resultSet.getInt("e_id");
                String title = resultSet.getString("title");
                long start = resultSet.getLong("start");
                long end = resultSet.getLong("end");

                // Converting epoch seconds to LocalDateTime
                LocalDateTime startDate = LocalDateTime.ofEpochSecond(start, 0, ZoneOffset.UTC);
                LocalDateTime endDate = LocalDateTime.ofEpochSecond(end, 0, ZoneOffset.UTC);

                // Extracting date and time from LocalDateTime
                LocalDate eventStartDate = startDate.toLocalDate();
                LocalTime eventStartTime = startDate.toLocalTime();
                LocalDate eventEndDate = endDate.toLocalDate();
                LocalTime eventEndTime = endDate.toLocalTime();

                Event event = new Event(key, club, title, eventStartDate, eventEndDate, eventStartTime, eventEndTime);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return events;
    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void delete(Event event) {

    }
}
