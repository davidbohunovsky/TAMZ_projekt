package com.example.david.edison;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class DatabaseConn {
    private static String CONNECTION_URL = "jdbc:jtds:sqlserver://dbsys.cs.vsb.cz:1521;instance=STUDENT;user=boh0115;password=mZLcfYKAXm";
    private static Connection connection = null;
    private static Logger LOGGER = Logger.getLogger(ResultSetRow.class.getName());

    public DatabaseConn() {
        try {
            new AsyncConnect().execute().get();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.info("Unable to run task connect to database." + "\n" + e);
        }
    }

    public boolean ExecuteNonQuery(PreparedStatement command) {
        try {
            return new AsyncNonQuery().execute(command).get();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.info("Unable to run task NonQuery" + command + "\n" + e);
        }
        return false;
    }

    public PreparedStatement CreateCommand(String strCommand) {
        try {
            return connection.prepareStatement(strCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT);
        } catch (SQLException e) {
            LOGGER.info("Unable to prepare command " + strCommand + "\n" + e);
        }
        return null;
    }

    public List<ResultSetRow> Select(PreparedStatement command) {
        try {
            return new AsyncSelect().execute(command).get();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.info("Unable to run task Select" + command + "\n" + e);
        }
        return null;
    }

    private static class AsyncConnect extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                if (connection == null || connection.isClosed())
                    connection = DriverManager.getConnection(CONNECTION_URL);
            } catch (SQLException e) {
                LOGGER.info("Unable to getConnection, CONNECTION_URL=" + CONNECTION_URL);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static class AsyncSelect extends AsyncTask<PreparedStatement, Void, List<ResultSetRow>> {

        @Override
        protected List<ResultSetRow> doInBackground(PreparedStatement... preparedStatements) {
            List<ResultSetRow> table = new ArrayList<>();
            try {
                ResultSet rs = preparedStatements[0].executeQuery();
                ResultSetRow.formTable(rs, table);
            } catch (SQLException e) {
                LOGGER.info("Unable to execute query! " + preparedStatements[0] + "\n" + e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.info("Unable to close connection" + "\n" + e);
                }
            }
            return table;
        }
    }

    private static class AsyncNonQuery extends AsyncTask<PreparedStatement, Void, Boolean> {

        @Override
        protected Boolean doInBackground(PreparedStatement... preparedStatements) {
            boolean executed = false;
            try {
                executed = preparedStatements[0].execute();
            } catch (Exception e) {
                LOGGER.info("Unable to execute command " + preparedStatements[0] + "\n" + e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.info("Unable to close connection" + "\n" + e);
                }
            }
            return executed;
        }
    }
}