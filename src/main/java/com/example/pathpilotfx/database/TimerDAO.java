package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Timer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TimerDAO {
    private Connection connection;

    public TimerDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Timer timer) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO timer VALUES(?,?,?)");
            insertData.setInt(1, timer.getUserID());
            insertData.setInt(2, timer.getBreakDuration());
            insertData.setInt(3, timer.getWorkDuration());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Timer timer) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE timer SET user_id = ?, break_duration = ?, work_duration = ? WHERE timer_id = ?"
            );
            updateData.setInt(1, timer.getUserID());
            updateData.setInt(2, timer.getBreakDuration());
            updateData.setInt(3, timer.getWorkDuration());
            updateData.setInt(4, timer.getTimerID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void deleteTimer(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM timer WHERE timer_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Timer> getAll() {
        List<Timer> timers = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM timer");
            while (rs.next()) {
                timers.add(
                        new Timer(
                                rs.getInt("user_id"),
                                rs.getInt("timer_id"),
                                rs.getInt("break_duration"),
                                rs.getInt("work_duration")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return timers;
    }

    public Timer getByTimerId(int id) {
        try {
            PreparedStatement getTimer = connection.prepareStatement("SELECT * FROM timer WHERE timer_id = ?");
            getTimer.setInt(1, id);
            ResultSet rs = getTimer.executeQuery();
            if (rs.next()) {
                return new Timer(
                        rs.getInt("user_id"),
                        rs.getInt("timer_id"),
                        rs.getInt("break_duration"),
                        rs.getInt("work_duration")
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
