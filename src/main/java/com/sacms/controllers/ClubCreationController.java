package com.sacms.controllers;
import com.sacms.models.Advisor;
import com.sacms.models.Club;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

public class ClubCreationController {
    private Advisor advisor;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private TableView<Club> clubTable;

    @FXML
    private TableColumn<Club, String> colDesc;

    @FXML
    private TableColumn<Club, String> colName;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtName;
    private final ObservableList<Club> clubList;
    private Callback<Void, Void> clubListChangeListener;

    public ClubCreationController() {
        clubList = FXCollections.observableArrayList();
    }

    public void initialize() {
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colDesc.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        clubTable.setItems(clubList);
    }

    @FXML
    void Add(ActionEvent event) {
        String name = txtName.getText();
        String description = txtDescription.getText();

        if (name.isEmpty()) {
            showError("Please enter a name for the club!");
            return;
        }

        Club club = new Club(name,description);
        if (advisor.getClubs().contains(club)) {
            showError("This club already exists!");
            return;
        }

        advisor.addClub(club);
        clubList.add(club);
        if (clubListChangeListener != null) {
            clubListChangeListener.call(null);
        }
    }

    @FXML
    void Remove(ActionEvent event) {
        Club selectedClub = (Club) clubTable.getSelectionModel().getSelectedItem(); //using typecast to confirm the returned object is from Club
        if (selectedClub == null) {
            showError("Please select a club to remove!");
            return;
        }

        advisor.removeClub(selectedClub);
        clubList.remove(selectedClub);
        if (clubListChangeListener != null) {
            clubListChangeListener.call(null);
        }
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
        clubList.clear();
        clubList.setAll(advisor.getClubs());
    }

    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input error!");
        alert.setHeaderText("Data input error!");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setClubListChangeListener(Callback<Void, Void> listener) {
        this.clubListChangeListener = listener;
    }
}
