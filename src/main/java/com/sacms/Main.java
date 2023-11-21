package com.sacms;

import com.sacms.controllers.ScreenController;
import com.sacms.database.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,500);
        stage.setTitle("SACMS!");
        stage.setScene(scene);
        stage.show();

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("Login", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginView.fxml"))));
        screenController.addScreen("SignUp", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUpView.fxml"))));
        screenController.addScreen("AdvisorDashboard", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View/AdvisorDashboard.fxml"))));
        screenController.addScreen("StudentDashboard", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentDashboard.fxml"))));
        screenController.addScreen("JoinClub", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("JoinClubView.fxml"))));
    }

    public static void main(String[] args) {
        DBManager database = DBManager.getInstance();
        launch();
    }
}