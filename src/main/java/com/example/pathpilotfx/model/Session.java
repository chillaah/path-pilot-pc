package com.example.pathpilotfx.model;

import java.sql.Date;

/**
 Class that initialises Session Object
 **/
public class Session {
    private int userID;
    private int sessionID;
    private Date sessionStart;
    private Date sessionEnd;
    private int sessionLength;

    /**
     * Constructs a session object with the specified attributes.
     *
     * @param userID        The ID of the user associated with the session.
     * @param sessionID     The ID of the session.
     * @param sessionStart  The start time of the session.
     * @param sessionEnd    The end time of the session.
     * @param sessionLength The duration of the session.
     */
    public Session(int userID, int sessionID, Date sessionStart, Date sessionEnd, int sessionLength) {
        this.userID = userID;
        this.sessionID = sessionID;
        this.sessionStart = sessionStart;
        this.sessionEnd = sessionEnd;
        this.sessionLength = sessionLength;
    }

    /**
     * Gets the ID of the user associated with the session.
     *
     * @return The user ID.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the ID of the user associated with the session.
     *
     * @param userID The user ID to set.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the ID of the session.
     *
     * @return The session ID.
     */
    public int getSessionID() {
        return sessionID;
    }

    /**
     * Sets the ID of the session.
     *
     * @param sessionID The session ID to set.
     */
    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * Gets the start time of the session.
     *
     * @return The start time of the session.
     */
    public Date getSessionStart() {
        return sessionStart;
    }

    /**
     * Sets the start time of the session.
     *
     * @param sessionStart The start time to set.
     */
    public void setSessionStart(Date sessionStart) {
        this.sessionStart = sessionStart;
    }

    /**
     * Gets the end time of the session.
     *
     * @return The end time of the session.
     */
    public Date getSessionEnd() {
        return sessionEnd;
    }

    /**
     * Sets the end time of the session.
     *
     * @param sessionEnd The end time to set.
     */
    public void setSessionEnd(Date sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    /**
     * Gets the duration of the session.
     *
     * @return The duration of the session.
     */
    public int getSessionLength() {
        return sessionLength;
    }

    /**
     * Sets the duration of the session.
     *
     * @param sessionLength The duration to set.
     */
    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }

    /**
     * Returns a string representation of the session object.
     *
     * @return A string representation of the session.
     */
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
