package com.sacms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdvisorDashboard {
    // Club title panel buttons and labels
    @FXML private Button btn_createEvent;
    @FXML private Button btn_generateClubReport;
    @FXML private Label lbl_clubName;

    // Event panel buttons
    @FXML private Button btn_addAttendance;

    // Event panel labels
    @FXML private Label lbl_endDate;
    @FXML private Label lbl_endTime;
    @FXML private Label lbl_eventTitle;
    @FXML private Label lbl_startDate;
    @FXML private Label lbl_startTime;

    // Event list
    @FXML private ListView<?> lst_prevEvents;
    @FXML private ListView<?> lst_upcomingEvents;

    // Menu bar
    @FXML private MenuItem menu_close;
    @FXML private MenuItem menu_newClub;
    @FXML private MenuItem menu_signOut;
    @FXML private MenuItem menu_switchClub;

    // Attendance table
    @FXML private TableView<?> tbl_attendance;
    @FXML private TableColumn<?, ?> tcol_firstName;
    @FXML private TableColumn<?, ?> tcol_lastName;
    @FXML private TableColumn<?, ?> tcol_phoneNumber;
    @FXML private TableColumn<?, ?> tcol_studentId;

    @FXML
    void onBtnAddAttendance(ActionEvent event) {

    }

    @FXML
    void onBtnCreateEvent(ActionEvent event) {

    }

    @FXML
    void onBtnGenerateClubReport(ActionEvent event) {

    }

    @FXML
    void onMenuCloseClick(ActionEvent event) {

    }

    @FXML
    void onMenuNewClubClick(ActionEvent event) {

    }

    @FXML
    void onMenuSignOutClick(ActionEvent event) {

    }

    @FXML
    void onMenuSwitchClubClick(ActionEvent event) {

    }

}
