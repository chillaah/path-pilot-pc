package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SessionDAO {
    private Connection connection;

    public SessionDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Session session) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO session VALUES(?,?,?,?,?)");
            insertData.setInt(1, session.getUserID());
            insertData.setInt(2, session.getSessionID());
            insertData.setDate(3, session.getSessionStart());
            insertData.setDate(4, session.getSessionEnd());
            insertData.setInt(5, session.getSessionLength());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

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

    public void deleteSession(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM session WHERE session_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Session> getAll() {
        List<Session> sessions = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM session");
            while (rs.next()) {
                sessions.add(
                        new Session(
                                rs.getInt("user_id"),
                                rs.getInt("session_id"),
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

    public Session getBySessionId(int sessionID) {
        try {
            PreparedStatement getSession = connection.prepareStatement("SELECT * FROM session WHERE session_id = ?");
            getSession.setInt(1, sessionID);
            ResultSet rs = getSession.executeQuery();
            if (rs.next()) {
                return new Session(
                        rs.getInt("user_id"),
                        rs.getInt("session_id"),
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

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
