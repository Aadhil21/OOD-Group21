package com.sacms;

import com.sacms.controllers.ScreenController;
import com.sacms.database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("SACMS!");
        stage.setScene(scene);
        stage.show();

        ScreenController screenController = new ScreenController(scene);
        screenController.addScreen("StudentLogin", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentLoginView.fxml"))));
        screenController.addScreen("AdvisorLogin", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdvisorLoginView.fxml"))));
        screenController.addScreen("ChooseClub", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChooseClubView.fxml"))));
    }

    public static void main(String[] args) {
        if (!Database.loadDBDriver()) return;
        Database.createNewDatabase();
        launch();
    }
}