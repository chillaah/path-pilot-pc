package com.example.pathpilotfx.model;

import java.sql.Timestamp;


/**
 Class that initialises User Object
 **/
public class User {

    private int userID;
    private String username;
    private String email;
    private String password;
    private Timestamp creationDate;
    private int exp;

    /**
     * Constructs a new User object with the specified parameters.
     *
     * @param userID       The user's ID.
     * @param username     The user's username.
     * @param email        The user's email address.
     * @param password     The user's password.
     * @param creationDate The timestamp representing the user's creation date.
     * @param exp          The user's experience points.
     */
    public User(int userID, String username, String email, String password, Timestamp creationDate, int exp) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.exp = exp;
    }

    /**
     * Constructs a new User object with the specified parameters.
     *
     * @param username     The user's username.
     * @param email        The user's email address.
     * @param password     The user's password.
     * @param creationDate The timestamp representing the user's creation date.
     * @param exp          The user's experience points.
     */
    public User(String username, String email, String password, Timestamp creationDate, int exp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.exp = exp;
    }

    /**
     * Constructs a new User object with the specified user ID.
     *
     * @param userID The user's ID.
     */
    public User(int userID)
    {
        this.userID = userID;
    }

    // Getter and setter methods for userID, username, email, password, exp, and creationDate

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Returns a string representation of the User object.
     * The string includes the user's ID, username, email, password, experience points, and creation date.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", exp=" + exp +
                ", creationDate=" + creationDate +
                '}';
    }
}
