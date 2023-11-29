package com.sacms.models;

import com.sacms.database.AttendanceDAO;
import com.sacms.database.DAOFactory;
import com.sacms.util.DateTimeUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private Club club;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private final List<Student> attendees;

    public Event(int id, Club club, String title, LocalDate startDate, LocalDate endDate,
                 LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.club = club;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;

        if (id != -1) {
            AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(EventAttendee.class);
            this.attendees = attendanceDAO.getAttendedStudents(this);
        } else {
            this.attendees = new ArrayList<>();
        }
    }

    public Event(Club club, String title, LocalDate startDate, LocalDate endDate,
                 LocalTime startTime, LocalTime endTime) {
        this(-1, club, title, startDate, endDate, startTime, endTime);
    }

    public Event(int key, Event event) {
        this(key, event.getClub(), event.getTitle(), event.getStartDate(), event.getEndDate(),
                event.getStartTime(), event.getEndTime());
    }

    public int getId() {
        return id;
    }

    public Club getClub() {
        return club;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        if (description == null) return "No description provided";
        return !description.isBlank() ? description : "No description provided";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void addAttendee(Student student) {
        EventAttendee eventAttendee = new EventAttendee(this, student);
        AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(EventAttendee.class);
        attendanceDAO.create(eventAttendee);
        attendees.add(student);
    }

    public boolean isCollidingWith(Event event) {
        LocalDateTime thisStart = this.getStartDate().atTime(this.getStartTime());
        LocalDateTime thisEnd = this.getEndDate().atTime(this.getEndTime());
        LocalDateTime otherStart = event.getStartDate().atTime(event.getStartTime());
        LocalDateTime otherEnd = event.getEndDate().atTime(event.getEndTime());

        return DateTimeUtils.isDateTimeOverlapping(thisStart, thisEnd, otherStart, otherEnd);
    }

    public void removeAttendee(Student student) {
        EventAttendee eventAttendee = new EventAttendee(this, student);
        AttendanceDAO attendanceDAO = (AttendanceDAO) DAOFactory.getInstance().getDAO(EventAttendee.class);
        attendanceDAO.delete(eventAttendee);
        attendees.remove(student);
    }
}
