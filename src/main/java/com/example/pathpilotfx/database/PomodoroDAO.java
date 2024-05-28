package com.example.pathpilotfx.database;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.model.Pomodoro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for handling Pomodoro database operations.
 */
public class PomodoroDAO {
    private Connection connection;

    /**
     * Constructs a new ToDoDAO object and initializes the database connection.
     */
    public PomodoroDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Inserts Pomodoro data into the database.
     *
     * @param timer The Pomodoro object containing the data to be inserted.
     */
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

    /**
     * Updates Pomodoro data in the database.
     *
     * @param timer The Pomodoro object containing the updated data.
     */
    public void update(Pomodoro timer) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE timer SET break_duration = ?, work_duration = ? WHERE user_id = ?"
            );
            updateData.setInt(1, timer.getRest());
            updateData.setInt(2, timer.getWork());
            System.out.println(SessionManager.getLoggedInUserId());
            updateData.setInt(3, SessionManager.getLoggedInUserId());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes Pomodoro data from the database.
     *
     * @param userId The ID of the user whose Pomodoro data is to be deleted.
     */
    public void deleteTimer(int userId) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM timer WHERE user_id = ?");
            delete.setInt(1, userId);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all Pomodoro data from the database.
     *
     * @return A list of Pomodoro objects containing all Pomodoro data.
     */
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

    /**
     * Retrieves Pomodoro data for a specific user from the database.
     *
     * @param userId The ID of the user.
     * @return A Pomodoro object containing the data for the specified user.
     */
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

    /**
     * Retrieves the work duration for a specific user from the database.
     *
     * @param userId the ID of the user whose work duration is to be retrieved
     * @return the work duration for the specified user, or -1 if no record is found or an error occurs
     */
    public int getWorkDurationByUser(int userId) {
        try {
            PreparedStatement getTimer = connection.prepareStatement("SELECT * FROM timer WHERE user_id = ?");
            getTimer.setInt(1, SessionManager.getLoggedInUserId());
            ResultSet rs = getTimer.executeQuery();
            if (rs.next()) {
                return rs.getInt("work_duration");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return -1;
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
