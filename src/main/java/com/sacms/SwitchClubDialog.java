package com.sacms;

import com.sacms.controllers.SwitchClubsController;
import com.sacms.models.Advisor;
import com.sacms.models.Club;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SwitchClubDialog {
    private Stage stage;
    private Club club;

    public SwitchClubDialog(Window parent, Advisor advisor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/SwitchClubs.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),320,410);

            stage = new Stage();
            stage.initOwner(parent);
            stage.setTitle("New Event");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);

            SwitchClubsController controller = fxmlLoader.getController();
            controller.setAdvisor(advisor);
            controller.setClubSelectListener(club -> {
                this.club = club;
                stage.close();
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Club showAndWait() {
        if (stage == null) return null;
        stage.showAndWait();
        return club;
    }
}
