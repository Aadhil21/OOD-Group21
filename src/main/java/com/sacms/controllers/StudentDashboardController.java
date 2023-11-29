package com.sacms.controllers;

import com.sacms.JoinClubDialog;
import com.sacms.database.LoginManager;
import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.models.Student;
import com.sacms.util.DateTimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Window;

public class StudentDashboardController {
    @FXML private Button btnLeaveClub;

    @FXML private Label lbl_event_desc;
    @FXML private Label lbl_event_endDate;
    @FXML private Label lbl_event_endTime;
    @FXML private Label lbl_event_name;
    @FXML private Label lbl_event_startDate;
    @FXML private Label lbl_event_startTime;

    @FXML private Label lbl_student_mail;
    @FXML private Label lbl_student_name;
    @FXML private Label lbl_student_phone;

    @FXML private ListView<Club> lst_clubs;
    @FXML private ListView<Event> lst_events;

    private final ObservableList<Club> clubs;
    private final ObservableList<Event> events;
    private final LoginManager loginManager;
    private Student student;
    private Club selectedClub = null;

    public StudentDashboardController() {
        this.clubs = FXCollections.observableArrayList();
        this.events = FXCollections.observableArrayList();
        this.loginManager = LoginManager.getInstance();
    }

    public void initialize() {
        lst_clubs.setItems(this.clubs);
        lst_events.setItems(this.events);

        student = (Student) this.loginManager.getCurrentUser();

        lbl_student_name.setText(student.getFirstName() + " " + student.getLastName());
        lbl_student_mail.setText(student.getEmail());
        lbl_student_phone.setText(student.getPhone());

        initClubList();
        initEventList();

        btnLeaveClub.setDisable(true);
    }

    private void initClubList() {
        lst_clubs.setItems(clubs);
        lst_clubs.setPlaceholder(new Label("No clubs to display"));
        lst_clubs.setCellFactory(eventListView -> new ListCell<>() {
            @Override
            protected void updateItem(Club club, boolean empty) {
                super.updateItem(club, empty);
                if (club != null && !empty) {
                    setText(club.getName());
                } else {
                    setText(null);
                    setGraphic(null);
                }
            };
        });

        lst_clubs.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedClub(newValue)
        );
    }

    private void initEventList() {
        lst_events.setItems(events);
        lst_events.setPlaceholder(new Label("No events to display"));
        lst_events.setCellFactory(eventListView -> new ListCell<>() {
            @Override
            protected void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (event != null && !empty) {
                    final String event_date = DateTimeUtils.toISODate(event.getStartDate());
                    final String event_title = event.getTitle();

                    setText(event_date + " --- " + event_title);
                } else {
                    setText(null);
                    setGraphic(null);
                }
            };
        });

        lst_events.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedEvent(newValue)
        );
    }

    private void setSelectedClub(Club club) {
        this.selectedClub = club;
        this.clubs.clear();
        events.clear();
        btnLeaveClub.setDisable(club == null);

        if (club == null) return;

        this.clubs.addAll(student.getClubs());
        events.addAll(club.getAllEvents());
    }

    private void setSelectedEvent(Event event) {
        if (event == null) {
            lbl_event_name.setText("N/A");
            lbl_event_desc.setText("N/A");
            lbl_event_startDate.setText("N/A");
            lbl_event_startTime.setText("N/A");
            lbl_event_endDate.setText("N/A");
            lbl_event_endTime.setText("N/A");
        } else {
            lbl_event_name.setText(event.getTitle());
            lbl_event_desc.setText(event.getDescription());
            lbl_event_startDate.setText(DateTimeUtils.toISODate(event.getStartDate()));
            lbl_event_startTime.setText(event.getStartDate().toString());
            lbl_event_endDate.setText(DateTimeUtils.toISODate(event.getEndDate()));
            lbl_event_endTime.setText(event.getEndDate().toString());
        }
    }

    @FXML
    void onJoinBtnClick(ActionEvent ignoredEvent) {
        Window window = btnLeaveClub.getScene().getWindow();
        JoinClubDialog joinClubDialog = new JoinClubDialog(window, student);
        Club newClub = joinClubDialog.showAndWait();
        if (newClub != null) clubs.add(newClub);
    }

    @FXML
    void onLeaveClub(ActionEvent ignoredEvent) {
        if (selectedClub == null) return;
        clubs.remove(selectedClub);
    }
}
