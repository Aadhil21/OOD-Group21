package com.sacms.controllers;

import com.sacms.database.ClubMembershipDAO;
import com.sacms.database.DAOFactory;
import com.sacms.models.Club;
import com.sacms.models.ClubMembership;
import com.sacms.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class JoinClubsController {
    @FXML private Button btn_joinClub;
    @FXML private ListView<Club> lst_clubs;

    private final ObservableList<Club> clubList;
    private Callback<Club, Void> clubSelectListener;
    private Club selectedClub = null;
    private Student student;

    public void initialize() {
        lst_clubs.setItems(clubList);
        lst_clubs.setPlaceholder(new Label("No clubs to display"));
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
                    btn_joinClub.setDisable(newValue == null);
                }
        );

        btn_joinClub.setDisable(selectedClub == null);
    }

    public JoinClubsController() {
        this.clubList = FXCollections.observableArrayList();
    }

    public void setStudent(Student student) {
        if (student== null) return;
        clubList.clear();
        clubList.addAll(student.getClubsToJoin());
        this.student = student;
    }

    public void setClubSelectListener(Callback<Club, Void> listener) {
        this.clubSelectListener = listener;
    }


    @FXML
    void onCancel(ActionEvent ignoredEvent) {
        btn_joinClub.getScene().getWindow().hide();
    }

    @FXML
    void onJoinClub(ActionEvent ignoredEvent) {
        ClubMembership membership = new ClubMembership(student, selectedClub);
        ClubMembershipDAO membershipDAO = (ClubMembershipDAO) DAOFactory.getInstance().getDAO(ClubMembership.class);
        membershipDAO.create(membership);

        if (clubSelectListener != null) {
            clubSelectListener.call(selectedClub);
        }
        btn_joinClub.getScene().getWindow().hide();
    }

}
