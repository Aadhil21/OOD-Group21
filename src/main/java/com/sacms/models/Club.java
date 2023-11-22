package com.sacms.models;

import com.sacms.database.AttendanceDAO;
import com.sacms.database.ClubDAO;
import com.sacms.database.EventDAO;
import com.sacms.util.CreateReport;
import com.sacms.util.DateTimeUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

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
        String excelFilePath = "Report/" + this.name + "MembershipReport" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = clubDAO.GetMembershipReport(this.name);
        CreateReport.CreateExcel(resultSet,excelFilePath);
    }

    public void ClubActivity(){
        String excelFilePath = "Report/" + this.name + "ClubEvents" + DateTimeUtils.getDateTime() + ".xlsx";
        ResultSet resultSet = ClubDAO.GetEvents(this.name);
        CreateReport.CreateExcel(resultSet,excelFilePath);
    }
}
