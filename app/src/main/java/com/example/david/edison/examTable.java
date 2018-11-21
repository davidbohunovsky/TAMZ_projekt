package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class examTable {
    public static String SQL_SELECT_SUBJECT = "SELECT * FROM exam WHERE ID_subject = ?";
    public static String SQL_SELECT_TEACHER = "SELECT * FROM exam WHERE ID_teacher = ?";
    public static String SQL_SELECT_ID = "SELECT * FROM exam WHERE ID_exam = ?";
    public static String SQL_INSERT = "INSERT INTO exam ( date, start_time, end_time, ID_teacher, ID_subject, ID_room ) VALUES (?,?,?,?,?,?)";
    public static String SQL_UPDATE = "UPDATE teacher SET date = ?, start_time = ?, end_time = ?, ID_teacher = ?, ID_subject = ?, ID_room = ? WHERE ID_teacher = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static LinkedList<examDB> SelectExamsBySubject( int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_SUBJECT);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static LinkedList<examDB> SelectExamsByTeacher( int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_TEACHER);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static examDB SeleceExamByID(int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_ID);
            command.setInt(1, ID);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<examDB> examList = proccessResultSet(tableWithValues);
            return examList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new examDB();
    }

    public static boolean AddExam(String date, String start, String end, int ID_teacher, int ID_subject, int ID_room) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setString(1, date);
        command.setString(2, start);
        command.setString(3,end);
        command.setInt(4, ID_teacher);
        command.setInt(5, ID_subject);
        command.setInt(6, ID_room);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdateExam(String date, String start, String end, int ID_teacher, int ID_subject, int ID_room, int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE);
            command.setString(1, date);
            command.setString(2, start);
            command.setString(3,end);
            command.setInt(4, ID_teacher);
            command.setInt(5, ID_subject);
            command.setInt(6, ID_room);
            command.setInt(7,ID);
            return db.ExecuteNonQuery(command);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    static private LinkedList<examDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<examDB> examList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            examDB exam = new examDB();
            exam.ID_exam = (int) ResultSetRow.columnToValue(row.get(0));
            exam.date = (String) ResultSetRow.columnToValue(row.get(1));
            exam.start = (String) ResultSetRow.columnToValue(row.get(2));
            exam.end = (String) ResultSetRow.columnToValue(row.get(3));
            exam.teacher = teacherTable.SelectTeacherByID((int) ResultSetRow.columnToValue(row.get(4)));
            exam.subject = subjectTable.SelectSubjectByID((int) ResultSetRow.columnToValue(row.get(5)));
            exam.room = roomTable.SelectRoomtByID((int) ResultSetRow.columnToValue(row.get(6)));
            examList.add(exam);
        }
        return examList;
    }
}