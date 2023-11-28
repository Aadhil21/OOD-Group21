package com.sacms.models;

import com.sacms.database.AdvisorDAO;
import com.sacms.database.ClubDAO;
import com.sacms.database.DAOFactory;

import java.util.ArrayList;
import java.util.List;

public class Advisor extends User {
    private final AdvisorDAO advisorDAO;
    private List<Club> clubs = new ArrayList<>();

    public Advisor(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);

        advisorDAO = (AdvisorDAO) DAOFactory.getInstance().getDAO(Advisor.class);
        clubs = advisorDAO.getClubs(this);
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void addClub(Club club) {
        club.setAdvisor(this);
        clubs.add(club);
        ClubDAO clubDAO = (ClubDAO) DAOFactory.getInstance().getDAO(Club.class);
        clubDAO.create(club);
    }

    public void removeClub(Club club) {
        clubs.remove(club);
        ClubDAO clubDAO = (ClubDAO) DAOFactory.getInstance().getDAO(Club.class);
        clubDAO.delete(club);
    }
}