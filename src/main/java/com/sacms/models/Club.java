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
    private String description;
    private Advisor advisor;
    private final List<Event> events;

    public Club(String name, String description, Advisor advisor) {
        this.name = name;
        this.advisor = advisor;
        this.description = description;
        this.events = eventDAO.getEventsByClub(this);
    }

    public Club(String name, String description) {
        this.name = name;
        this.description = description;
        this.events = eventDAO.getEventsByClub(this);
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Club club)) return false;
        return club.getName().equals(this.getName());
    }
}
