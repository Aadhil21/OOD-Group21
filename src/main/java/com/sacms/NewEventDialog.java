package com.sacms;

import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.controllers.NewEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.List;

public class NewEventDialog {
    private Stage stage;
    private Event newEvent;

    public NewEventDialog(Window parent, Club club, List<Event> events) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/NewEvent.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),400,400);

            stage = new Stage();
            stage.initOwner(parent);
            stage.setTitle("New Event");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setResizable(false);

            NewEvent controller = fxmlLoader.getController();
            controller.setEvents(events);
            controller.setClub(club);
            controller.setOnCreateEventHandler(event -> {
                newEvent = event;
                stage.close();
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Event showAndWait() {
        if (stage == null) return null;
        stage.showAndWait();
        return newEvent;
    }
}
