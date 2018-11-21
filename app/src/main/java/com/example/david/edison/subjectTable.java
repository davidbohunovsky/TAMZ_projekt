package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class subjectTable {
    public static String SQL_SELECT = "SELECT * FROM subject";
    public static String SQL_SELECT_ID = "SELECT * FROM subject WHERE ID_subject = ?";
    public static String SQL_INSERT = "INSERT INTO subject ( name, credits, active ) VALUES (?,?,?)";
    public static String SQL_UPDATE = "UPDATE subject SET name = ?, credits = ?, active = ? WHERE ID_subject = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static LinkedList<subjectDB> SelectAllSubjects() {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static subjectDB SelectSubjectByID(int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_ID);
            command.setInt(1, ID);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<subjectDB> subjectList = proccessResultSet(tableWithValues);
            return subjectList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new subjectDB();
    }

    public static boolean AddSubject(String name, int credits, boolean active ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setString(1, name);
        command.setInt(2, credits);
        command.setBoolean(3, active);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdateSubject(String name, int credits, boolean active, int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE);
            command.setString(1, name);
            command.setInt(2, credits);
            command.setBoolean(3, active);
            command.setInt(4, ID);
            return db.ExecuteNonQuery(command);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    static private LinkedList<subjectDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<subjectDB> subjectList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            subjectDB subject = new subjectDB();
            subject.ID_subject = (int) ResultSetRow.columnToValue(row.get(0));
            subject.name = (String) ResultSetRow.columnToValue(row.get(1));
            subject.credits = (int) ResultSetRow.columnToValue(row.get(2));
            subject.active = (String)ResultSetRow.columnToValue(row.get(3)) == "1" ? true : false;
            subjectList.add(subject);
        }
        return subjectList;
    }
}