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

    /**
     * Returns the DAO of the specified type. If the DAO does not exist, null is returned.<br>
     * Example: <br>
     * {@code DAO<Student> studentDAO = DAOFactory.getInstance().getDAO(Student.class);}
     * @param dao The type of DAO to return. Must be one of the data entity classes.
     * @param <T> The type of Data entity class which the required DAO corresponds to.
     * @return The DAO of the specified type.
     */
    @SuppressWarnings("unchecked")
    /* It is guaranteed that the DAOs will be of the correct type by the if conditions.
    When doing any development here, comment out the annotation to get editor warnings */
    public <T> DAO<T> getDAO(Class<T> dao) {
        if (dao == Advisor.class) return (DAO<T>) this.advisorDAO;
        if (dao == Club.class) return (DAO<T>) this.clubDAO;
        if (dao == ClubMembership.class) return (DAO<T>) this.clubMembershipDAO;
        if (dao == Event.class) return (DAO<T>) this.eventDAO;
        if (dao == EventAttendee.class) return (DAO<T>) this.attendanceDAO;
        if (dao == Student.class) return (DAO<T>) this.studentDAO;

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
