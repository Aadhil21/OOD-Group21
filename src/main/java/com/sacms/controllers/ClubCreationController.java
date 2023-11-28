package com.sacms.controllers;
import com.sacms.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ClubCreationController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;

    @FXML
    void Add(ActionEvent event) {
    }

    @FXML
    void Remove(ActionEvent event) {

    }
}
