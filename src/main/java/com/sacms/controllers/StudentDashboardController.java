package com.sacms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StudentDashboardController {
    private final ScreenController screenController = ScreenController.getInstance();

    @FXML
    void clubReport(ActionEvent event) {

    }

    @FXML
    void joinClub(ActionEvent event) {
        screenController.activate("JoinClub");

    }

    @FXML
    void joinMeeting(ActionEvent event) {

    }


}
