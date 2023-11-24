package com.sacms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;

public class NewEventDialog {
    private Stage stage;

    public NewEventDialog(Window parent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/NewEvent.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),400,400);

            stage = new Stage();
            stage.initOwner(parent);
            stage.setTitle("New Event");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAndWait() {
        if (stage == null) return;
        stage.showAndWait();
    }
}
