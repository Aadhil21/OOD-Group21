package com.sacms.controllers;

import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.util.DateTimeUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EventAttendanceController {
    @FXML private Label lbl_club;
    @FXML private Label lbl_email;
    @FXML private Label lbl_event;
    @FXML private Label lbl_fullName;
    @FXML private Label lbl_id;
    @FXML private Label lbl_phone;
    @FXML private ListView<?> lst_students;

    private Event event;
    private Club club;

    public void initialize() {
    }

    public void setEvent(Event event) {
        this.event = event;
        lbl_event.setText(event.getTitle() + " (" + DateTimeUtils.toISODate(event.getStartDate()) + ")");
        setClub(event.getClub());
    }

    private void setClub(Club club) {
        this.club = club;
        lbl_club.setText(club.getName());
    }

    @FXML
    void onBtnAttend(ActionEvent event) {

    }

    @FXML
    void onBtnClose(ActionEvent event) {

    }
}
