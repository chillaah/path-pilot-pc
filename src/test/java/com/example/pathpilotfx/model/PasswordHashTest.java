package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.PasswordHash;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordHashTest {

//    // Test case for authenticating a user with correct password
//    @Test
//    public void testAuthenticateUserCorrectPassword() {
//        assertTrue(PasswordHash.authenticateUser("test@example.com", "Password@123"));
//    }

    // Test case for authenticating a user with incorrect password
    @Test
    public void testAuthenticateUserIncorrectPassword() {
        assertFalse(PasswordHash.authenticateUser("test@example.com", "incorrectPassword"));
    }

    // Test case for authenticating a user that doesn't exist
    @Test
    public void testAuthenticateUserNotFound() {
        assertFalse(PasswordHash.authenticateUser("nonexistent@example.com", "password"));
    }

    // Test case for hashing a password
    @Test
    public void testHashPassword() {
        String password = "password123";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    // Test case for hashing an empty password
    @Test
    public void testHashEmptyPassword() {
        String password = "";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertEquals(64, hashedPassword.length()); // SHA-256 hash length is 64 characters
    }

//     Test case for hashing a null password
//    @Test
//    public void testHashNullPassword() {
//        assertNull(PasswordHash.hashPassword(null));
//    }

    // Test case for hashing a long password
    @Test
    public void testHashLongPassword() {
        String password = "ThisIsALongPasswordWithMoreThanSixtyFourCharactersAndShouldBeHashedProperly";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertEquals(64, hashedPassword.length()); // SHA-256 hash length is 64 characters
    }

    // Test case for hashing a password with special characters
    @Test
    public void testHashPasswordWithSpecialCharacters() {
        String password = "!@#$%^&*()_+";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertEquals(64, hashedPassword.length()); // SHA-256 hash length is 64 characters
    }

    // Test case for hashing a password with leading/trailing whitespaces
    @Test
    public void testHashPasswordWithWhitespace() {
        String password = "  password123  ";
        String hashedPassword = PasswordHash.hashPassword(password.trim());
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    // Test case for hashing a password with Unicode characters
    @Test
    public void testHashPasswordWithUnicodeCharacters() {
        String password = "日本語パスワード";
        String hashedPassword = PasswordHash.hashPassword(password);
        assertNotNull(hashedPassword);
        assertEquals(64, hashedPassword.length()); // SHA-256 hash length is 64 characters
    }
}
