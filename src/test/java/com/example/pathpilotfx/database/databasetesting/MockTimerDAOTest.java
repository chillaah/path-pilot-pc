package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Timer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MockTimerDAOTest {

    @Test
    public void testInsert() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        Timer timer = new Timer(1, 1, "Monday",30);
        timerDAO.insert(timer);
        assertNotNull(timerDAO.getByTimerId(1));
    }

    @Test
    public void testUpdate() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        Timer timer = new Timer(1, 1, "Monday",30);
        timerDAO.insert(timer);
        timer.setDayName("Tuesday");
        timerDAO.update(timer);
        assertEquals("Tuesday", timerDAO.getByTimerId(1).getDayName());
    }

    @Test
    public void testDeleteTimer() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        Timer timer = new Timer(1, 1, "Monday",30);
        timerDAO.insert(timer);
        timerDAO.deleteTimer(1);
        assertNull(timerDAO.getByTimerId(1));
    }

    @Test
    public void testGetAll() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        Timer timer1 = new Timer(1, 1, "Monday",30);
        Timer timer2 = new Timer(1, 2, "Wednesday",45);
        timerDAO.insert(timer1);
        timerDAO.insert(timer2);
        assertEquals(2, timerDAO.getAll().size());
    }

    @Test
    public void testGetByTimerId_ValidId() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        Timer timer = new Timer(1, 1, "Monday",30);
        timerDAO.insert(timer);
        assertNotNull(timerDAO.getByTimerId(1));
    }

    @Test
    public void testGetByTimerId_InvalidId() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        assertNull(timerDAO.getByTimerId(100));
    }


    @Test
    public void testGetByTimerId_NonExistentId() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        assertNull(timerDAO.getByTimerId(999));
    }

    @Test
    public void testGetByTimerId_ZeroId() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        assertNull(timerDAO.getByTimerId(0));
    }

    @Test
    public void testGetByTimerId_NegativeId() {
        MockTimerDAO timerDAO = new MockTimerDAO();
        assertNull(timerDAO.getByTimerId(-1));
    }
}
