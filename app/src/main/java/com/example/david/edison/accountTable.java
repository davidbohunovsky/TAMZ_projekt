package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class accountTable {

    private static String SQL_SELECT_USERNAME = "SELECT * FROM account WHERE username = ?";
    private static String SQL_INSERT = "INSERT INTO account (username, password, authority) VALUES (?,?,?)";
    private static String SQL_UPDATE = "UPDATE account SET password = ? WHERE ID_account = ?";
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public static boolean AddAccount(String username, String password, int authority) throws SQLException {
        DatabaseConn db = new DatabaseConn();
        PreparedStatement command = db.CreateCommand(SQL_INSERT);
        command.setString(1,username);
        command.setString(2,password);
        command.setInt(3,authority);
        return db.ExecuteNonQuery(command);
    }

    public static boolean UpdatePassword(String password, int ID){
        try{
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_UPDATE);
            command.setString(1,password);
            command.setInt(2,ID);
            return db.ExecuteNonQuery(command);

        }catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return false;
    }

    public static account GetAccountByLogin(String username) {
        try {
            DatabaseConn db = new DatabaseConn();
            PreparedStatement command = db.CreateCommand(SQL_SELECT_USERNAME);
            command.setString(1, username);

            List<ResultSetRow> tableWithValues = db.Select(command);
            List<account> accountList = proccessResultSet(tableWithValues);
            if (accountList.isEmpty())
                return  null;
            else
                return accountList.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
        }
        return new account();
    }

    static private LinkedList<account> proccessResultSet(List<ResultSetRow> tableWithValues) {
        LinkedList<account> accountList = new LinkedList<>();
        for (ResultSetRow resultSetRow : tableWithValues) {
            List<Map.Entry<Object, Class>> row = resultSetRow.row;
            account account = new account();
            account.ID_account = (int) ResultSetRow.columnToValue(row.get(0));
            account.username = (String) ResultSetRow.columnToValue(row.get(1));
            account.password = (String) ResultSetRow.columnToValue(row.get(2));
            account.authority = (int) ResultSetRow.columnToValue(row.get(3));
            accountList.add(account);
        }
        return accountList;
    }
}
