package com.example.pathpilotfx.controller.authentication;
/**
 Class that keeps track of the logged-in user
 **/
public class SessionManager {

    // Variable to store the ID of the logged-in user
    private static int loggedInUserId;

    /**
     * Retrieves the ID of the logged-in user.
     *
     * @return The ID of the logged-in user.
     */
    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    /**
     * Sets the ID of the logged-in user.
     *
     * @param userId The ID of the logged-in user to be set.
     */
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
}
