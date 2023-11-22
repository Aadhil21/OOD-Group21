package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.User;
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
    private Label userName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = LoginManager.getInstance().getCurrentUser();
        userName.setText("User Name: " + user.getFirstName() + " " + user.getLastName());
        studentId.setText("Student ID: " + String.valueOf(user.getUid()));
        ShowMyClubs();
    }

    @FXML
    void ShowMyClubs() {
        myClubs.setSelected(true);
        joinClubs.setSelected(false);
        joinMeeting.setSelected(false);


    }

    @FXML
    void ShowJoinClubs() {
        myClubs.setSelected(false);
        joinClubs.setSelected(true);
        joinMeeting.setSelected(false);
    }

    @FXML
    void ShowJoinMeeting() {
        myClubs.setSelected(false);
        joinClubs.setSelected(false);
        joinMeeting.setSelected(true);
    }

    @FXML
    void logOut() {
        screenController.activate("Login");
    }

}
