package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUserDAO {
    private Map<Integer, User> users = new HashMap<>();
    private int nextUserId = 1;


    public void insert(User user) {
        int userId = nextUserId++;
        user.setUserID(userId);
        users.put(userId, user);
    }

    public void update(User user) {
        users.put(user.getUserID(), user);
    }

    public void UpdateEXP(int userID, int exp) {
        User user = users.get(userID);
        if (user != null) {
            user.setExp(exp);
            users.put(userID, user);
        }
    }

    public void deleteUser(int id) {
        users.remove(id);
    }

    public void deleteAllUsers() {
        users.clear();
    }

    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }

    public User getByUserId(int id) {
        return users.get(id);
    }


    //getLatestID and getLatestUser cannot be done here

    public boolean isEmailAvailable(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    public String getStoredHashedPassword(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user.getPassword();
            }
        }
        return null;
    }


}
