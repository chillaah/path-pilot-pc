package com.example.pathpilotfx.controller.authentication;

public class SessionManager {
    private static int loggedInUserId;

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
