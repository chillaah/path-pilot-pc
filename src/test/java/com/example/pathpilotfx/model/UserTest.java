package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.User;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    public void testGetUserID() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        assertEquals(1, user.getUserID());
    }

    @Test
    public void testSetUsername() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        user.setUsername("jane_doe");
        assertEquals("jane_doe", user.getUsername());
    }

    @Test
    public void testGetEmail() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    public void testSetPassword() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        user.setPassword("new_password");
        assertEquals("new_password", user.getPassword());
    }

    @Test
    public void testGetExp() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        assertEquals(100, user.getExp());
    }

    @Test
    public void testSetCreationDate() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        User user = new User(1, "john_doe", "john@example.com", "password123", now, 100);
        Timestamp newCreationDate = Timestamp.valueOf(LocalDateTime.of(2022, 4, 30, 0, 0));
        user.setCreationDate(newCreationDate);
        assertEquals(newCreationDate, user.getCreationDate());
    }

    @Test
    public void testSetUserID() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        user.setUserID(2);
        assertEquals(2, user.getUserID());
    }

    @Test
    public void testGetUsername() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        assertEquals("john_doe", user.getUsername());
    }

    @Test
    public void testSetEmail() {
        User user = new User(1, "john_doe", "john@example.com", "password123", Timestamp.valueOf(LocalDateTime.now()), 100);
        user.setEmail("jane@example.com");
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    public void testToString() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        User user = new User(1, "john_doe", "john@example.com", "password123", now, 100);
        String expectedString = "User{userID=1, username='john_doe', email='john@example.com', password='password123', exp=100, creationDate=" + now.toString() + '}';
        assertEquals(expectedString, user.toString());
    }
}
