package com.sacms.models;

import com.sacms.database.AdvisorDAO;

public class Advisor extends User {
    static AdvisorDAO DAO = new AdvisorDAO();

    public Advisor(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);
    }

    public static Advisor getAdvisor(int uid, String pass) {
        try {
            Advisor advisor = DAO.read(uid);
            if(advisor.getPassword().equals(pass)){
                return advisor;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

    public static Advisor setAdvisor(Advisor advisor) {
        try {
            DAO.create(advisor);
            return advisor;
        } catch (Exception e) {
            return null;
        }
    }

}