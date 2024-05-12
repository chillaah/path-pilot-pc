package com.example.pathpilotfx.model;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.todolist.TaskChangeListener;
import com.example.pathpilotfx.database.UserDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task {

    private String task;
    private Integer id;
    private int userID;
    private boolean status;
    private String description;
    private String priority;
    private java.sql.Date datecreated, dueDate;

    private TaskChangeListener taskChangeListener;
    public Task(String task, int user_id, String description, String priority, LocalDate dueDate) {
        this.task = task;
        this.userID = user_id;
        this.status = false;
        this.description = description;
        this.priority = priority;
        this.datecreated = java.sql.Date.valueOf(LocalDate.now());
        // Check if dueDate is not null and is not a past date
        if (dueDate != null && !dueDate.isBefore(LocalDate.now())) {
            this.dueDate = java.sql.Date.valueOf(dueDate);
        }
    }
    public Task(int id, int user_id, String task, boolean status, String description, String priority, LocalDate datecreated, LocalDate dueDate) {
        this.id = id;
        this.task = task;
        this.userID = user_id;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.datecreated = java.sql.Date.valueOf(LocalDate.now());
        // Check if dueDate is not null and is not a past date
        if (dueDate != null && !dueDate.isBefore(LocalDate.now())) {
            this.dueDate = java.sql.Date.valueOf(dueDate);
        }
    }

    public int getID() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public java.sql.Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = new java.sql.Date(datecreated.getTime());
    }

    public java.sql.Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = new java.sql.Date(dueDate.getTime());

    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate != null && !dueDate.isBefore(LocalDate.now())) {
            this.dueDate = java.sql.Date.valueOf(dueDate);
        }
        else{
            this.dueDate = null;
        }
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
