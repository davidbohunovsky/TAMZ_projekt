package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class examResultTable {
    //TODO
    // Aby mohlo místo = false být is null / zjistit jak poslat null bool v java
    public static String SQL_SELECT_RESULT_ID = "SELECT * FROM examResult WHERE ID_result = ?";
    public static String SQL_SELECT_TRUE_RESULT_STUDENT = "SELECT * FROM examResult WHERE result = 1 AND ID_student = ?";
    public static String SQL_SELECT_FALSE_RESULT_STUDENT = "SELECT * FROM examResult WHERE result = 0 AND ID_student = ?";
    public static String SQL_SELECT_TRUE_RESULT_TEACHER = "SELECT * FROM examResult eR JOIN exam e ON e.ID_exam = eR.ID_exam WHERE eR.result = 1 AND e.ID_teacher = ?";
    public static String SQL_SELECT_FALSE_RESULT_TEACHER = "SELECT * FROM examResult eR JOIN exam e ON e.ID_exam = eR.ID_exam WHERE eR.result = 0 AND e.ID_teacher = ?";
    public static String SQL_INSERT = "INSERT into examResult (result,points,ID_student,ID_exam) values (?,?,?,?)";
    public static String SQL_UPDATE = "UPDATE examResult SET result = ?, points = ? WHERE ID_result = ?";
    public static String SQL_DELETE = "DELETE FROM examResult WHERE ID_result = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static LinkedList<examResultDB> SelectDoneResultsStudent(int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_TRUE_RESULT_STUDENT);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static LinkedList<examResultDB> SelectResultsStudent(int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_FALSE_RESULT_STUDENT);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static LinkedList<examResultDB> SelectDoneResultsTeacher(int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_TRUE_RESULT_TEACHER);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static LinkedList<examResultDB> SelectResultsTeacher(int ID ) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_SELECT_FALSE_RESULT_TEACHER);
        command.setInt(1,ID);

        List<ResultSetRow> tableWithValues = db.Select(command);
        return proccessResultSet(tableWithValues);
    }

    public static examResultDB SeleceExamResultByID(int ID) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_RESULT_ID);
            command.setInt(1, ID);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<examResultDB> examResultList = proccessResultSet(tableWithValues);
            return examResultList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new examResultDB();
    }

    public static boolean AddExamResult(boolean result, int points, int ID_student, int ID_exam) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setBoolean(1, result);
        command.setInt(2,points);
        command.setInt(3,ID_student);
        command.setInt(4,ID_exam);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdateExamResult(boolean result, int points, int ID) throws SQLException {

        examResultDB tmpRes = SeleceExamResultByID(ID);

        int idStudent = tmpRes.student.ID_student;
        int oldCredits = tmpRes.student.credits;
        if (result == true && tmpRes.result == false) {
            oldCredits += tmpRes.exam.subject.credits;
        } else if (result == false && tmpRes.result == true) {
            oldCredits -= tmpRes.exam.subject.credits;
        }

        studentTable.AddCredits(oldCredits, idStudent);

        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_UPDATE);
        command.setBoolean(1, result);
        command.setInt(2, points);
        command.setInt(3, ID);
        return db.ExecuteNonQuery(command);
    }

    public static boolean DeleteExamResult(int ID) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_DELETE);
        command.setInt(1,ID);
        return db.ExecuteNonQuery(command);
    }

    static private LinkedList<examResultDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<examResultDB> examResultList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            examResultDB examResult = new examResultDB();
            examResult.ID_result = (int) ResultSetRow.columnToValue(row.get(0));
            examResult.result = (String)ResultSetRow.columnToValue(row.get(1)) == "1" ? true : false;
            examResult.points = (int) ResultSetRow.columnToValue(row.get(2));
            examResult.student = studentTable.SelectStudentByID((int) ResultSetRow.columnToValue(row.get(3)));
            examResult.exam = examTable.SeleceExamByID((int) ResultSetRow.columnToValue(row.get(4)));
            examResultList.add(examResult);
        }
        return examResultList;
    }
}
