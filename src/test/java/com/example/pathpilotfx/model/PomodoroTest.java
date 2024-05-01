package com.example.pathpilotfx.model;

import javafx.animation.Timeline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PomodoroTest {

    private Pomodoro pomodoro;

    @BeforeEach
    public void setUp() {
        pomodoro = new Pomodoro();
    }

    // @Test
    // public void testDefaultConstructor() {
    //     assertEquals(1500, pomodoro.getSeconds());
    //     assertEquals("25:00", pomodoro.getDisplay());
    //     assertEquals("FOCUS",pomodoro.getType());
    // }

    @Test
    public void testCustomConstructor() {
        pomodoro = new Pomodoro(20, 3);
        assertEquals(1200, pomodoro.getSeconds());
        assertEquals("20:00", pomodoro.getDisplay());
        assertEquals("FOCUS",pomodoro.getType());
    }

    // @Test
    // public void testDecreaseSeconds() {
    //     pomodoro.decreaseSeconds();
    //     assertEquals(1499, pomodoro.getSeconds());
    //     assertEquals("24:59", pomodoro.getDisplay());
    // }

    // @Test
    // public void testResetTimer() {
    //     pomodoro.decreaseSeconds();
    //     pomodoro.resetTimer();
    //     assertEquals(1500, pomodoro.getSeconds());
    //     assertEquals("25:00", pomodoro.getDisplay());
    //     assertEquals("FOCUS",pomodoro.getType());
    // }

    @Test
    public void testToggleType() {
        assertEquals("FOCUS",pomodoro.getType());
        assertEquals("BREAK", pomodoro.toggleType());
        assertEquals("BREAK",pomodoro.getType());
        assertEquals("FOCUS", pomodoro.toggleType());
        assertEquals("FOCUS",pomodoro.getType());
    }

    @Test
    public void testGetType() {
        assertEquals("FOCUS", pomodoro.getType());
    }

    @Test
    public void testSetTimeline() {
        Timeline timeline = pomodoro.setTimeline();
        assertNotNull(timeline);
    }

    @Test
    public void testZeroSeconds() {
        pomodoro = new Pomodoro(0, 0);
        assertEquals(0, pomodoro.getSeconds());
        assertEquals("00:00", pomodoro.getDisplay());
    }

//    @Test
//    public void testNegativeSeconds() {
//        pomodoro = new Pomodoro(-5, -2);
//        assertEquals(0, pomodoro.getSeconds());
//        assertEquals("00:00", pomodoro.getDisplay());
//    }

//    @Test
//    public void testVeryLargeNumbers() {
//        pomodoro = new Pomodoro(1000, 500);
//        assertEquals(6000, pomodoro.getSeconds());
//        assertEquals("100:00", pomodoro.getDisplay());
//    }


}
