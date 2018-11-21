package com.example.david.edison;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class roomTable{
        public static String SQL_SELECT = "SELECT * FROM room";
        public static String SQL_SELECT_ID = "SELECT * FROM room WHERE ID_room = ?";
        public static String SQL_INSERT = "INSERT INTO room ( number, faculty, active ) VALUES (?,?,?)";
        public static String SQL_UPDATE = "UPDATE room SET number = ?, faculty = ?, active = ? WHERE ID_room = ?";
        private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

        public static LinkedList<roomDB> SelectAllRooms() {
                DatabaseConn db = new DatabaseConn();
                PreparedStatement command = db.CreateCommand(SQL_SELECT);

                List<ResultSetRow> tableWithValues = db.Select(command);
                return proccessResultSet(tableWithValues);
        }

        public static roomDB SelectRoomtByID(int ID) {
                try {
                        DatabaseConn db = new DatabaseConn();
                        PreparedStatement command = db.CreateCommand(SQL_SELECT_ID);
                        command.setInt(1, ID);

                        List<ResultSetRow> tableWithValues = db.Select(command);
                        List<roomDB> roomList = proccessResultSet(tableWithValues);
                        return roomList.get(0);
                } catch (SQLException e) {
                        LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
                }
                return new roomDB();
        }

        public static boolean AddRoom(String number, String faculty, boolean active ) throws SQLException {
                DatabaseConn db = new DatabaseConn();
                PreparedStatement command = db.CreateCommand(SQL_INSERT);
                command.setString(1, number);
                command.setString(2, faculty);
                command.setBoolean(3, active);
                return db.ExecuteNonQuery(command);
        }

        public static boolean UpdateRoom(String number, String faculty, boolean active , int ID) {
                try {
                        DatabaseConn db = new DatabaseConn();
                        PreparedStatement command = db.CreateCommand(SQL_UPDATE);
                        command.setString(1, number);
                        command.setString(2, faculty);
                        command.setBoolean(3, active);
                        command.setInt(4, ID);
                        return db.ExecuteNonQuery(command);

                } catch (SQLException e) {
                        LOGGER.log(Level.SEVERE, "Error occured during Select_ID!", e);
                }
                return false;
        }

        static private LinkedList<roomDB> proccessResultSet(List<ResultSetRow> tableWithValues) {
                LinkedList<roomDB> roomList = new LinkedList<>();
                for (ResultSetRow resultSetRow : tableWithValues) {
                        List<Map.Entry<Object, Class>> row = resultSetRow.row;
                        roomDB room = new roomDB();
                        room.ID_room = (int) ResultSetRow.columnToValue(row.get(0));
                        room.number = (String) ResultSetRow.columnToValue(row.get(1));
                        room.faculty = (String) ResultSetRow.columnToValue(row.get(2));
                        room.active = (String)ResultSetRow.columnToValue(row.get(3)) == "1" ? true : false;
                        roomList.add(room);
                }
                return roomList;
        }
}
