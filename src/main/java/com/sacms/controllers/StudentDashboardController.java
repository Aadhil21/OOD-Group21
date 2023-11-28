package com.sacms.controllers;

import com.sacms.database.ClubMembershipDAO;
import com.sacms.database.DAOFactory;
import com.sacms.database.LoginManager;
import com.sacms.models.Club;
import com.sacms.models.ClubMembership;
import com.sacms.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StudentDashboardController implements Initializable {

    private Student student = null;

    @FXML
    private Label email;

    @FXML
    private ListView<String> joinClubs;

    @FXML
    private Tab joinClubsList;

    @FXML
    private ListView<String> joinMeeting;

    @FXML
    private Tab myClubs;

    @FXML
    private ListView<String> myClubsList;

    @FXML
    private Tab myMeetingList;

    @FXML
    private Label phoneNo;

    @FXML
    private Label studentId;

    @FXML
    private Label userName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        student = (Student) LoginManager.getInstance().getCurrentUser();
        userName.setText(student.getFirstName() +" "+student.getLastName());
        studentId.setText(String.valueOf(student.getUid()));
        email.setText(student.getEmail());
        phoneNo.setText(student.getPhone());
    }

    @FXML
    void ShowJoinClubs(ActionEvent event) {
        ClubMembershipDAO membershipDAO = (ClubMembershipDAO) DAOFactory.getInstance().getDAO(ClubMembership.class);
        List<Club> list = membershipDAO.getClubsToJoin(student);
        ObservableList<String> observableClubInfoList = FXCollections.observableArrayList();

        for (Club club : list) {
            String clubInfo = club.getName() + ": " + club.getDescription();
            observableClubInfoList.add(clubInfo);
        }

        joinClubs.setItems(observableClubInfoList);
    }


    @FXML
    void ShowMyMeeting(ActionEvent event) {

    }

    @FXML
    void ShowMyClubs(ActionEvent event) {

    }

    @FXML
    void logOut(ActionEvent event) {
        LoginManager.getInstance().logout();

    }
}


