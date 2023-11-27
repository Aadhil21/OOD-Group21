package com.sacms.controllers;

import com.sacms.EventAttendanceDialog;
import com.sacms.NewEventDialog;
import com.sacms.database.LoginManager;
import com.sacms.models.Advisor;
import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.util.DateTimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private ObservableList<Event> observableEvents;

    public AdvisorDashboard() {
        loginManager = LoginManager.getInstance();
        observableEvents = FXCollections.observableArrayList();
    }

    public void initialize() {
        Advisor advisor = (Advisor) loginManager.getCurrentUser();

        Club club = advisor.getClubs().get(0);
        observableEvents = FXCollections.observableList(club.getAllEvents());

        lst_events.setItems(observableEvents);
        lst_events.setPlaceholder(new Label("No events to display"));
        lst_events.setCellFactory(eventListView -> new ListCell<>() {
            @Override
            protected void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (event != null && !empty) {
                    final String event_date = DateTimeUtils.toISODate(event.getStartDate());
                    final String event_title = event.getTitle();

                    setText(event_date + " --- " + event_title);
                }
            };
        });

        lst_events.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedEvent(newValue)
        );

        if (advisor.getClubs().isEmpty()) {
            vbox_noClubView.setVisible(true);
            vbox_clubAdvisorView.setVisible(false);
        } else {
            vbox_noClubView.setVisible(false);
            vbox_clubAdvisorView.setVisible(true);
            setCurrentClub(advisor.getClubs().get(0));
        }

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
            lbl_startDate.setText("Start date: " + event.getStartDate().toString());
            lbl_startTime.setText("Start time: " + event.getStartTime().toString());
            lbl_endDate.setText("End date: " + event.getEndDate().toString());
            lbl_endTime.setText("End time: " + event.getEndTime().toString());
        }
    }

    @FXML
    void onBtnAddAttendance(ActionEvent ignoredEvent) {
        Window window = btn_addAttendance.getScene().getWindow();
        EventAttendanceDialog eventAttendanceDialog = new EventAttendanceDialog(window, selectedEvent);
        eventAttendanceDialog.showAndWait();
    }

    @FXML
    void onBtnCreateEvent(ActionEvent event) {
        Window window = btn_createEvent.getScene().getWindow();
        NewEventDialog newEventDialog = new NewEventDialog(window, currentClub, currentClub.getAllEvents());
        Event newEvent = newEventDialog.showAndWait();
        if (newEvent != null) observableEvents.add(newEvent);
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
