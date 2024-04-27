package com.example.pathpilotfx.model;
import java.sql.Date;

public class TaskV {
    private int userID;
    private int taskID;
    private String taskName;
    private String description;
    private Date deadline;
    private Date reminder;
    private boolean isSelected;
    private int assignmentID;
    private String status;
    private String category;
    private String priority;
    private String extraNotes;

    public TaskV(int userID, int taskID, String taskName, String description, Date deadline, Date reminder, boolean isSelected, int assignmentID, String status, String category, String priority, String extraNotes) {
        this.userID = userID;
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.deadline = deadline;
        this.reminder = reminder;
        this.isSelected = isSelected;
        this.assignmentID = assignmentID;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.extraNotes = extraNotes;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getReminder() {
        return reminder;
    }

    public void setReminder(Date reminder) {
        this.reminder = reminder;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getExtraNotes() {
        return extraNotes;
    }

    public void setExtraNotes(String extraNotes) {
        this.extraNotes = extraNotes;
    }

    @Override
    public String toString() {
        return "Task{" +
                "userID=" + userID +
                ", taskID=" + taskID +
                ", assignmentID=" + assignmentID +
                ", taskName='" + taskName + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", reminder=" + reminder +
                ", isSelected=" + isSelected +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                ", priority='" + priority + '\'' +
                ", extraNotes='" + extraNotes + '\'' +
                '}';
    }
}
