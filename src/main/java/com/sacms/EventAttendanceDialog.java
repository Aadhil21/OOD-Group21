package com.sacms;

import com.sacms.controllers.EventAttendanceController;
import com.sacms.controllers.NewEvent;
import com.sacms.models.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class EventAttendanceDialog {
    private Stage stage;

    public EventAttendanceDialog(Window parent, Event event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/EventAttendance.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),600,600);

            stage = new Stage();
            stage.initOwner(parent);
            stage.setTitle("Event Attendance");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);

            EventAttendanceController controller = fxmlLoader.getController();
            controller.setEvent(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAndWait() {
        if (stage == null) return;
        stage.showAndWait();
    }
}
