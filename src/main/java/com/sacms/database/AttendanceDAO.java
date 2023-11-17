package com.sacms.database;

import com.sacms.models.EventAttendee;

/**
 * This class is responsible for handling database operations for the EventAttendance table.
 */
public class AttendanceDAO implements DAO<EventAttendee> {
    private static final DBManager dbManager = DBManager.getInstance();

    /**
     * Creates the EventAttendance table in the database if it does not exist.
     */
    @Override
    public void createTable() {
        final String sqlStatement = "CREATE TABLE IF NOT EXISTS EventAttendance(event INTEGER, participant INTEGER, " +
                "FOREIGN KEY (event) REFERENCES Events(e_id), FOREIGN KEY (participant) REFERENCES Users(uid), " +
                "PRIMARY KEY (event, participant));";
        dbManager.executeSQLStatement(sqlStatement);
    }

    /**
     * Creates a new {@link EventAttendee} entry in the database.
     * @param attendee The {@link EventAttendee} object to be added to the database.
     */
    @Override
    public void create(EventAttendee attendee) {
        final int event = attendee.event().getId();
        final int student = attendee.student().getUid();

        final String sqlStatement = String.format(
            "INSERT INTO EventAttendance(event, participant) VALUES (%d, %d);",
            event, student
        );

        dbManager.executeSQLStatement(sqlStatement);
    }
}
