package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.TaskV;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TaskDAO {
    private Connection connection;

    public TaskDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(TaskV taskV) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO task VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            insertData.setInt(1, taskV.getUserID());
            insertData.setInt(2, taskV.getTaskID());
            insertData.setString(3, taskV.getTaskName());
            insertData.setString(4, taskV.getDescription());
            insertData.setDate(5, taskV.getDeadline());
            insertData.setDate(6, taskV.getReminder());
            insertData.setBoolean(7, taskV.isSelected());
            insertData.setInt(8, taskV.getAssignmentID());
            insertData.setString(9, taskV.getStatus());
            insertData.setString(10, taskV.getCategory());
            insertData.setString(11, taskV.getPriority());
            insertData.setString(12, taskV.getExtraNotes());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(TaskV taskV) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE task SET title = ?, description = ?, " +
                            "deadline = ?, reminder = ?, selectionStatus = ?, " +
                            "assignment = ?, status = ?, category = ?, " +
                            "priority = ?, extra_notes = ? WHERE task_id = ? AND user_id = ?"
            );
            updateData.setString(1, taskV.getTaskName());
            updateData.setString(2, taskV.getDescription());
            updateData.setDate(3, taskV.getDeadline());
            updateData.setDate(5, taskV.getReminder());
            updateData.setBoolean(5, taskV.isSelected());
            updateData.setInt(6, taskV.getAssignmentID());
            updateData.setString(7, taskV.getStatus());
            updateData.setString(8, taskV.getCategory());
            updateData.setString(9, taskV.getPriority());
            updateData.setString(10, taskV.getExtraNotes());
            updateData.setInt(11, taskV.getTaskID());
            updateData.setInt(12, taskV.getUserID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void deleteTask(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM task WHERE task_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<TaskV> getAll() {
        List<TaskV> taskVS = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM bankAccounts");
            while (rs.next()) {
                taskVS.add(
                        new TaskV(
                                rs.getInt("user_id"),
                                rs.getInt("task_id"),
                                rs.getString("title"),
                                rs.getString("description"),
                                rs.getDate("deadline"),
                                rs.getDate("reminder"),
                                rs.getBoolean("selectionStatus"),
                                rs.getInt("assignment"),
                                rs.getString("status"),
                                rs.getString("category"),
                                rs.getString("priority"),
                                rs.getString("extra_notes")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return taskVS;
    }

    public TaskV getByTaskId(int id) {
        try {
            PreparedStatement getTask = connection.prepareStatement("SELECT * FROM task WHERE task_id = ?");
            getTask.setInt(1, id);
            ResultSet rs = getTask.executeQuery();
            if (rs.next()) {
                return new TaskV(
                        rs.getInt("user_id"),
                        rs.getInt("task_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("deadline"),
                        rs.getDate("reminder"),
                        rs.getBoolean("selectionStatus"),
                        rs.getInt("assignment"),
                        rs.getString("status"),
                        rs.getString("category"),
                        rs.getString("priority"),
                        rs.getString("extra_notes")
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
