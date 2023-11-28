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
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private ChoiceBox<String> rolesChoiceBox;

    @FXML
    private TextField userID;
    private final ScreenController screenController = ScreenController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rolesChoiceBox.getItems().clear();
        rolesChoiceBox.getItems().addAll("Advisor", "Student");
        rolesChoiceBox.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        try {
            StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);
            AdvisorDAO advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);

            String userIdText = userID.getText();
            String userPassword = password.getText();
            String role = rolesChoiceBox.getValue();

            if (userIdText.isEmpty() || userPassword.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please fill in all fields", ButtonType.OK).showAndWait();
                return;
            }

            int userID = Integer.parseInt(userIdText);

            if (role.equals("Student")) {
                Student student = studentDAO.read(userID);
                if (student != null && student.getPassword().equals(userPassword)) {
                    LoginManager.getInstance().login(student);
                    screenController.activate("StudentDashboard");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Please enter the correct password").showAndWait();
                }
            } else if (role.equals("Advisor")) {
                Advisor advisor = advisorDAO.read(userID);
                if (advisor != null && advisor.getPassword().equals(userPassword)) {
                    LoginManager.getInstance().login(advisor);
                    screenController.activate("AdvisorDashboard");
                }else{
                    new Alert(Alert.AlertType.WARNING, "Please enter the correct password").showAndWait();
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid User ID").showAndWait();
        } catch (NullPointerException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.WARNING, "Warning: Profile doesn't exist").showAndWait();
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        screenController.activate("SignUp");
    }

}
