package com.sacms.controllers;

import com.sacms.database.DAOFactory;
import com.sacms.database.EventDAO;
import com.sacms.models.Club;
import com.sacms.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.function.UnaryOperator;

public class NewEvent {
    @FXML private Button btn_cancelEvent;
    @FXML private CheckBox chk_oneDayEvent;
    @FXML private DatePicker date_endDate;
    @FXML private DatePicker date_startDate;
    @FXML private TextField txt_endTime;
    @FXML private TextField txt_eventDescription;
    @FXML private TextField txt_eventName;
    @FXML private TextField txt_startTime;

    private List<Event> events;
    private Callback<Event, Void> callback;
    private Club club;

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setOnCreateEventHandler(Callback<Event, Void> callback) {
        this.callback = callback;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void initialize() {
        date_startDate.setValue(LocalDate.now());
        date_endDate.setValue(LocalDate.now());

        date_startDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (chk_oneDayEvent.isSelected()) {
                date_endDate.setValue(newValue);
            }
        });

        UnaryOperator<TextFormatter.Change> timeFilter = change -> {
            String newText = change.getControlNewText();
            String regex = switch (newText.length()) {
                case 1 -> "[0-2]?";
                case 2 -> "[0-2]?[0-9]?";
                case 3 -> "[0-2]?[0-9]?:?";
                case 4 -> "[0-2]?[0-9]?:?[0-5]?";
                default -> "[0-2]?[0-9]?:?[0-5]?[0-9]?";
            };

            if (newText.matches(regex)) {
                return change;
            }

            return null;
        };

        txt_startTime.setTextFormatter(new TextFormatter<>(timeFilter));
        txt_endTime.setTextFormatter(new TextFormatter<>(timeFilter));
    }


    @FXML
    void onBtnAddEventClick(ActionEvent ignoredEvent) {
        if (!validateInput()) return;

        String name = txt_eventName.getText();
        String description = txt_eventDescription.getText();
        LocalTime startTime = getTimeFromString(txt_startTime.getText());
        LocalTime endTime = getTimeFromString(txt_endTime.getText());
        LocalDate startDate = date_startDate.getValue();
        LocalDate endDate = date_endDate.getValue();

        Event newEvent = new Event(club, name, startDate, endDate, startTime, endTime);

        Event collidingEvent = getCollidingEvent(newEvent);
        if (collidingEvent != null) {
            showErrorMessage("Event collides with another event: " + collidingEvent.getTitle());
            return;
        }

        EventDAO eventDAO = (EventDAO) DAOFactory.getInstance().getDAO(Event.class);
        newEvent = eventDAO.create(newEvent);

        if (callback != null) {
            callback.call(newEvent);
        }

        Stage stage = getThisStage();
        stage.close();
    }

    @FXML
    void onBtnCancelClick(ActionEvent ignoredEvent) {
        getThisStage().close();
    }

    @FXML
    void onChkOneDayToggle(ActionEvent ignoredEvent) {
        date_endDate.setDisable(chk_oneDayEvent.isSelected());
        if (chk_oneDayEvent.isSelected()) {
            date_endDate.setValue(date_startDate.getValue());
        }
    }

    private Stage getThisStage() {
        return (Stage) btn_cancelEvent.getScene().getWindow();
    }

    private Event getCollidingEvent(Event event) {
        for (Event e : events) {
            if (event.isCollidingWith(e)) return e;
        }

        return null;
    }

    private LocalTime getTimeFromString(String time) {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        return LocalTime.of(hour, minute);
    }

    private boolean validateInput() {
        String name = txt_eventName.getText();
        String startTime = txt_startTime.getText();
        String endTime = txt_endTime.getText();
        LocalDate startDate = date_startDate.getValue();
        LocalDate endDate = date_endDate.getValue();

        if (name.isEmpty()) {
            showErrorMessage("Event name cannot be empty");
            return false;
        }

        if (startTime.isEmpty()) {
            showErrorMessage("Event start time cannot be empty");
            return false;
        }

        if (endTime.isEmpty()) {
            showErrorMessage("Event end time cannot be empty");
            return false;
        }

        if (startDate == null) {
            showErrorMessage("Event start date cannot be empty");
            return false;
        }

        if (endDate == null) {
            showErrorMessage("Event end date cannot be empty");
            return false;
        }

        if (txt_startTime.getLength() != 5) {
            showErrorMessage("Event start time must be in the 24h format HH:MM");
            return false;
        }

        if (txt_endTime.getLength() != 5) {
            showErrorMessage("Event end time must be in the 24h format HH:MM");
            return false;
        }

        LocalTime start = getTimeFromString(startTime);
        LocalTime end = getTimeFromString(endTime);
        LocalDateTime eventStart = LocalDateTime.of(startDate, start);
        LocalDateTime eventEnd = LocalDateTime.of(endDate, end);

        if (eventStart.isAfter(eventEnd)) {
            showErrorMessage("Event start time cannot be after event end time");
            return false;
        }

        return true;
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Content error!");
        alert.setHeaderText("Input data invalid");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
