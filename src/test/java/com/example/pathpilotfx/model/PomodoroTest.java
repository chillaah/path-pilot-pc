package com.example.pathpilotfx.model;

import javafx.animation.Timeline;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PomodoroTest {

    private Pomodoro pomodoro;

//    @BeforeEach
//    public void setUp() {
//        pomodoro = new Pomodoro();
//    }

    @Test
    public void testDefaultConstructor() {
        pomodoro = new Pomodoro();
        assertNotNull(pomodoro); // Ensure the object is not null
        assertEquals(1500, pomodoro.getSeconds());
        assertEquals("25:00", pomodoro.getDisplay());
        assertEquals("FOCUS", pomodoro.getType());
    }

    @Test
    public void testCustomConstructor() {
        pomodoro = new Pomodoro(20, 3);
        assertEquals(1200, pomodoro.getSeconds());
        assertEquals("20:00", pomodoro.getDisplay());
        assertEquals("FOCUS", pomodoro.getType());
    }

    @Test
    public void testDecreaseSeconds() {
        pomodoro = new Pomodoro();
        int initialSeconds = pomodoro.getSeconds();
        pomodoro.decreaseSeconds();
        assertEquals(initialSeconds - 1, pomodoro.getSeconds()); // Check if seconds decreased by 1
    }

    @Test
    public void testResetTimer() {
        pomodoro = new Pomodoro();
        pomodoro.decreaseSeconds();
        pomodoro.resetTimer();
        assertEquals(1500, pomodoro.getSeconds()); // Check if seconds reset to default
        assertEquals("25:00", pomodoro.getDisplay());
        assertEquals("FOCUS", pomodoro.getType());
    }

    @Test
    public void testToggleType() {
        pomodoro = new Pomodoro();
        assertEquals("FOCUS", pomodoro.getType());
        assertEquals("BREAK", pomodoro.toggleType());
        assertEquals("BREAK", pomodoro.getType());
        assertEquals("FOCUS", pomodoro.toggleType());
        assertEquals("FOCUS", pomodoro.getType());
    }

    @Test
    public void testZeroSeconds() {
        pomodoro = new Pomodoro(0, 0);
        assertEquals(0, pomodoro.getSeconds());
        assertEquals("00:00", pomodoro.getDisplay());
    }

    @Test
    public void testNegativeSeconds() {
        pomodoro = new Pomodoro(-5, -2);
        assertEquals(0, pomodoro.getSeconds());
        assertEquals("00:00", pomodoro.getDisplay());
    }

    @Test
    public void testVeryLargeNumbers() {
        pomodoro = new Pomodoro(1000, 500);
        assertEquals(60000, pomodoro.getSeconds());
        assertEquals("1000:00", pomodoro.getDisplay());
    }

    @Test
    public void testGetSessionCount() {
        pomodoro = new Pomodoro();
        assertEquals(0, pomodoro.getSessionCount());
    }

    @Test
    public void testGetRest() {
        pomodoro = new Pomodoro();
        assertEquals(5, pomodoro.getRest());
    }

    @Test
    public void testGetWork() {
        pomodoro = new Pomodoro();
        assertEquals(25, pomodoro.getWork());
    }

    @Test
    public void testGetTimerType() {
        pomodoro = new Pomodoro();
        assertEquals("FOCUS", pomodoro.getTimerType());
    }

    @Test
    public void testSetSeconds() {
        pomodoro = new Pomodoro();
        pomodoro.setSeconds(200);
        assertEquals(200, pomodoro.getSeconds());
    }

    @Test
    public void testSetDisplay() {
        pomodoro = new Pomodoro();
        pomodoro.setDisplay("10:00");
        assertEquals("10:00", pomodoro.getDisplay());
    }

    @Test
    public void testSetSessionCount() {
        pomodoro = new Pomodoro();
        pomodoro.setSessionCount(3);
        assertEquals(3, pomodoro.getSessionCount());
    }

    @Test
    public void testSetRest() {
        pomodoro = new Pomodoro();
        pomodoro.setRest(7);
        assertEquals(7, pomodoro.getRest());
    }

    @Test
    public void testSetWork() {
        pomodoro = new Pomodoro();
        pomodoro.setWork(30);
        assertEquals(30, pomodoro.getWork());
    }
}
