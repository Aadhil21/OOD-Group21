package com.sacms.database;

import com.sacms.models.Club;
import com.sacms.models.ClubMembership;
import com.sacms.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for handling the database operations for the ClubMembership model.
 */
public class ClubMembershipDAO implements DAO<ClubMembership> {
    private static final DBManager dbManager = DBManager.getInstance();

    ClubMembershipDAO() {}

    @Override
    public void createTable() {
        final String createMembers = "CREATE TABLE IF NOT EXISTS Members(club TEXT, student INTEGER, FOREIGN KEY (club) REFERENCES Clubs(name), " +
            "FOREIGN KEY (student) REFERENCES Student(uid), PRIMARY KEY (club, student));";
        dbManager.executeSQLStatement(createMembers);
    }

    @Override
    public ClubMembership create(ClubMembership membership) {
        final String club = membership.club().getName();
        final int student = membership.student().getUid();

        final String sqlStatement = String.format(
            "INSERT INTO Members(club, student) VALUES ('%s', %d);",
            club, student
        );

        dbManager.executeSQLStatement(sqlStatement);
        return null;
    }

    public List<Student> getMembers(Club club) {
        List<Student> students = new ArrayList<>();
        final String sqlStatement = String.format("SELECT S.* FROM Members M JOIN Student S ON M.student = S.uid WHERE M.club = %d",
            club.getName()
        );

        try (DBManager.ResultContainer results = dbManager.executeSQLQuery(sqlStatement)) {
            ResultSet resultSet = results.resultSet;
            while (resultSet.next()) {
                final int uid = resultSet.getInt("uid");
                final String firstName = resultSet.getString("first_name");
                final String lastName = resultSet.getString("last_name");
                final String email = resultSet.getString("email");
                final String phone = resultSet.getString("phone");

                Student student = new Student(uid, firstName, lastName, phone, email, "");
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return students;
    }
    public List<Club> getClubsToJoin(Student student) {
        List<Club> clubs = new ArrayList<>();
        final String sqlStatement = String.format(
                "SELECT c.name, c.description FROM Clubs c LEFT JOIN Members m ON c.name = m.club AND m.student = %d WHERE m.club IS NULL",
                student.getUid()
        );


        try (DBManager.ResultContainer results = dbManager.executeSQLQuery(sqlStatement)) {
            ResultSet resultSet = results.resultSet;
            while (resultSet.next()) {
                final String clubname = resultSet.getString("name");
                final String clubdesc = resultSet.getString("description");

                Club club = new Club(clubname,clubdesc);
                clubs.add(club);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clubs;
    }


    @Override
    public void update(ClubMembership clubMembership) {

    }

    @Override
    public void delete(ClubMembership clubMembership) {

    }
}
