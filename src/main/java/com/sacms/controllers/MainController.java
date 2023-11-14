package com.sacms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    private Button StudentPortal,AdvisorPortal;

    @FXML
    protected void AdvisorPortalButtonClick(ActionEvent actionEvent) {
        ScreenController.activate("AdvisorLogin");
    }

    public void StudentPortalButtonClick(ActionEvent actionEvent) {
        ScreenController.activate("StudentLogin");
    }
}