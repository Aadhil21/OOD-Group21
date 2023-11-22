package com.sacms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    private final ScreenController screenController = ScreenController.getInstance();

    @FXML
    private TableColumn<?, ?> columnOne;
    @FXML
    private TableColumn<?, ?> columnThree;
    @FXML
    private TableColumn<?, ?> columnTwo;
    @FXML
    private ToggleButton joinClubs;
    @FXML
    private ToggleButton joinMeeting;
    @FXML
    private ToggleButton myClubs;
    @FXML
    private Label studentId;
    @FXML
    private TableView<?> table;
    @FXML
    private Label uerName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myClubs.setSelected(true);
        joinClubs.setSelected(false);
        joinMeeting.setSelected(false);
    }

    @FXML
    void ShowMyClubs(ActionEvent event) {
        myClubs.setSelected(true);
        joinClubs.setSelected(false);
        joinMeeting.setSelected(false);
    }

    @FXML
    void ShowJoinClubs(ActionEvent event) {
        myClubs.setSelected(false);
        joinClubs.setSelected(true);
        joinMeeting.setSelected(false);
    }

    @FXML
    void ShowJoinMeeting(ActionEvent event) {
        myClubs.setSelected(false);
        joinClubs.setSelected(false);
        joinMeeting.setSelected(true);
    }

    @FXML
    void logOut(ActionEvent event){
       screenController.activate("Login");
    }

}
