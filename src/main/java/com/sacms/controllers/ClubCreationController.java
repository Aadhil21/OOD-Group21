package com.sacms.controllers;
import com.sacms.database.ClubDAO;
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

        String name = txtName.getText();
        String description = txtDescription.getText();

        Club newClub = new Club();
        newClub.setName(name);
        newClub.setDescription(description);

        ClubDAO clubDAO = new ClubDAO();
        Club insertedClub = clubDAO.create(newClub);

        if (insertedClub != null) {
            // If insertion is successful, update the UI's table
            //updateTable();
        } else {
            // Handle insertion failure
            System.out.println("Failed to add the club to the database.");
        }
    }

    @FXML
    void Remove(ActionEvent event) {

    }
}
