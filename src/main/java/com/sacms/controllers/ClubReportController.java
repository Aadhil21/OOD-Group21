package com.sacms.controllers;

import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.models.Student;
import com.sacms.util.DateTimeUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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


}
