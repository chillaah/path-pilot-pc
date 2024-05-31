package com.example.pathpilotfx.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 Class to connect to database through an instance
 **/
public class DatabaseConnection {
    private static Connection instance = null;
    public static final String dbURL = "jdbc:sqlite:pathpilotpc.db";

    /**
     Gets database connection
     **/
    private DatabaseConnection() {
        try {
            instance = DriverManager.getConnection(dbURL);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     Sets database connection
     **/
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}
