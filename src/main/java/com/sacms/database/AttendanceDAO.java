package com.sacms.database;

import com.sacms.models.EventAttendee;

/**
 * This class is responsible for handling database operations for the EventAttendance table.
 */
public class AttendanceDAO implements DAO<EventAttendee> {
    private static final DBManager dbManager = DBManager.getInstance();

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

    @Override
    public EventAttendee read(int i) {
        return null;
    }

    @Override
    public void update(EventAttendee eventAttendee) {

    }

    @Override
    public void delete(EventAttendee eventAttendee) {

    }
}
