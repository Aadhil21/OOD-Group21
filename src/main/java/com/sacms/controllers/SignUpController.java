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

public class SignUpController implements Initializable {

    @FXML
    private TextField userId;

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
    private ChoiceBox<String> rolesChoiceBox;

    private final ScreenController screenController = ScreenController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rolesChoiceBox.getItems().clear();
        rolesChoiceBox.getItems().addAll("Advisor", "Student");
        rolesChoiceBox.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        screenController.activate("Login");
    }

    @FXML
    void signUp(ActionEvent event) {
        try {
            StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);
            AdvisorDAO advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);

            String userIdText = userId.getText();
            String userFirstName = firstName.getText();
            String userLastName = lastName.getText();
            String userPhoneNo = phoneNo.getText();
            String userEmail = email.getText();
            String userPassword = password.getText();
            String role = rolesChoiceBox.getValue();

            if (userIdText.isEmpty()
                    || userFirstName.isEmpty()
                    || userLastName.isEmpty()
                    || userPhoneNo.isEmpty()
                    || userEmail.isEmpty()
                    || userPassword.isEmpty())
            {
                new Alert(Alert.AlertType.WARNING, "Please fill in all fields", ButtonType.OK).showAndWait();
                return;
            }

            int userId = Integer.parseInt(userIdText);

            if (role.equals("Student")) {
                Student student = new Student(userId, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
                if (studentDAO.create(student) != null) {
                    screenController.activate("Login");
                }
            } else if (role.equals("Advisor")) {
                Advisor advisor = new Advisor(userId, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
                if (advisorDAO.create(advisor) != null) {
                    screenController.activate("Login");
                }
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter a valid User ID", ButtonType.OK).showAndWait();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Warning: Failed to create profile", ButtonType.OK).showAndWait();
        }
    }
}
