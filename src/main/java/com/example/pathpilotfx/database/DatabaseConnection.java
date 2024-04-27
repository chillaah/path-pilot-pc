package com.example.pathpilotfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static Connection instance = null;
    public static final String dbURL = "jdbc:sqlite:pathpilotpc.db";

    private DatabaseConnection() {
        try {
            instance = DriverManager.getConnection(dbURL);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}
