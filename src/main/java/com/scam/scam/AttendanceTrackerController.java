package com.scam.scam.AttendanceTracker;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.Pane;

public class AttendanceTrackerController {
    @FXML
    private Pane AttendanceTracker;

    @FXML
    private DatePicker date;

    @FXML
    private TableColumn<?, ?> ExcuseCol;

    @FXML
    private TableColumn<?, ?> PresentStatusCol;

    @FXML
    private TableColumn<?, ?> stdIDCol;

    @FXML
    private TableColumn<?, ?> stdNameCol;
    
}
