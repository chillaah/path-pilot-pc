package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.User;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGetUserID() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        assertEquals(1, user.getUserID());
    }

    @Test
    void testGetUsername() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        assertEquals("username", user.getUsername());
    }

    @Test
    void testGetEmail() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        assertEquals("email@example.com", user.getEmail());
    }

    @Test
    void testGetPassword() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        assertEquals("password", user.getPassword());
    }

    @Test
    void testGetCreationDate() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        assertNull(user.getCreationDate());
    }

    @Test
    void testGetExp() {
        User user = new User(1, "username", "email@example.com", "password", null, 100);
        assertEquals(100, user.getExp());
    }

    @Test
    void testSetUserID() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);

        user.setUserID(2);
        assertEquals(2, user.getUserID());
    }

    @Test
    void testSetUsername() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        user.setUsername("newUsername");
        assertEquals("newUsername", user.getUsername());
    }

    @Test
    void testSetEmail() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        user.setEmail("newemail@example.com");
        assertEquals("newemail@example.com", user.getEmail());
    }

    @Test
    void testSetExp() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        user.setExp(200);
        assertEquals(200, user.getExp());
    }

    @Test
    void testConstructorAndGetters() {
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "username", "email@example.com", "password", creationDate, 100);

        assertEquals(1, user.getUserID());
        assertEquals("username", user.getUsername());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(creationDate, user.getCreationDate());
        assertEquals(100, user.getExp());
    }

    @Test
    void testToString() {
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "username", "email@example.com", "password", creationDate, 100);

        String expectedToString = "User{userID=1, username='username', email='email@example.com', password='password', exp=100, creationDate=" + creationDate + '}';
        assertEquals(expectedToString, user.toString());
    }

    @Test
    void testSetPassword() {
        User user = new User(1, "username", "email@example.com", "password", null, 0);
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testConstructorWithUserIDOnly() {
        User user = new User(1);
        assertEquals(1, user.getUserID());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getCreationDate());
        assertEquals(0, user.getExp());
    }

    @Test
    void testSetCreationDate() {
        Timestamp creationDate = new Timestamp(System.currentTimeMillis());
        User user = new User(1, "username", "email@example.com", "password", null, 0);

        user.setCreationDate(creationDate);
        assertEquals(creationDate, user.getCreationDate());
    }
}
