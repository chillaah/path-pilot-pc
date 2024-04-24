package com.example.data;

import java.sql.Date;

public class Session {
    private int userID;
    private int sessionID;
    private Date sessionStart;
    private Date sessionEnd;
    private int sessionLength;

    public Session(int userID, int sessionID, Date sessionStart, Date sessionEnd, int sessionLength) {
        this.userID = userID;
        this.sessionID = sessionID;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.sessionLength = sessionLength;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public Date getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    public Date getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public int getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }

    @Override
    public String toString() {
        return "Session{" +
                "userID=" + userID +
                ", sessionID=" + sessionID +
                ", sessionStart=" + sessionStart +
                ", sessionEnd=" + sessionEnd +
                ", sessionLength=" + sessionLength +
                '}';
    }
}
