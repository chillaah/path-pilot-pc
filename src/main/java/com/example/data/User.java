<<<<<<< Updated upstream
package main.java.com.example.data;
import java.sql.Date;
=======
package com.example.demo;
import java.sql.Timestamp;

>>>>>>> Stashed changes
public class User {

    private int userID;
    private String username;
    private String email;
    private String password;
    private Timestamp creationDate;
    private int exp;

    public User(int userID, String username, String email, String password, Timestamp creationDate, int exp) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creationDate = creationDate;
        this.exp = exp;
    }

    public User(int userID)
    {
        this.userID = userID;
    }

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
