package com.sacms.database;

import com.sacms.models.*;

public class DAOFactory {
    private static DAOFactory instance = null;

    private final AdvisorDAO advisorDAO;
    private final ClubDAO clubDAO;
    private final ClubMembershipDAO clubMembershipDAO;
    private final EventDAO eventDAO;
    private final AttendanceDAO attendanceDAO;
    private final StudentDAO studentDAO;

    private DAOFactory() {
        this.advisorDAO = AdvisorDAO.getInstance();
        this.clubDAO = new ClubDAO();
        this.clubMembershipDAO = new ClubMembershipDAO();
        this.eventDAO = new EventDAO();
        this.attendanceDAO = new AttendanceDAO();
        this.studentDAO = StudentDAO.getInstance();
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }

        return instance;
    }

    public DAO<?> getDAO(Class<?> dao) {
        if (dao == Advisor.class) return this.advisorDAO;
        if (dao == Club.class) return this.clubDAO;
        if (dao == ClubMembership.class) return this.clubMembershipDAO;
        if (dao == Event.class) return this.eventDAO;
        if (dao == EventAttendee.class) return this.attendanceDAO;
        if (dao == Student.class) return this.studentDAO;

        return null;
    }

    public void generateTables() {
        advisorDAO.createTable();
        attendanceDAO.createTable();
        clubDAO.createTable();
        clubMembershipDAO.createTable();
        eventDAO.createTable();
        studentDAO.createTable();
    }
}
