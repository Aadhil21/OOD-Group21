package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainLoginController {

    @FXML
    private TextField uid;
    @FXML
    private PasswordField password;

    @FXML
    protected void signIn(ActionEvent actionEvent) {
        int UserId = Integer.parseInt(uid.getText());
        String UserPassword = password.getText();

        Student student = Student.registeredStudent(UserId, String.valueOf(password));

        if (student != null) {
            LoginManager login = LoginManager.getInstance();
            login.login(student);
            ScreenController.activate("StudentDashboard");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Incorrect password");
            alert.show();
        }
    }

    @FXML
    protected void createNewStudentProfile(ActionEvent actionEvent) {
        ScreenController.activate("CreateStudentProfile");
    }

    private void setActiveUser() {

    }
}