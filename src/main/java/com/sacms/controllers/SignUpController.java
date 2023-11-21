package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.Advisor;
import com.sacms.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField uid;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNo;

    @FXML
    private TextField email;

    @FXML
    private ChoiceBox<String> roles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().clear();
        roles.getItems().addAll("Advisor", "Student");
        roles.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        ScreenController.activate("Login");
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            int userID = Integer.parseInt(uid.getText());
            String userFirstName = firstName.getText();
            String userLastName = lastName.getText();
            String userPhoneNo = phoneNo.getText();
            String userEmail = email.getText();
            String userPassword = password.getText();
            String role = roles.getValue();

            if (role.equals("Student")) {
                Student student = new Student(userID, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
                if (Student.setStudent(student) != null) {
                    LoginManager.getInstance().login(student);
                    ScreenController.activate("Login");
                }
            } else if (role.equals("Advisor")) {
                Advisor advisor = new Advisor(userID, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
                if (Advisor.setAdvisor(advisor) != null) {
                    LoginManager.getInstance().login(advisor);
                    ScreenController.activate("Login");
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid User ID", ButtonType.OK).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Warning: Failed to create profile", ButtonType.OK).showAndWait();
        }
    }

}