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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainLoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,500);
        stage.setTitle("SACMS!");
        stage.setScene(scene);
        stage.show();

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("MainLogin", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainLoginView.fxml"))));
        screenController.addScreen("StudentDashboard", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentDashboard.fxml"))));
        screenController.addScreen("CreateStudentProfile", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateStudentProfileView.fxml"))));
        screenController.addScreen("ChooseClub", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChooseClubView.fxml"))));
    }

    public static void main(String[] args) {
        DBManager.getInstance();
        launch();
    }
}