package com.sacms;

import com.sacms.controllers.ClubReportController;
import com.sacms.models.Club;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ClubReportDialog {
    private Stage stage;

    public ClubReportDialog(Window owner, Club club) {
        if (owner == null || club == null) return; // Invalid arguments

        ClubReportController controller = null;
        Scene scene = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/ClubReport.fxml"));
            scene = new Scene(fxmlLoader.load(), 600, 500);
            controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (controller == null) return; // If the controller is null, then the scene is null too.

        controller.setClub(club);
        stage = new Stage();
        stage.initOwner(owner);
        stage.setTitle("Club report");
        stage.setScene(scene);
        stage.setMaximized(true);
    }

    public void show() {
        if (stage == null) return;
        stage.show();
    }
}
