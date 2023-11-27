package com.sacms.models;

import com.sacms.database.ClubDAO;
import com.sacms.database.ClubMembershipDAO;
import com.sacms.database.DAOFactory;
import com.sacms.database.EventDAO;

import java.util.List;

public class Club {
    static ClubDAO clubDAO = new ClubDAO();
    static EventDAO eventDAO = new EventDAO();

    private String name;
    private Advisor advisor;
    private List<Event> events;

    public Club(String name, Advisor advisor) {
        this.name = name;
        this.advisor = advisor;
        this.events = eventDAO.getEventsByClub(this);
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public void setAllEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public void addEvent(Event event) {
        eventDAO.create(event);
        events.add(event);
    }

    public List<Student> getMembers() {
        ClubMembershipDAO clubMembershipDAO = (ClubMembershipDAO) DAOFactory.getInstance().getDAO(ClubMembership.class);
        return clubMembershipDAO.getMembers(this);
    }
}
