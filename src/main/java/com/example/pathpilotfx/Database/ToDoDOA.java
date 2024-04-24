package com.example.pathpilotfx.Database;

import com.example.pathpilotfx.model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ToDoDOA {
    private Connection connection;

    public ToDoDOA(){
        connection = DatabaseConnection.getInstance();
    }

    public void createTaskTable() throws SQLException {
        try{
            //need to add user_id to the database as well
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS tasks ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "taskName VARCHAR NOT NULL, "
                    + "status BOOLEAN NOT NULL, "
                    + "description VARCHAR NOT NULL, "
                    + "priority VARCHAR, "
                    + "date_created DATETIME NOT NULL, "
                    + "due_date DATE "
                    + ")"
            );

        } catch (SQLException ex){
            System.err.println(ex);
        }

    }

    //TO UPDATE
    public void insertTask(Task task) {
        try {
            PreparedStatement insertTask = connection.prepareStatement(
                    "INSERT INTO tasks (taskName,status,description,priority,date_created,due_date) VALUES (?, ?, ?, ?, ?, ?)"
            );
            insertTask.setString(1, task.getTask());
            insertTask.setBoolean(2, task.getStatus());
            insertTask.setString(3, task.getDescription());
            insertTask.setString(4, task.getPriority());
            insertTask.setDate(5, task.getDatecreated());
            insertTask.setDate(6,task.getDueDate());
            insertTask.execute();
        } catch (SQLException ex) {
            System.out.println("An error occurred while inserting the task:");
            ex.printStackTrace(); // Print the stack trace for detailed error information
        }
    }


    public void update(Task task) {
        try {
            PreparedStatement updateAccount = connection.prepareStatement(
                    "UPDATE tasks SET taskName = ?, status = ?, description = ?, priority = ?, due_date = ? WHERE id = ?"
            );
            updateAccount.setString(1, task.getTask());
            updateAccount.setBoolean(2, task.getStatus());
            updateAccount.setString(3, task.getDescription());
            updateAccount.setString(4, task.getPriority());
            updateAccount.setDate(5, task.getDueDate());
            updateAccount.setInt(6, task.getID());
            updateAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement deleteAccount = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
            deleteAccount.setInt(1, id);
            deleteAccount.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Task> getAll() {
        List<Task> taskList = new ArrayList<>();
        try {
            if (!connection.isClosed()) { // Check if connection is still open
                Statement getAll = connection.createStatement();
                ResultSet rs = getAll.executeQuery("SELECT * FROM tasks");
                while (rs.next()) {
                    Task task = new Task(
                            rs.getString("taskName"),
                            rs.getString("description"),
                            rs.getString("priority"),
                            getLocalDateOrNull(rs.getDate("due_date"))

                    );
                    task.setId(rs.getInt("id"));
                    task.setStatus(rs.getBoolean("status"));
                    task.setDatecreated(rs.getDate("date_created"));

                    taskList.add(task);
                }
            } else {
                System.out.println("Database connection is closed.");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred while getting all tasks:");
            ex.printStackTrace();
        }
        return taskList;
    }

    private LocalDate getLocalDateOrNull(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    public Task getById(int id) {
        try {
            PreparedStatement getAccount = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?");
            getAccount.setInt(1, id);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()) {
                Task task = new Task(
                        rs.getString("taskName"),
                        rs.getString("description"),
                        rs.getString("priority"),
                        getLocalDateOrNull(rs.getDate("due_date"))
                );
                task.setId(rs.getInt("id"));
                task.setStatus(rs.getBoolean("status"));
                task.setDatecreated(rs.getDate("date_created"));
                return task;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
