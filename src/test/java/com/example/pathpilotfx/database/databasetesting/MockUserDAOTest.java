package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MockUserDAOTest {

    @Test
    public void testInsert() {
        MockUserDAO userDAO = new MockUserDAO();
        User user = new User("testUser", "test@example.com", "password", null, 0);
        userDAO.insert(user);
        assertNotNull(userDAO.getByUserId(1));
    }

    @Test
    public void testUpdate() {
        MockUserDAO userDAO = new MockUserDAO();
        User user = new User("testUser", "test@example.com", "password", null, 0);
        userDAO.insert(user);
        user.setUsername("updatedUsername");
        userDAO.update(user);
        assertEquals("updatedUsername", userDAO.getByUserId(1).getUsername());
    }

    @Test
    public void testUpdateEXP() {
        MockUserDAO userDAO = new MockUserDAO();
        User user = new User("testUser", "test@example.com", "password", null, 0);
        userDAO.insert(user);
        userDAO.UpdateEXP(1, 100);
        assertEquals(100, userDAO.getByUserId(1).getExp());
    }

    @Test
    public void testDeleteUser() {
        MockUserDAO userDAO = new MockUserDAO();
        User user = new User("testUser", "test@example.com", "password", null, 0);
        userDAO.insert(user);
        userDAO.deleteUser(1);
        assertNull(userDAO.getByUserId(1));
    }

    @Test
    public void testDeleteAllUsers() {
        MockUserDAO userDAO = new MockUserDAO();
        userDAO.insert(new User("testUser1", "test1@example.com", "password1", null, 0));
        userDAO.insert(new User("testUser2", "test2@example.com", "password2", null, 0));
        userDAO.deleteAllUsers();
        assertEquals(0, userDAO.getAll().size());
    }

    @Test
    public void testIsEmailAvailable_Available() {
        MockUserDAO userDAO = new MockUserDAO();
        userDAO.insert(new User("testUser", "test@example.com", "password", null, 0));
        assertTrue(userDAO.isEmailAvailable("new@example.com"));
    }

    @Test
    public void testIsEmailAvailable_NotAvailable() {
        MockUserDAO userDAO = new MockUserDAO();
        userDAO.insert(new User("testUser", "test@example.com", "password", null, 0));
        assertFalse(userDAO.isEmailAvailable("test@example.com"));
    }

    @Test
    public void testGetStoredHashedPassword_ExistingEmail() {
        MockUserDAO userDAO = new MockUserDAO();
        userDAO.insert(new User("testUser", "test@example.com", "password", null, 0));
        assertEquals("password", userDAO.getStoredHashedPassword("test@example.com"));
    }

    @Test
    public void testGetStoredHashedPassword_NonExistingEmail() {
        MockUserDAO userDAO = new MockUserDAO();
        assertNull(userDAO.getStoredHashedPassword("nonexisting@example.com"));
    }
}
