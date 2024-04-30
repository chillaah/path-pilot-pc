package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Stamp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StampTest {

    @Test
    public void testStampIDGetterAndSetter() {
        // Given
        Stamp stamp = new Stamp(1, 2, true);

        // When
        stamp.setStampID(3);

        // Then
        assertEquals(3, stamp.getStampID());
    }

    @Test
    public void testUserIDGetterAndSetter() {
        // Given
        Stamp stamp = new Stamp(1, 2, true);

        // When
        stamp.setUserID(4);

        // Then
        assertEquals(4, stamp.getUserID());
    }

    @Test
    public void testIsObtainedGetterAndSetter() {
        // Given
        Stamp stamp = new Stamp(1, 2, true);

        // When
        stamp.setObtained(false);

        // Then
        assertEquals(false, stamp.isObtained());
    }

    @Test
    public void testToString() {
        // Given
        Stamp stamp = new Stamp(1, 2, true);

        // When
        String expectedToString = "Stamp{stampID=1, userID=2, isObtained=true}";
        String actualToString = stamp.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
}
