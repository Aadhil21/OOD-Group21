package com.sacms;

import com.sacms.controllers.ScreenController;
import com.sacms.database.DAOFactory;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,500);
        stage.setTitle("SACMS!");
        stage.setScene(scene);
        stage.show();

        ScreenController screenController = ScreenController.getInstance();
        screenController.setStage(stage);

        screenController.addScreen("SignIn", getClass().getResource("SignInView.fxml"));
        screenController.addScreen("SignUp", getClass().getResource("SignUpView.fxml"));
        screenController.addScreen("AdvisorDashboard", getClass().getResource("view/AdvisorDashboard.fxml"));
        screenController.addScreen("StudentDashboard", getClass().getResource("StudentDashboard.fxml"));
        screenController.addScreen("JoinClub", getClass().getResource("JoinClubView.fxml"));

        screenController.activate("SignIn");
    }

    public static void main(String[] args) {
        DAOFactory.getInstance().generateTables();
        launch();
    }
}