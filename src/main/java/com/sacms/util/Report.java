package com.sacms.util;

import com.sacms.database.ReportDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report {

    ReportDAO reportDAO = new ReportDAO();

    public void ClubMembership(String clubName) throws SQLException {
        String excelFilePath = "Report/" + clubName + "MembershipReport" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetMembershipReport(clubName);
        CreateReport.CreateExcel(resultSet, excelFilePath);
    }

    public void ClubActivity(String clubName) throws SQLException {
        String excelFilePath = "Report/" + clubName + "ClubEvents" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetEvents(clubName);
        CreateReport.CreateExcel(resultSet, excelFilePath);
    }

    public void EventAttendance(String eventName) throws SQLException {
        String excelFilePath = "Report/" + eventName + "EventAttendance" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetAttendanceReport(eventName);
        CreateReport.CreateExcel(resultSet, excelFilePath);
    }
}
