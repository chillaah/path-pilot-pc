package com.example.pathpilotfx.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExplorationTest {

    @Test
    public void testConstructor() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertNotNull(exploration);
    }

    @Test
    public void testGetUserID() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertEquals(1, exploration.getUserID());
    }

    @Test
    public void testSetUserID() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        exploration.setUserID(2);
        assertEquals(2, exploration.getUserID());
    }

    @Test
    public void testGetCountryID() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertEquals(100, exploration.getCountryID());
    }

    @Test
    public void testSetCountryID() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        exploration.setCountryID(200);
        assertEquals(200, exploration.getCountryID());
    }

    @Test
    public void testGetStatus() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertEquals("InProgress", exploration.getStatus());
    }

    @Test
    public void testSetStatus() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        exploration.setStatus("Completed");
        assertEquals("Completed", exploration.getStatus());
    }

    @Test
    public void testIsLocked() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertFalse(exploration.isLocked());
    }

    @Test
    public void testSetLocked() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        exploration.setLocked(true);
        assertTrue(exploration.isLocked());
    }

    @Test
    public void testIsFavourited() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        assertTrue(exploration.isFavourite());
    }

    @Test
    public void testSetFavourited() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        exploration.setFavourite(false);
        assertFalse(exploration.isFavourite());
    }

    @Test
    public void testToString() {
        Exploration exploration = new Exploration(1, 100, "InProgress", false, true);
        String expectedToString = "Exploration{userID=1, countryID=100, status='InProgress', isLocked=false, isFavourite=true}";
        assertEquals(expectedToString, exploration.toString());
    }
}
