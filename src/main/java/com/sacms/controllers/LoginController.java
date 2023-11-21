package com.sacms.controllers;

import com.sacms.database.LoginManager;
import com.sacms.models.Advisor;
import com.sacms.models.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> roles;

    @FXML
    private TextField uid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().clear();
        roles.getItems().addAll("Advisor", "Student");
        roles.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        try {
            int userID = Integer.parseInt(uid.getText());
            String userPassword = password.getText();
            String role = roles.getValue();

            switch (role) {
                case "Student":
                    Student student = Student.getStudent(userID, userPassword);
                    if (student != null) {
                        LoginManager.getInstance().login(student);
                        ScreenController.activate("StudentDashboard");
                    }
                    break;

                case "Advisor":
                    Advisor advisor = Advisor.getAdvisor(userID, userPassword);
                    if (advisor != null) {
                        LoginManager.getInstance().login(advisor);
                        ScreenController.activate("AdvisorDashboard");
                    }
                    break;

                default:
                    throw new Exception();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid User ID").showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Warning: Profile doesn't exist").showAndWait();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        ScreenController.activate("SignUp");
    }

}
