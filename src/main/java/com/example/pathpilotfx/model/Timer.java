package com.example.pathpilotfx.model;

public class Timer {
    private int userID;
    private int timerID;
    private int breakDuration;
    private int workDuration;

    public Timer(int userID, int timerID, int breakDuration, int workDuration) {
        this.userID = userID;
        this.timerID = timerID;
        this.breakDuration = breakDuration;
        this.workDuration = workDuration;
    }
    public Timer(int userID, int breakDuration, int workDuration) {
        this.userID = userID;
        this.breakDuration = breakDuration;
        this.workDuration = workDuration;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTimerID() {
        return timerID;
    }

    public void setTimerID(int timerID) {
        this.timerID = timerID;
    }

    public int getBreakDuration() {
        return breakDuration;
    }

    public void setBreakDuration(int breakDuration) {
        this.breakDuration = breakDuration;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }
}