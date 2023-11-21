package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.Advisor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class AdvisorDashboard {
    @FXML private VBox vbox_noClubView;
    @FXML private VBox vbox_clubAdvisorView;

    // No club yet view
    @FXML private Label lbl_noClubView_username;
    @FXML private Button btn_noClubView_createClub;

    // Club title panel buttons and labels
    @FXML private Button btn_createEvent;
    @FXML private Button btn_generateClubReport;
    @FXML private Label lbl_clubName;

    @FXML private VBox vbox_events_noEventMsgView;
    @FXML private VBox vbox_events_eventView;

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

    private final LoginManager loginManager;

    public AdvisorDashboard() {
        loginManager = LoginManager.getInstance();
    }
    public void initialize() {
        System.out.println("AdvisorDashboard initialized");
        Advisor advisor = (Advisor) loginManager.getCurrentUser();

        if (advisor.getClubs().isEmpty()) {
            vbox_noClubView.setVisible(true);
            vbox_clubAdvisorView.setVisible(false);
        } else {
            vbox_noClubView.setVisible(false);
            vbox_clubAdvisorView.setVisible(true);
        }
    }

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

    @FXML
    void onNoClubViewCreateClub(ActionEvent event) {

    }
}
