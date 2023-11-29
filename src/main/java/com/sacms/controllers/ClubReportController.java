package com.sacms.controllers;

import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.models.Student;
import com.sacms.util.DateTimeUtils;
import com.sacms.util.Report;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class ClubReportController {

    @FXML private Label lbl_advisorEmail;
    @FXML private Label lbl_advisorFirstName;
    @FXML private Label lbl_advisorId;
    @FXML private Label lbl_advisorLastName;
    @FXML private Label lbl_advisorPhone;

    @FXML private Label lbl_clubDescription;
    @FXML private Label lbl_clubName;

    @FXML private Label lbl_eventDescription;
    @FXML private Label lbl_eventEndDate;
    @FXML private Label lbl_eventEndTime;
    @FXML private Label lbl_eventName;
    @FXML private Label lbl_eventStartDate;
    @FXML private Label lbl_eventStartTime;

    @FXML private ListView<Event> lst_events;

    @FXML private TableView<Student> tbl_eventAttendance;
    @FXML private TableView<Student> tbl_members;
    @FXML private TableColumn<Student, String> tcol_event_firstName;
    @FXML private TableColumn<Student, String> tcol_event_lastName;
    @FXML private TableColumn<Student, String> tcol_event_phone;
    @FXML private TableColumn<Student, String> tcol_event_studentId;
    @FXML private TableColumn<Student, String> tcol_memberEmail;
    @FXML private TableColumn<Student, String> tcol_memberFirstName;
    @FXML private TableColumn<Student, String> tcol_memberId;
    @FXML private TableColumn<Student, String> tcol_memberLastName;
    @FXML private TableColumn<Student, String> tcol_memberPhone;

    private final ObservableList<Event> events;
    private final ObservableList<Student> members;
    private final ObservableList<Student> eventAttendees;

    public ClubReportController() {
        this.events = FXCollections.observableArrayList();
        this.members = FXCollections.observableArrayList();
        this.eventAttendees = FXCollections.observableArrayList();
    }

    public void setClub(Club club) {
        if (club == null) return;

        lbl_clubName.setText(club.getName());
        lbl_clubDescription.setText(club.getDescription());
        lbl_advisorId.setText(String.valueOf(club.getAdvisor().getUid()));
        lbl_advisorFirstName.setText(club.getAdvisor().getFirstName());
        lbl_advisorLastName.setText(club.getAdvisor().getLastName());
        lbl_advisorEmail.setText(club.getAdvisor().getEmail());
        lbl_advisorPhone.setText(club.getAdvisor().getPhone());

        events.clear();
        events.addAll(club.getAllEvents());
        members.clear();
        members.addAll(club.getMembers());

        Report report = new Report();
        try {
            report.ClubActivityReport(club.getName());
            report.ClubMembershipReport(club.getName());
            for (Event event : club.getAllEvents()) {
                report.EventAttendanceReport(event.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        tbl_members.setItems(members);
        tbl_eventAttendance.setItems(eventAttendees);
        lst_events.setItems(events);

        initEventList();
        initTables();
    }

    private void setEvent(Event event) {
        if (event == null) {
            lbl_eventName.setText("");
            lbl_eventDescription.setText("");
            lbl_eventStartDate.setText("");
            lbl_eventStartTime.setText("");
            lbl_eventEndDate.setText("");
            lbl_eventEndTime.setText("");
            return;
        }

        lbl_eventName.setText(event.getTitle());
        lbl_eventDescription.setText(event.getDescription());
        lbl_eventStartDate.setText(DateTimeUtils.toISODate(event.getStartDate()));
        lbl_eventStartTime.setText(event.getStartTime().toString());
        lbl_eventEndDate.setText(event.getEndDate().toString());
        lbl_eventEndTime.setText(event.getEndTime().toString());

        eventAttendees.clear();
        eventAttendees.addAll(event.getAttendees());
    }

    private void initEventList() {
        lst_events.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setEvent(newValue)
        );

        lst_events.setCellFactory(eventListView -> new ListCell<>() {
            @Override
            protected void updateItem(Event event, boolean empty) {
                super.updateItem(event, empty);
                if (event != null && !empty) {
                    final String event_date = DateTimeUtils.toISODate(event.getStartDate());
                    final String event_title = event.getTitle();

                    setText(event_date + " --- " + event_title);
                } else {
                    setText("");
                }
            }
        });

        lst_events.setPlaceholder(new Label("This club don't have any events yet."));
    }

    private void initTables() {
        tcol_event_studentId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUid())));
        tcol_event_firstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        tcol_event_lastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tcol_event_phone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        tbl_members.setPlaceholder(new Label("This club don't have any members yet."));

        tcol_memberId.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getUid())));
        tcol_memberFirstName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        tcol_memberLastName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        tcol_memberEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        tcol_memberPhone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
        tbl_eventAttendance.setPlaceholder(new Label("No attendees for this event."));
    }
}
