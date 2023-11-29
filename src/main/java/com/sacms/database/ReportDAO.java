package com.sacms.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportDAO {
    private static final DBManager dbManager = DBManager.getInstance();

    public ResultSet GetMembershipReport(String ClubName) throws SQLException {
        final String getMembers = String.format("SELECT S.uid, S.first_name, S.last_name, S.email, S.phone FROM Members M JOIN Student S ON M.student = S.uid WHERE M.club = '%s'", ClubName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers).resultSet;
        return resultSet;
    }

    public ResultSet GetEvents(String ClubName) throws SQLException {
        final String getMembers = String.format("SELECT title FROM Events WHERE club = '%s'", ClubName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers).resultSet;
        return resultSet;
    }

    public ResultSet GetAttendanceReport(String eventName) throws SQLException {
        final String getMembers = String.format("SELECT student FROM EventAttendance WHERE title = %s", eventName);
        ResultSet resultSet = dbManager.executeSQLQuery(getMembers).resultSet;
        return resultSet;
    }
}
