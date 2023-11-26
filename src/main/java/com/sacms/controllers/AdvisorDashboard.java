package com.sacms.controllers;

import com.sacms.NewEventDialog;
import com.sacms.database.LoginManager;
import com.sacms.models.Advisor;
import com.sacms.models.Club;
import com.sacms.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

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
    @FXML private ListView<Event> lst_events;

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
    private Event selectedEvent;
    private Club currentClub;

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
            currentClub = advisor.getClubs().get(0);
        }

        refreshAdvisorView();
        setSelectedEvent(null);
    }

    private void setCurrentClub(Club club) {
        currentClub = club;
        refreshAdvisorView();
    }

    private void refreshAdvisorView() {
        if (currentClub == null) {
            vbox_noClubView.setVisible(true);
            vbox_clubAdvisorView.setVisible(false);
        } else {
            vbox_noClubView.setVisible(false);
            vbox_clubAdvisorView.setVisible(true);
            lbl_clubName.setText(currentClub.getName());
        }
    }

    private void setSelectedEvent(Event event) {
        selectedEvent = event;
        refreshEventPane();
    }

    private Event getSelectedEvent() {
        return selectedEvent;
    }

    private void refreshEventPane() {
        Event event = getSelectedEvent();
        if (event == null) {
            vbox_events_noEventMsgView.setVisible(true);
            vbox_events_eventView.setVisible(false);
        } else {
            vbox_events_noEventMsgView.setVisible(false);
            vbox_events_eventView.setVisible(true);
            lbl_eventTitle.setText(event.getTitle());
            lbl_startDate.setText(event.getStartDate().toString());
            lbl_startTime.setText(event.getStartTime().toString());
            lbl_endDate.setText(event.getEndDate().toString());
            lbl_endTime.setText(event.getEndTime().toString());
        }
    }

    @FXML
    void onBtnAddAttendance(ActionEvent event) {

    }

    @FXML
    void onBtnCreateEvent(ActionEvent event) {
        Window window = btn_createEvent.getScene().getWindow();
        NewEventDialog newEventDialog = new NewEventDialog(window, currentClub, currentClub.getAllEvents());
        Event newEvent = newEventDialog.showAndWait();
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
