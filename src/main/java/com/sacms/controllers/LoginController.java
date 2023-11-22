package com.sacms.controllers;

import com.sacms.database.AdvisorDAO;
import com.sacms.database.DAOFactory;
import com.sacms.database.LoginManager;
import com.sacms.database.StudentDAO;
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
    private final ScreenController screenController = ScreenController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roles.getItems().clear();
        roles.getItems().addAll("Advisor", "Student");
        roles.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        try {
            StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);
            AdvisorDAO advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);

            int userID = Integer.parseInt(uid.getText());
            String userPassword = password.getText();
            String role = roles.getValue();

            switch (role) {
                case "Student":
                    Student student = studentDAO.read(userID);
                    if (student != null && student.getPassword().equals(userPassword)) {
                        LoginManager.getInstance().login(student);
                        screenController.activate("StudentDashboard");
                    }
                    break;

                case "Advisor":
                    Advisor advisor = advisorDAO.read(userID);
                    if (advisor != null && advisor.getPassword().equals(userPassword)) {
                        LoginManager.getInstance().login(advisor);
                        screenController.activate("AdvisorDashboard", true);
                    }
                    break;

                default:
                    throw new Exception();
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid User ID").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Warning: Profile doesn't exist").showAndWait();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        screenController.activate("SignUp");
    }

}
