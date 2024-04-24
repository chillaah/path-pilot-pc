package com.example.authentication;

import com.example.data.UserDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    static UserDAO db = new UserDAO();
    public static boolean authenticateUser(String email, String providedPassword) {
        // Retrieve the hashed password from the database
        String storedHashedPassword = db.getStoredHashedPassword(email);

        if (storedHashedPassword == null) {
            // User not found in the database
            return false;
        }

        // Hash the provided password using the same algorithm and salt
        String hashedPassword = hashPassword(providedPassword);

        // Compare the hashed passwords
        assert hashedPassword != null;
        return hashedPassword.equals(storedHashedPassword);
    }
    public static String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hashed bytes
            byte[] hashedBytes = md.digest();

            // Convert hashed bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle error (e.g., algorithm not found)
            e.printStackTrace();
            return null;
        }
    }
}
