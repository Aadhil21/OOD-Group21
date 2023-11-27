package com.sacms;

import com.sacms.controllers.EventAttendanceController;
import com.sacms.models.Event;
import com.sacms.models.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

import java.io.IOException;

public class EventAttendanceDialog {
    private Stage stage;
    private final EventAttendanceController controller;

    public EventAttendanceDialog(Window parent, Event event) {
        Scene scene = null;
        EventAttendanceController controller = null;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/EventAttendance.fxml"));
            scene = new Scene(fxmlLoader.load(),600,600);
            controller = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.controller = controller;
        if (controller == null) return; // If above IOException occurred, controller will always be null

        stage = new Stage();
        stage.initOwner(parent);
        stage.setTitle("Event Attendance");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);

        controller.setEvent(event);
    }

    public void showAndWait() {
        if (stage == null) return;
        stage.showAndWait();
    }

    public void setOnStudentAttendListener(Callback<Student, Void> callback) {
        controller.setStudentAttendListener(callback);
    }
}
