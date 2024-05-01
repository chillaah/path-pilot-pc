package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockSessionDAO;
import com.example.pathpilotfx.model.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockSessionDAOTest {

    private MockSessionDAO mockSessionDAO;

    @BeforeEach
    public void setUp() {
        mockSessionDAO = new MockSessionDAO();
    }

    @Test
    public void testInsert() {
        Session session = new Session(1, 1,
            Date.valueOf("2024-04-30"), Date.valueOf("2024-05-01"), 2);
        mockSessionDAO.insert(session);
        assertNotNull(mockSessionDAO.getBySessionId(session.getSessionID()));
    }

    @Test
    public void testUpdate() {
        Session session = new Session(1, 1,
                Date.valueOf("2024-04-30"),
                Date.valueOf("2024-05-01"), 2);
        mockSessionDAO.insert(session);
        session.setSessionLength(3);
        mockSessionDAO.update(session);
        assertEquals(3, mockSessionDAO.getBySessionId(session.getSessionID()).getSessionLength());
    }

    @Test
    public void testDeleteSession() {
        Session session1 = new Session(1, 1,
                Date.valueOf("2024-04-30"), Date.valueOf("2024-05-01"), 2);
        Session session2 = new Session(1, 2,
                Date.valueOf("2024-07-27"), Date.valueOf("2024-07-28"), 2);
        mockSessionDAO.insert(session1);
        mockSessionDAO.insert(session2);
        mockSessionDAO.deleteSession(session1.getSessionID());
        assertNull(mockSessionDAO.getBySessionId(session1.getSessionID()));
        assertNotNull(mockSessionDAO.getBySessionId(session2.getSessionID()));
    }

    @Test
    public void testGetAll() {
        Session session1 = new Session(1, 1,
                Date.valueOf("2024-04-30"), Date.valueOf("2024-05-01"), 2);
        Session session2 = new Session(1, 2,
                Date.valueOf("2024-07-27"), Date.valueOf("2024-07-28"), 2);
        mockSessionDAO.insert(session1);
        mockSessionDAO.insert(session2);
        List<Session> allSessions = mockSessionDAO.getAll();
        assertEquals(2, allSessions.size());
    }

    @Test
    public void testGetBySessionId() {
        Session session = new Session(1, 1,
                Date.valueOf("2024-04-30"), Date.valueOf("2024-05-01"), 2);
        mockSessionDAO.insert(session);
        assertNotNull(mockSessionDAO.getBySessionId(session.getSessionID()));
    }

    @Test
    public void testGetBySessionId_NotFound() {
        assertNull(mockSessionDAO.getBySessionId(100));
    }
}
