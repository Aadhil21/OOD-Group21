package com.sacms.models;

import com.sacms.database.AdvisorDAO;

public class Advisor extends User {
    static AdvisorDAO advisorDAO = AdvisorDAO.getInstance();

    public Advisor(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);
    }

    public static Advisor getAdvisor(int uid, String pass) {
        try {
            Advisor advisor = advisorDAO.read(uid);
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
            advisorDAO.create(advisor);
            return advisor;
        } catch (Exception e) {
            return null;
        }
    }


}