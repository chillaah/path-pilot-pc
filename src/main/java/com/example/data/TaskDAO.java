package com.example.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class TaskDAO {
    private Connection connection;

    public TaskDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Task task) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO task VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            insertData.setInt(1, task.getUserID());
            insertData.setInt(2, task.getTaskID());
            insertData.setString(3, task.getTaskName());
            insertData.setString(4, task.getDescription());
            insertData.setDate(5, task.getDeadline());
            insertData.setDate(6, task.getReminder());
            insertData.setBoolean(7, task.isSelected());
            insertData.setInt(8, task.getAssignmentID());
            insertData.setString(9, task.getStatus());
            insertData.setString(10, task.getCategory());
            insertData.setString(11, task.getPriority());
            insertData.setString(12, task.getExtraNotes());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Task task) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE task SET user_id = ?, task_id = ?, title = ?, " +
                        "description = ?, deadline = ?, reminder = ?, selectionStatus = ?, " +
                        "assignment = ?, status = ?, category = ?, priority = ?, extra_notes = ? WHERE task_id = ?"
            );
            updateData.setInt(1, task.getUserID());
            updateData.setInt(2, task.getTaskID());
            updateData.setString(3, task.getTaskName());
            updateData.setString(4, task.getDescription());
            updateData.setDate(5, task.getDeadline());
            updateData.setDate(6, task.getReminder());
            updateData.setBoolean(7, task.isSelected());
            updateData.setInt(8, task.getAssignmentID());
            updateData.setString(9, task.getStatus());
            updateData.setString(10, task.getCategory());
            updateData.setString(11, task.getPriority());
            updateData.setString(12, task.getExtraNotes());
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

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM bankAccounts");
            while (rs.next()) {
                tasks.add(
                        new Task(
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
        return tasks;
    }

    public Task getByTaskId(int id) {
        try {
            PreparedStatement getTask = connection.prepareStatement("SELECT * FROM task WHERE task_id = ?");
            getTask.setInt(1, id);
            ResultSet rs = getTask.executeQuery();
            if (rs.next()) {
                return new Task(
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
