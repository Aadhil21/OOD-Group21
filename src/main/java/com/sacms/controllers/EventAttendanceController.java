package com.sacms.controllers;

import com.sacms.models.Club;
import com.sacms.models.Event;
import com.sacms.models.Student;
import com.sacms.util.DateTimeUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;

public class EventAttendanceController {
    @FXML private Button btn_markAsAttended;
    @FXML private Label lbl_club;
    @FXML private Label lbl_email;
    @FXML private Label lbl_event;
    @FXML private Label lbl_fullName;
    @FXML private Label lbl_id;
    @FXML private Label lbl_phone;
    @FXML private ListView<Student> lst_students;

    private Event event;
    private Club club;
    private Student selectedStudent;
    private final ObservableList<Student> students = FXCollections.observableArrayList();
    private Callback<Student, Void> studentAttendListener;

    public void initialize() {
        lst_students.setCellFactory(eventListView -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (student != null && !empty) {
                    setText(student.getUid() + " --- " + student.getFirstName() + " " + student.getLastName());
                } else {
                    setText("");
                }
            };
        });

        lst_students.setPlaceholder(new Label("No students available to mark as attended"));
        lst_students.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setSelectedStudent(newValue)
        );
        lst_students.setItems(students);

        setSelectedStudent(null);
    }

    public void setEvent(Event event) {
        this.event = event;
        lbl_event.setText(event.getTitle() + " (" + DateTimeUtils.toISODate(event.getStartDate()) + ")");
        setClub(event.getClub());
        updateUnattendedStudentsList();
    }

    private void setClub(Club club) {
        this.club = club;
        lbl_club.setText(club.getName());
    }

    private void setSelectedStudent(Student student) {
        if (student == null) System.out.println("Selected student is null");
        else System.out.println("Selected student is " + student.getFirstName() + " " + student.getLastName());
        selectedStudent = student;
        refreshStudentDetails();
        btn_markAsAttended.setDisable(selectedStudent == null);
    }

    private void refreshStudentDetails() {
        if (selectedStudent == null) {
            lbl_id.setText("N/A");
            lbl_fullName.setText("N/A");
            lbl_email.setText("N/A");
            lbl_phone.setText("N/A");
        } else {
            lbl_id.setText(String.valueOf(selectedStudent.getUid()));
            lbl_fullName.setText(selectedStudent.getFirstName() + " " + selectedStudent.getLastName());
            lbl_email.setText(selectedStudent.getEmail());
            lbl_phone.setText(selectedStudent.getPhone());
        }
    }

    public void setStudentAttendListener(Callback<Student, Void> studentAttendListener) {
        this.studentAttendListener = studentAttendListener;
    }

    private void updateUnattendedStudentsList() {
        List<Student> students = new ArrayList<>(club.getMembers());
        List<Student> attendedStudents = event.getAttendees();
        for (Student attendedStudent : attendedStudents) {
            students.remove(attendedStudent);
        }

        this.students.setAll(students);
    }

    @FXML
    void onBtnAttend(ActionEvent ignoredEvent) {
        if (selectedStudent == null) return;
        if (this.event == null) return;

        final Student selectedStudent = this.selectedStudent;
        this.event.addAttendee(selectedStudent);

        if (studentAttendListener != null) {
            studentAttendListener.call(selectedStudent);
        }

        students.remove(selectedStudent);

        final String studentFullName = selectedStudent.getFirstName() + " " + selectedStudent.getLastName();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Student added!");
        alert.setHeaderText("Student marked as attended.");
        alert.setContentText(studentFullName + " has been marked as attended. " +
            "You can mark more students as attended or close this window now.");
        alert.show();
    }

    @FXML
    void onBtnClose(ActionEvent ignoredEvent) {
        btn_markAsAttended.getScene().getWindow().hide();
    }
}
