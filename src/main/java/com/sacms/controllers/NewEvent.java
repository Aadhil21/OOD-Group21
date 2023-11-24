package com.sacms.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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

    @FXML
    void onBtnAddEventClick(ActionEvent event) {

    }

    @FXML
    void onBtnCancelClick(ActionEvent event) {

    }

    @FXML
    void onChkOneDayToggle(ActionEvent event) {

    }

}
