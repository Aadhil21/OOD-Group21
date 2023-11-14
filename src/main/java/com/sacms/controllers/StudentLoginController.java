package com.sacms.controllers;

import com.sacms.services.StudentService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class StudentLoginController {
    @FXML
    private TextField IDNumberField, StudentNameField;
    @FXML
    private PasswordField StudentPasswordField;

    private StudentService studentservice;

    @FXML
    protected void SignInAction() {
        int idNo = Integer.parseInt(IDNumberField.getText());
        String name = StudentNameField.getText();
        String password = StudentPasswordField.getText();

        if (studentservice.StudentRegistered(idNo) != null) {
            ScreenController.activate("ChooseClub");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Not registered");
            alert.show();
        }
    }

    @FXML
    public void SignUpAction() {
        int idNo = Integer.parseInt(IDNumberField.getText());
        String name = StudentNameField.getText();
        String password = StudentPasswordField.getText();

        if (studentservice.StudentRegistered(idNo) == null) {
            studentservice.RegisterStudent(idNo, name, password);
        }
    }
}
