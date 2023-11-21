package com.sacms.models;

import com.sacms.database.AdvisorDAO;
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

    public static Advisor getAdvisor(int uid, String pass) {
        try {
            Advisor advisor = DAOFactory.getInstance().getDAO(Advisor.class).read(uid);
            if (advisor != null && advisor.getPassword().equals(pass)) {
                return advisor;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static Advisor setAdvisor(Advisor advisor) {
        try {
            DAOFactory.getInstance().getDAO(Advisor.class).create(advisor);
            return advisor;
        } catch (Exception e) {
            return null;
        }
    }


}