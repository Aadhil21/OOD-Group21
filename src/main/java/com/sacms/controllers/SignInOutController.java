package com.sacms.controllers;

import com.sacms.database.*;
import com.sacms.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class SignInOutController implements Initializable {

    @FXML private TextField userId;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private PasswordField password;
    @FXML private TextField phoneNo;
    @FXML private TextField email;
    @FXML private ChoiceBox<String> rolesChoiceBox;

    private final ScreenController screenController = ScreenController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureRolesChoiceBox();
    }

    private void configureRolesChoiceBox() {
        rolesChoiceBox.getItems().addAll("Advisor", "Student");
        rolesChoiceBox.setValue("Student");
    }

    @FXML
    void signIn(ActionEvent event) {
        if (screenController.getSceneName().equals("SignUp")) {
            screenController.activate("SignIn");
        } else {
            handleSignIn();
        }
    }

    private void handleSignIn() {
        try {
            String userIdText = userId.getText();
            String userPassword = password.getText();
            String role = rolesChoiceBox.getValue();

            if (userIdText.isEmpty() || userPassword.isEmpty()) {
                showAlert("Please fill in all fields", Alert.AlertType.WARNING);
                return;
            }

            int userID = Integer.parseInt(userIdText);

            if (role.equals("Student")) {
                loginAsStudent(userID, userPassword);
            } else if (role.equals("Advisor")) {
                loginAsAdvisor(userID, userPassword);
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid User ID", Alert.AlertType.WARNING);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Warning: Profile doesn't exist", Alert.AlertType.WARNING);
        }
    }

    private void loginAsStudent(int userID, String userPassword) {
        StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);
        Student student = studentDAO.read(userID);

        if (student != null && student.getPassword().equals(userPassword)) {
            LoginManager.getInstance().login(student);
            screenController.activate("StudentDashboard", true);
        } else if (student == null) {
            showAlert("Account doesn't exist please create one", Alert.AlertType.WARNING);
        } else {
            showAlert("Please enter the correct password", Alert.AlertType.WARNING);
        }
    }

    private void loginAsAdvisor(int userID, String userPassword) {
        AdvisorDAO advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);
        Advisor advisor = advisorDAO.read(userID);

        if (advisor != null && advisor.getPassword().equals(userPassword)) {
            LoginManager.getInstance().login(advisor);
            screenController.activate("AdvisorDashboard", true);
        }else if (advisor == null) {
            showAlert("Account doesn't exist please create one", Alert.AlertType.WARNING);
        }  else {
            showAlert("Please enter the correct password", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void signUp(ActionEvent event) {
        if (screenController.getSceneName().equals("SignIn")) {
            screenController.activate("SignUp");
        } else {
            handleSignUp();
        }
    }

    private void handleSignUp() {
        try {
            String userIdText = userId.getText();
            String userFirstName = firstName.getText();
            String userLastName = lastName.getText();
            String userPhoneNo = phoneNo.getText();
            String userEmail = email.getText();
            String userPassword = password.getText();
            String role = rolesChoiceBox.getValue();

            if (userIdText.isEmpty() || userFirstName.isEmpty() || userLastName.isEmpty()
                    || userPhoneNo.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                showAlert("Please fill in all fields", Alert.AlertType.WARNING);
                return;
            }

            int userId = Integer.parseInt(userIdText);

            if (role.equals("Student")) {
                createStudent(userId, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
            } else if (role.equals("Advisor")) {
                createAdvisor(userId, userFirstName, userLastName, userPhoneNo, userEmail, userPassword);
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter a valid User ID", Alert.AlertType.WARNING);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Warning: Failed to create profile", Alert.AlertType.WARNING);
        }
    }

    private void createStudent(int userId, String firstName, String lastName,
                               String phoneNo, String email, String password) {
        StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);
        Student student = studentDAO.read(userId);
        if(student != null){
            showAlert("Account already exist please login", Alert.AlertType.WARNING);
        }else {
            student = new Student(userId, firstName, lastName, phoneNo, email, password);

            if (studentDAO.create(student) != null) {
                screenController.activate("SignIn");
            }
        }
    }

    private void createAdvisor(int userId, String firstName, String lastName,
                               String phoneNo, String email, String password) {
        AdvisorDAO advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);
        Advisor advisor = advisorDAO.read(userId);
        if(advisor != null) {
            showAlert("Account already exist please login", Alert.AlertType.WARNING);
        }else{
            advisor = new Advisor(userId, firstName, lastName, phoneNo, email, password);

            if (advisorDAO.create(advisor) != null) {
                screenController.activate("SignIn");
            }
        }
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        new Alert(alertType, message, ButtonType.OK).showAndWait();
    }
}
