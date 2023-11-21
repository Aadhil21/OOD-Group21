package com.sacms.models;

import com.sacms.database.ClubDAO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Club {
    static ClubDAO clubDAO = new ClubDAO();

    private String name;
    private Advisor advisor;


    public Club(String name, Advisor advisor) {
        this.name = name;
        this.advisor = advisor;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void ClubMembership() {
//        club membership, event attendance, and club activities

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = currentDateTime.format(formatter);

        String excelFilePath = this.name + "MembershipReport"+ formattedDateTime +".xlsx";

        try {
            ResultSet resultSet= clubDAO.GetMembershipReport(this.name);

            // Create a new Excel workbook and sheet
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Members");

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
