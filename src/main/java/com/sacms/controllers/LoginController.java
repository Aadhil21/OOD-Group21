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
            String pass = password.getText();
            String role = roles.getValue();

            if (role.equals("Student")) {
                Student student = Student.getStudent(userID, pass);

                if (student != null) {
                    LoginManager.getInstance().login(student);
                    ScreenController.activate("StudentDashboard");
                }
            }
            else if (role.equals("Advisor")) {
                Advisor advisor = Advisor.getAdvisor(userID, pass);

                if (advisor != null) {
                    LoginManager.getInstance().login(advisor);
                    ScreenController.activate("AdvisorDashboard");
                }
            }
            else {
                throw new Exception();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Warning: Profile doesn't exist", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        ScreenController.activate("SignUp");
    }

}
