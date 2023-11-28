package com.sacms.controllers;

import com.sacms.models.Advisor;
import com.sacms.models.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class SwitchClubsController {
    @FXML private Button btn_changeClub;
    @FXML private ListView<Club> lst_clubs;

    private final ObservableList<Club> clubList;
    private Callback<Club, Void> clubSelectListener;
    private Club selectedClub = null;

    public SwitchClubsController() {
        this.clubList = FXCollections.observableArrayList();
    }

    public void initialize() {
        lst_clubs.setItems(clubList);
        lst_clubs.setPlaceholder(new Label("No events to display"));
        lst_clubs.setCellFactory(clubListView -> new ListCell<>() {
            @Override
            protected void updateItem(Club club, boolean empty) {
                super.updateItem(club, empty);
                if (club != null && !empty) {
                    setText(club.getName());
                }
            };
        });

        lst_clubs.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                selectedClub = newValue;
                btn_changeClub.setDisable(newValue == null);
            }
        );

        btn_changeClub.setDisable(selectedClub == null);
    }

    public void setAdvisor(Advisor advisor) {
        if (advisor == null) return;
        clubList.clear();
        clubList.addAll(advisor.getClubs());
    }

    public void setClubSelectListener(Callback<Club, Void> listener) {
        this.clubSelectListener = listener;
    }

    @FXML
    void onCancel(ActionEvent ignoredEvent) {
        btn_changeClub.getScene().getWindow().hide();
    }

    @FXML
    void onChangeClub(ActionEvent ignoredEvent) {
        if (clubSelectListener != null) {
            clubSelectListener.call(selectedClub);
        }
        btn_changeClub.getScene().getWindow().hide();
    }
}
