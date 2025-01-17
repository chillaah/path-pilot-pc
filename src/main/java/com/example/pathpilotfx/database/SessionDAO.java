package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class representing a Session Data Access Object for SQLite queries.
 */
public class SessionDAO {
    private Connection connection;

    /**
     * Constructs a SessionDAO object and initializes the database connection.
     */
    public SessionDAO() {
        connection = DatabaseConnection.getInstance();
    }



    /**
     * Inserts session data into the database.
     *
     * @param session The session object to be inserted.
     */
    public void insert(Session session) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO session (user_id, session_start, session_end, session_length) VALUES(?,?,?,?)");
            insertData.setInt(1, session.getUserID());
            insertData.setDate(2, session.getSessionStart());
            insertData.setDate(3, session.getSessionEnd());
            insertData.setInt(4, session.getSessionLength());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    /**
     * Updates session data in the database.
     *
     * @param session The session object to be updated.
     */
    public void update(Session session) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE session SET user_id = ?, session_id = ?, " +
                        "session_start = ?, session_end = ?, session_length = ? WHERE session_id = ?"
            );
            updateData.setInt(1, session.getUserID());
            updateData.setInt(2, session.getSessionID());
            updateData.setDate(3, session.getSessionStart());
            updateData.setDate(4, session.getSessionEnd());
            updateData.setInt(5, session.getSessionLength());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    /**
     * Retrieves all session data from the database.
     *
     * @return A list of Session objects containing all session data.
     */
    public List<Session> getAll() {
        List<Session> sessions = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM session");
            while (rs.next()) {
                sessions.add(
                        new Session(
                                rs.getInt("user_id"),
                                rs.getDate("session_start"),
                                rs.getDate("session_end"),
                                rs.getInt("session_length")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return sessions;
    }

    /**
     * Retrieves session data from the database based on the session ID.
     *
     * @param sessionID The ID of the session to retrieve.
     * @return The Session object corresponding to the session ID.
     */
    public Session getBySessionId(int sessionID) {
        try {
            PreparedStatement getSession = connection.prepareStatement("SELECT * FROM session WHERE session_id = ?");
            getSession.setInt(1, sessionID);
            ResultSet rs = getSession.executeQuery();
            if (rs.next()) {
                return new Session(
                        rs.getInt("user_id"),
                        rs.getDate("session_start"),
                        rs.getDate("session_end"),
                        rs.getInt("session_length")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Retrieves total focus minutes for a user from the database.
     *
     * @param userID The ID of the user to retrieve session data for.
     * @return A list of Session objects corresponding to the user ID.
     */
    public int getTotalFocusMinutes(int userID) {
        int totalFocusMinutes = -1;
        try {
            PreparedStatement getTotalFocusMinutes = connection.prepareStatement("SELECT SUM(session_length) FROM session WHERE user_id = ?");
            getTotalFocusMinutes.setInt(1, userID);
            ResultSet rs = getTotalFocusMinutes.executeQuery();
            if (rs.next()) {
                totalFocusMinutes = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return totalFocusMinutes;
    }

    /**
     * Retrieves total focus sessions for a user from the database.
     *
     * @param userID The ID of the user to retrieve session data for.
     * @return A list of Session objects corresponding to the user ID.
     */
    public int getTotalFocusSessions(int userID) {
        int totalFocusSessions = -1;
        try {
            PreparedStatement getTotalFocusSessions = connection.prepareStatement("SELECT COUNT(session_id) FROM session WHERE user_id = ?");
            getTotalFocusSessions.setInt(1, userID);
            ResultSet rs = getTotalFocusSessions.executeQuery();
            if (rs.next()) {
                totalFocusSessions = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return totalFocusSessions;
    }

    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
