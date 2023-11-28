package com.sacms.controllers;
import com.sacms.models.Advisor;
import com.sacms.models.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class ClubCreationController {
    private Advisor advisor;

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
        // TODO: Put real values for CLUB_NAME and CLUB_DESCRIPTION
        Club club = new Club("CLUB_NAME", "CLUB_DESCRIPTION");
        advisor.addClub(club);
    }

    @FXML
    void Remove(ActionEvent event) {
        // TODO: Get the selected club from the table then delete. Following is an example.
        // advisor.removeClub(club);
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
