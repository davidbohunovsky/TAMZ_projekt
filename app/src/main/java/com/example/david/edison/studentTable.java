package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class studentTable {
    public static String SQL_SELECT = "SELECT * FROM student";
    public static String SQL_SELECT_LOGIN = "SELECT * FROM student WHERE login = ?";
    public static String SQL_SELECT_ID = "SELECT * FROM student WHERE ID_student = ?";
    public static String SQL_INSERT = "INSERT INTO student ( name, surname, birth_date ,birth_number, credits  ,login , active ) VALUES (?,?,?,?,?,?,?)";
    public static String SQL_UPDATE = "UPDATE student SET name = ?, surname = ?, birth_date = ?, birth_number = ?, active = ? WHERE ID_student = ?";
    public static String SQL_UPDATE_CREDITS  = "UPDATE student SET credits = ? WHERE ID_student = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static LinkedList<studentDB> SelectAllStudents() {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static studentDB SelectStudentByID(int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_ID);
            command.setInt(1, ID);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<studentDB> studentList = proccessResultSet(tableWithValues);
            return studentList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new studentDB();
    }

    public static studentDB SelectStudentByLogin(String login) throws SQLException {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_LOGIN);
            command.setString(1, login);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<studentDB> studentList = proccessResultSet(tableWithValues);
            return studentList.get(0);
    }

    public static boolean AddStudent(String name, String surname,String birth_date, String birth_number, boolean active ) throws SQLException {
        String tmpLogin = "";
        tmpLogin += name.substring(0,2);
        tmpLogin += surname.substring(0,2);
        Random rand = new Random();
        tmpLogin += rand.nextInt(1000)+1;

        accountTable.AddAccount(tmpLogin,"heslo",1);

        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setString(1, name);
        command.setString(2, surname);
        command.setString(3,birth_date);
        command.setString(4,birth_number);
        command.setInt(5,0);
        command.setString(6,tmpLogin);
        command.setBoolean(7, active);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdateStudent(String name, String surname,String birth_date, String birth_number, boolean active , int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE);
            command.setString(1, name);
            command.setString(2, surname);
            command.setString(3,birth_date);
            command.setString(4,birth_number);
            command.setBoolean(5, active);
            command.setInt(6, ID);
            return db.ExecuteNonQuery(command);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    public static boolean AddCredits(int credits, int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE_CREDITS);
            command.setInt(1,credits);
            command.setInt(2, ID);
            return db.ExecuteNonQuery(command);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    static private LinkedList<studentDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<studentDB> studentList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            studentDB student = new studentDB();
            student.ID_student = (int) ResultSetRow.columnToValue(row.get(0));
            student.name = (String) ResultSetRow.columnToValue(row.get(1));
            student.surname = (String) ResultSetRow.columnToValue(row.get(2));
            student.birth_date = (String) ResultSetRow.columnToValue(row.get(3));
            student.birth_number= (String) ResultSetRow.columnToValue(row.get(4));
            student.credits = (int) ResultSetRow.columnToValue(row.get(5));
            student.login = (String) ResultSetRow.columnToValue(row.get(6));
            student.active = (String)ResultSetRow.columnToValue(row.get(7)) == "1" ? true : false;
            studentList.add(student);
        }
        return studentList;
    }
}