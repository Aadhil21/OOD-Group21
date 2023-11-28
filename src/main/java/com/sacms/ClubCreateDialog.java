package com.sacms;

import com.sacms.controllers.ClubCreationController;
import com.sacms.controllers.NewEvent;
import com.sacms.models.Advisor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ClubCreateDialog {
    private Stage stage;

    public ClubCreateDialog(Window parent, Advisor advisor) {
        Scene scene = null;
        ClubCreationController controller = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClubCreationView.fxml"));
            scene = new Scene(fxmlLoader.load(),400,420);
            controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (controller == null) return; // If the controller is null, then the scene is null too.

        controller.setAdvisor(advisor);
        stage = new Stage();
        stage.initOwner(parent);
        stage.setTitle("Create new club");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        // stage.setResizable(false);
    }

    public void showAndWait() {
        if (stage == null) return;
        stage.showAndWait();
    }
}
