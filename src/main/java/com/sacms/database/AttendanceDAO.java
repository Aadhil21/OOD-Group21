package com.sacms.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sacms.models.Event;
import com.sacms.models.EventAttendee;
import com.sacms.models.Student;

/**
 * This class is responsible for handling database operations for the EventAttendance table.
 */
public class AttendanceDAO implements DAO<EventAttendee> {
    private static final DBManager dbManager = DBManager.getInstance();

    @Override
    public void createTable() {
        final String createEventAttendance = "CREATE TABLE IF NOT EXISTS EventAttendance(event INTEGER, participant INTEGER, " +
                "FOREIGN KEY (event) REFERENCES Events(e_id), FOREIGN KEY (participant) REFERENCES Users(uid), " +
                "PRIMARY KEY (event, participant));";
        dbManager.executeSQLStatement(createEventAttendance);
    }

    @Override
    public EventAttendee create(EventAttendee attendee) {
        final int event = attendee.event().getId();
        final int student = attendee.student().getUid();

        final String sqlStatement = String.format(
                "INSERT INTO EventAttendance(event, participant) VALUES (%d, %d);",
                event, student
        );

        dbManager.executeSQLStatement(sqlStatement);
        return null;
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

    public List<Student> getAttendedStudents(Event event) {
        final int eventId = event.getId();
        final String sqlStatement = String.format(
                "SELECT participant FROM EventAttendance WHERE event = %d;",
                eventId
        );

        try (ResultSet resultSet = dbManager.executeQuery(sqlStatement)) {
            List<Student> attendedStudents = new ArrayList<>();

            while (resultSet.next()) {
                int studentId = resultSet.getInt("participant");
                // Assuming you have a StudentDAO class with a read method
                Student attendedStudent = StudentDAO.read(studentId);
                attendedStudents.add(attendedStudent);
            }

            return attendedStudents;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception based on your application's needs
            return new ArrayList<>(); // Return an empty list or handle the error accordingly
        }
    }
}
