package com.sacms.models;

import com.sacms.database.ClubMembershipDAO;
import com.sacms.database.DAOFactory;
import com.sacms.database.StudentDAO;

import java.util.List;

public class Student extends User {

    static StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(Student.class);

    public Student(int uid, String firstName, String lastName, String phone, String email, String password) {
        super(uid, firstName, lastName, phone, email, password);
    }

    public List<Club> getClubs() {
        ClubMembershipDAO clubMembershipDAO = (ClubMembershipDAO) DAOFactory.getInstance().getDAO(ClubMembership.class);
        return clubMembershipDAO.getClubs(this);
    }

    public List<Club> getClubsToJoin() {
        ClubMembershipDAO clubMembershipDAO = (ClubMembershipDAO) DAOFactory.getInstance().getDAO(ClubMembership.class);
        return clubMembershipDAO.getClubsToJoin(this);
    }
}
