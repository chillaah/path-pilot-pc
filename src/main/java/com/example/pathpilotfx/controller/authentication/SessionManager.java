package com.example.pathpilotfx.controller.authentication;
/**
 Class that keeps track of the logged-in user
 **/
public class SessionManager {
    private static int loggedInUserId;
    /**
     Method that gets the logged-in user
     **/
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }
    /**
     Method that sets the logged-in user
     **/
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
