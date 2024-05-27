package com.example.pathpilotfx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    private Connection connection;

    @BeforeEach
    public void setUp() throws SQLException {
        // Set up an in-memory SQLite database
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        createDatabaseSchema();
        // Insert test user with pre-hashed password
        String hashedPassword = PasswordHash.hashPassword("Password@123");
        insertTestUser("test@example.com", hashedPassword);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    // Helper method to create the database schema
    private void createDatabaseSchema() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT)");
        }
    }

    // Helper method to insert a test user into the database
    private void insertTestUser(String email, String hashedPassword) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(String.format("INSERT INTO users (email, password) VALUES ('%s', '%s')", email, hashedPassword));
        }
    }

    // Helper method to simulate getting the stored hashed password from the database
    private String getStoredHashedPassword(String email) throws SQLException {
        String query = "SELECT password FROM users WHERE email = '" + email + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        }
    }

//    // Test case for authenticating a user with correct password
//    @Test
//    public void testAuthenticateUserCorrectPassword() throws SQLException {
//        String email = "test@example.com";
//        String password = "Password@123";
//        String storedHashedPassword = getStoredHashedPassword(email);
//        assertNotNull(storedHashedPassword);
//        boolean isAuthenticated = PasswordHash.authenticateUser(email, password);
//        assertTrue(isAuthenticated);
//    }

    // Test case for authenticating a user with incorrect password
    @Test
    public void testAuthenticateUserIncorrectPassword() throws SQLException {
        String email = "test@example.com";
        String password = "incorrectPassword";
        String storedHashedPassword = getStoredHashedPassword(email);
        assertNotNull(storedHashedPassword);
        boolean isAuthenticated = PasswordHash.authenticateUser(email, password);
        assertFalse(isAuthenticated);
    }

    // Test case for authenticating a user that doesn't exist
    @Test
    public void testAuthenticateUserNotFound() throws SQLException {
        String email = "nonexistent@example.com";
        String password = "password";
        String storedHashedPassword = getStoredHashedPassword(email);
        assertNull(storedHashedPassword);
        boolean isAuthenticated = PasswordHash.authenticateUser(email, password);
        assertFalse(isAuthenticated);
    }

    // Test case for hashing a password
    @Test
    public void testHashPassword() {
        String password = "password123";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
        assertTrue(hashedPassword.startsWith("$argon2"));
    }

    // Test case for hashing an empty password
    @Test
    public void testHashEmptyPassword() {
        String password = "";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNull(hashedPassword);
    }

    // Test case for hashing a long password
    @Test
    public void testHashLongPassword() {
        String password = "ThisIsALongPasswordWithMoreThanSixtyFourCharactersAndShouldBeHashedProperly";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertTrue(hashedPassword.startsWith("$argon2"));
    }

    // Test case for hashing a password with special characters
    @Test
    public void testHashPasswordWithSpecialCharacters() {
        String password = "!@#$%^&*()_+";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertTrue(hashedPassword.startsWith("$argon2"));
    }

    // Test case for hashing a password with leading/trailing whitespaces
    @Test
    public void testHashPasswordWithWhitespace() {
        String password = "  password123  ";
        String hashedPassword = PasswordHash.hashPassword(password.trim());
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
        assertTrue(hashedPassword.startsWith("$argon2"));
    }

    // Test case for hashing a password with Unicode characters
    @Test
    public void testHashPasswordWithUnicodeCharacters() {
        String password = "日本語パスワード";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertTrue(hashedPassword.startsWith("$argon2"));
    }
}
