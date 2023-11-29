package com.sacms.util;

import com.sacms.database.DAOFactory;
import com.sacms.database.ReportDAO;
import com.sacms.database.StudentDAO;
import com.sacms.models.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Report {

    ReportDAO reportDAO = new ReportDAO();

    public void ClubMembershipReport(String clubName) throws SQLException {
        String excelFilePath = "Report/" + clubName + "MembershipReport" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetMembershipReport(clubName);
        CreateExcel(resultSet, excelFilePath);
    }

    public void ClubActivityReport(String clubName) throws SQLException {
        String excelFilePath = "Report/" + clubName + "ClubEvents" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetEvents(clubName);
        CreateExcel(resultSet, excelFilePath);
    }

    public void EventAttendanceReport(String eventName) throws SQLException {
        String excelFilePath = "Report/" + eventName + "EventAttendance" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = reportDAO.GetAttendanceReport(eventName);
        CreateExcel(resultSet, excelFilePath);
    }

    public static void CreateExcel(ResultSet resultSet, String excelFilePath) {

        try {
            // Create a new Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet 1");

            // Get metadata to fetch column names
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Create headers in Excel sheet
            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                headerRow.createCell(i - 1).setCellValue(columnName);
            }

            // Populate data rows in Excel sheet
            int rowNum = 1;
            while (resultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    Cell cell = row.createCell(i - 1);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } // Add other data types as needed
                }
            }

            // Write data to Excel file
            try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file created successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
