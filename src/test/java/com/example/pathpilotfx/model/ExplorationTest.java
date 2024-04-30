package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Exploration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExplorationTest {

    @Test
    public void testUserIDGetterAndSetter() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setUserID(3);

        // Then
        assertEquals(3, exploration.getUserID());
    }

    @Test
    public void testCountryIDGetterAndSetter() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setCountryID(4);

        // Then
        assertEquals(4, exploration.getCountryID());
    }

    @Test
    public void testStatusGetterAndSetter() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setStatus("Completed");

        // Then
        assertEquals("Completed", exploration.getStatus());
    }

    @Test
    public void testIsLockedGetterAndSetter() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setLocked(true);

        // Then
        assertTrue(exploration.isLocked());
    }

    @Test
    public void testIsFavouritedGetterAndSetter() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setFavourited(false);

        // Then
        assertFalse(exploration.isFavourited());
    }

    @Test
    public void testToString() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // Then
        String expected = "Exploration{userID=1, countryID=2, status='Exploring', isLocked=false, isFavourited=true}";
        assertEquals(expected, exploration.toString());
    }

    @Test
    public void testChainedGettersAndSetters() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setUserID(3);
        exploration.setCountryID(4);
        exploration.setStatus("Completed");
        exploration.setLocked(true);
        exploration.setFavourited(false);

        // Then
        assertEquals(3, exploration.getUserID());
        assertEquals(4, exploration.getCountryID());
        assertEquals("Completed", exploration.getStatus());
        assertTrue(exploration.isLocked());
        assertFalse(exploration.isFavourited());
    }

    @Test
    public void testMultipleSettersAndGetters() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setUserID(3);
        exploration.setCountryID(4);
        exploration.setStatus("Completed");
        exploration.setLocked(true);
        exploration.setFavourited(false);

        // Then
        assertEquals(3, exploration.getUserID());
        assertEquals(4, exploration.getCountryID());
        assertEquals("Completed", exploration.getStatus());
        assertTrue(exploration.isLocked());
        assertFalse(exploration.isFavourited());
    }

    @Test
    public void testCombinedSettersAndGetters() {
        // Given
        Exploration exploration = new Exploration(1, 2, "Exploring", false, true);

        // When
        exploration.setUserID(3);
        exploration.setCountryID(4);
        exploration.setStatus("Completed");
        exploration.setLocked(true);
        exploration.setFavourited(false);

        // Then
        assertEquals(3, exploration.getUserID());
        assertEquals(4, exploration.getCountryID());
        assertEquals("Completed", exploration.getStatus());
        assertTrue(exploration.isLocked());
        assertFalse(exploration.isFavourited());
    }
}
