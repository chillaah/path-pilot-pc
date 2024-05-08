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
                    "INSERT INTO timer VALUES(?,?,?,?)");
            insertData.setInt(1, timer.getUserID());
            insertData.setInt(2, timer.getTimerID());
            insertData.setString(3, timer.getDayName());
            insertData.setInt(4, timer.getDuration());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Timer timer) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE timer SET user_id = ?, timer_id = ?, dayName = ?, duration = ? WHERE timer_id = ?"
            );
            updateData.setInt(1, timer.getUserID());
            updateData.setInt(2, timer.getTimerID());
            updateData.setString(3, timer.getDayName());
            updateData.setInt(4, timer.getDuration());
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
            ResultSet rs = getAll.executeQuery("SELECT * FROM bankAccounts");
            while (rs.next()) {
                timers.add(
                        new Timer(
                                rs.getInt("user_id"),
                                rs.getInt("timer_id"),
                                rs.getString("dayName"),
                                rs.getInt("duration")
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
                        rs.getString("dayName"),
                        rs.getInt("duration")
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
