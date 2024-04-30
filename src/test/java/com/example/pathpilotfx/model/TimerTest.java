package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Timer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimerTest {

    @Test
    public void testPositiveDuration() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        assertEquals(60, timer.getDuration());
    }

    @Test
    public void testZeroDuration() {
        Timer timer = new Timer(2, 1002, "Tuesday", 0);
        assertEquals(0, timer.getDuration());
    }

    @Test
    public void testNegativeDuration() {
        Timer timer = new Timer(3, 1003, "Wednesday", -30);
        assertEquals(-30, timer.getDuration());
    }

    @Test
    public void testSetDifferentUserID() {
        Timer timer = new Timer(4, 1004, "Thursday", 45);
        timer.setUserID(5);
        assertEquals(5, timer.getUserID());
    }
    @Test
    public void testGetUserID() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        assertEquals(1, timer.getUserID());
    }

    @Test
    public void testSetTimerID() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        timer.setTimerID(2002);
        assertEquals(2002, timer.getTimerID());
    }

    @Test
    public void testGetDayName() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        assertEquals("Monday", timer.getDayName());
    }

    @Test
    public void testSetDayName() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        timer.setDayName("Tuesday");
        assertEquals("Tuesday", timer.getDayName());
    }

    @Test
    public void testSetDuration() {
        Timer timer = new Timer(1, 1001, "Monday", 60);
        timer.setDuration(90);
        assertEquals(90, timer.getDuration());
    }


    // Add more test cases for other scenarios...
}
