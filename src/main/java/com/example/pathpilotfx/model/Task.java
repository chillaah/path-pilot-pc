package com.example.pathpilotfx.model;

import com.example.pathpilotfx.controller.todolist.TaskChangeListener;
import java.time.LocalDate;
import java.util.Date;


/**
 * Represents a task in the to-do list.
 */
public class Task {

    private String task;
    private Integer id;
    private int userID;
    private boolean status;
    private String description;
    private String priority;
    private java.sql.Date datecreated, dueDate;

    private TaskChangeListener taskChangeListener;

    /**
     * Constructs a new task.
     *
     * @param task        The name of the task.
     * @param user_id     The ID of the user associated with the task.
     * @param description The description of the task.
     * @param priority    The priority of the task.
     * @param dueDate     The due date of the task.
     */
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

    /**
     * Constructs a new task with an ID.
     *
     * @param id          The ID of the task.
     * @param user_id     The ID of the user associated with the task.
     * @param task        The name of the task.
     * @param status      The status of the task.
     * @param description The description of the task.
     * @param priority    The priority of the task.
     * @param datecreated The creation date of the task.
     * @param dueDate     The due date of the task.
     */
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

    /**
     * Retrieves the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the ID of the task.
     *
     * @param id The ID of the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the Task of the task.
     *
     * @return The Task of the task.
     */
    public String getTask() {
        return task;
    }

    /**
     * Retrieves the Task of the task.
     *
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return The status of the task.
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * Retrieves the status of the task.
     *
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     */
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

    /**
     * Retrieves the due date of the task.
     *
     * @return The due date of the task.
     */
    public java.sql.Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the task.
     *
     * @param dueDate The due date of the task.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = new java.sql.Date(dueDate.getTime());

    }

    /**
     * Sets the due date of the task from a LocalDate object.
     *
     * @param dueDate The due date of the task.
     */
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
