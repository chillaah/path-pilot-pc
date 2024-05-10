package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class ToDoDAO {
    private Connection connection;

    public ToDoDAO(){
        connection = DatabaseConnection.getInstance();
    }

    public void createTaskTable() throws SQLException {
        try{
            //need to add user_id to the database as well
            Statement createTable = connection.createStatement();
            createTable.execute("CREATE TABLE IF NOT EXISTS tasks ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "user_id INTEGER NOT NULL, "
                    + "taskName VARCHAR NOT NULL, "
                    + "status BOOLEAN NOT NULL, "
                    + "description VARCHAR NOT NULL, "
                    + "priority VARCHAR, "
                    + "date_created DATETIME NOT NULL, "
                    + "due_date DATE, "
                    + "FOREIGN KEY (user_id) REFERENCES user(user_id)"
                    + ")"
            );
        } catch (SQLException ex){
            System.err.println(ex);
        }

    }

    //TO UPDATE

    public void insert(Task task) {
        try {

            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO tasks (user_id,taskName,status,description,priority,date_created,due_date) VALUES(?,?,?,?,?,?,?)");
            insertData.setInt(1, task.getUserID());
            insertData.setString(2, task.getTask());
            insertData.setBoolean(3, task.getStatus());
            insertData.setString(4, task.getDescription());
            insertData.setString(5, task.getPriority());
            insertData.setDate(6, task.getDatecreated());
            insertData.setDate(7, task.getDueDate());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Task task) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE tasks SET user_id = ?, task_name = ?, status = ?, " +
                            "description = ?, priority = ?, due_date = ? " +
                            "WHERE id = ?"
            );
            updateData.setInt(1, task.getUserID());
            updateData.setString(2, task.getTask());
            updateData.setBoolean(3,task.getStatus());
            updateData.setString(4, task.getDescription());
            updateData.setString(5, task.getPriority());
            updateData.setDate(6, task.getDueDate());
            updateData.setInt(7, task.getID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void delete(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM tasks WHERE id = ?");
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
            ResultSet rs = getAll.executeQuery("SELECT * FROM tasks");
            while (rs.next()) {
                tasks.add(
                        new Task(
                                rs.getInt("id"),
                                rs.getInt("user_id"),
                                rs.getString("taskName"),
                                rs.getBoolean("status"),
                                rs.getString("description"),
                                rs.getString("priority"),
                                getLocalDateOrNull(rs.getDate("date_created")),
                                getLocalDateOrNull(rs.getDate("due_date"))
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return tasks;
    }

    public List<Task> getUncomplete(int id) {
        List<Task> taskList = new ArrayList<>();
        try {
            if (!connection.isClosed()) { // Check if connection is still open
                Statement getAll = connection.createStatement();
                PreparedStatement getAccount = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
                getAccount.setInt(1, id);
                ResultSet rs = getAccount.executeQuery();
                while (rs.next()) {
                    Task task = new Task(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("taskName"),
                            rs.getBoolean("status"),
                            rs.getString("description"),
                            rs.getString("priority"),
                            getLocalDateOrNull(rs.getDate("date_created")),
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
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("taskName"),
                        rs.getBoolean("status"),
                        rs.getString("description"),
                        rs.getString("priority"),
                        getLocalDateOrNull(rs.getDate("date_created")),
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

    public List<Date> getDueDatesByUserId(int userId) {
        List<Date> tasks = new ArrayList<>();
        try {
            PreparedStatement getTasks = connection.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
            getTasks.setInt(1, userId);
            ResultSet rs = getTasks.executeQuery();
            Date date = null;
            while (rs.next()) {
                date = Date.valueOf(getLocalDateOrNull(rs.getDate("due_date")));
            }
            tasks.add(date);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return tasks;
    }
    public ObservableList<PieChart.Data> getPriorityCountsByUserId(int userId) {
        ObservableList<PieChart.Data> priorities = FXCollections.observableArrayList();
        try {
            PreparedStatement getTasks = connection.prepareStatement(
                "SELECT priority, COUNT(*) AS count FROM tasks WHERE user_id = ? GROUP BY priority");
            getTasks.setInt(1, userId);
            ResultSet rs = getTasks.executeQuery();
            while (rs.next()) {
                String priority = rs.getString("priority");
                int count = rs.getInt("count");
                priorities.add(new PieChart.Data(priority, count));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return priorities;
    }

    public int getTaskIDCount(int userID) {
        try {
            PreparedStatement getAccount = connection.prepareStatement("SELECT count(id) AS count FROM tasks WHERE user_id = ?");
            getAccount.setInt(1, userID);
            ResultSet rs = getAccount.executeQuery();
            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
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
