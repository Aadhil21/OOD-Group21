package com.sacms.controllers;

import com.sacms.database.DAOFactory;
import com.sacms.database.EventDAO;
import com.sacms.models.Club;
import com.sacms.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NewEvent {
    @FXML private Button btn_addEvent;
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

    @FXML
    void onBtnAddEventClick(ActionEvent event) {
        String name = txt_eventName.getText();
        String description = txt_eventDescription.getText();
        LocalTime startTime = getTimeFromString(txt_startTime.getText());
        LocalTime endTime = getTimeFromString(txt_endTime.getText());
        LocalDate startDate = date_startDate.getValue();
        LocalDate endDate = date_endDate.getValue();

        Event newEvent = new Event(club, name, startDate, endDate, startTime, endTime);

        Event collidingEvent = getCollidingEvent(newEvent);
        if (collidingEvent != null) {
            System.out.println("Colliding event: " + collidingEvent.getTitle());
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
    void onBtnCancelClick(ActionEvent event) {
        getThisStage().close();
    }

    @FXML
    void onChkOneDayToggle(ActionEvent event) {

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
}
