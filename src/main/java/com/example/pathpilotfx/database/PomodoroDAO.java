package com.example.pathpilotfx.database;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.Timer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PomodoroDAO {
    private Connection connection;

    public PomodoroDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Pomodoro timer) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO timer (user_id,break_duration,work_duration) VALUES(?,?,?)");
            insertData.setInt(1, SessionManager.getLoggedInUserId());
            insertData.setInt(2, timer.getRest());
            insertData.setInt(3, timer.getWork());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Pomodoro timer) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE timer SET break_duration = ?, work_duration = ? WHERE user_id = ?"
            );
            updateData.setInt(1, timer.getRest());
            updateData.setInt(2, timer.getWork());
            updateData.setInt(3, SessionManager.getLoggedInUserId());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteTimer(int userId) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM timer WHERE user_id = ?");
            delete.setInt(1, userId);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Pomodoro> getAll() {
        List<Pomodoro> timers = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM timer");
            while (rs.next()) {
                timers.add(
                        new Pomodoro(
                               rs.getInt("work_duration"),
                               rs.getInt("break_duration")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return timers;
    }

    public Pomodoro getTimerByUser(int userId) {
        try {
            PreparedStatement getTimer = connection.prepareStatement("SELECT * FROM timer WHERE user_id = ?");
            getTimer.setInt(1, SessionManager.getLoggedInUserId());
            ResultSet rs = getTimer.executeQuery();
            if (rs.next()) {
                return new Pomodoro(
                        rs.getInt("work_duration"),
                        rs.getInt("break_duration")
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
