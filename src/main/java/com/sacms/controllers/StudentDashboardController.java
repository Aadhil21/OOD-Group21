package com.sacms.controllers;

import com.sacms.models.Club;
import com.sacms.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
    @FXML
    void onJoinBtnClick(ActionEvent ignoredEvent) {

    }

    @FXML
    void onLeaveClub(ActionEvent ignoredEvent) {

    }

}
