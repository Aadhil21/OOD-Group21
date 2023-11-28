package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    @FXML
    private Tab joinClubs;

    @FXML
    private Tab joinMeeting;

    @FXML
    private Tab myClubs;

    @FXML
    private Label studentId;

    @FXML
    private Label email;

    @FXML
    private Label phoneNo;


    @FXML
    private Label userName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = LoginManager.getInstance().getCurrentUser();
        userName.setText(user.getFirstName() +" "+user.getLastName());
        studentId.setText(String.valueOf(user.getUid()));
        email.setText(user.getEmail());
        phoneNo.setText(user.getPhone());

    }

    @FXML
    void ShowJoinClubs(ActionEvent event) {

    }

    @FXML
    void ShowJoinMeeting(ActionEvent event) {

    }

    @FXML
    void ShowMyClubs(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {
        LoginManager.getInstance().logout();

    }
}


