package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class teacherTable {
    public static String SQL_SELECT = "SELECT * FROM teacher";
    public static String SQL_SELECT_LOGIN = "SELECT * FROM teacher WHERE login = ?";
    public static String SQL_SELECT_ID = "SELECT * FROM teacher WHERE ID_teacher = ?";
    public static String SQL_INSERT = "INSERT INTO teacher ( name, surname, login , active ) VALUES (?,?,?,?)";
    public static String SQL_UPDATE = "UPDATE teacher SET name = ?, surname = ?, active = ? WHERE ID_teacher = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static LinkedList<teacherDB> SelectAllTeachers() {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static teacherDB SelectTeacherByID(int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_ID);
            command.setInt(1, ID);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<teacherDB> teacherList = proccessResultSet(tableWithValues);
            return teacherList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new teacherDB();
    }

    public static  teacherDB SelectTeacherByLogin(String login){
        try{
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_LOGIN);
            command.setString(1, login);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<teacherDB> teacherList = proccessResultSet(tableWithValues);
            return teacherList.get(0);
        }catch(SQLException e){
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new teacherDB();
    }

    public static boolean AddTeacher(String name, String surname, boolean active ) throws SQLException {
        String tmpLogin = "";
        tmpLogin += name.substring(0,2);
        tmpLogin += surname.substring(0,2);
        Random rand = new Random();
        tmpLogin += rand.nextInt(1000)+1;
        accountTable.AddAccount(tmpLogin,"heslo",2);

        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setString(1, name);
        command.setString(2, surname);
        command.setString(3,tmpLogin);
        command.setBoolean(4, active);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdateTeacher(String name, String surname, boolean active , int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE);
            command.setString(1, name);
            command.setString(2, surname);
            command.setBoolean(3, active);
            command.setInt(4, ID);
            return db.ExecuteNonQuery(command);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    static private LinkedList<teacherDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<teacherDB> teacherList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            teacherDB teacher = new teacherDB();
            teacher.ID_teacher = (int) ResultSetRow.columnToValue(row.get(0));
            teacher.name = (String) ResultSetRow.columnToValue(row.get(1));
            teacher.surname = (String) ResultSetRow.columnToValue(row.get(2));
            teacher.login = (String) ResultSetRow.columnToValue(row.get(3));
            teacher.active = (String)ResultSetRow.columnToValue(row.get(4)) == "1" ? true : false;
            teacherList.add(teacher);
        }
        return teacherList;
    }
}

