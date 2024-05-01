package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Stamp;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StampTest {

    @Test
    public void testStampIDGetterAndSetter() {
        Stamp stamp = new Stamp(1, 2, true);

        stamp.setStampID(3);

        assertEquals(3, stamp.getStampID());
    }

    @Test
    public void testUserIDGetterAndSetter() {
        Stamp stamp = new Stamp(1, 2, true);

        stamp.setUserID(4);

        assertEquals(4, stamp.getUserID());
    }

    @Test
    public void testIsObtainedGetterAndSetter() {
        Stamp stamp = new Stamp(1, 2, true);

        stamp.setObtained(false);

        assertEquals(false, stamp.isObtained());
    }

    @Test
    public void testToString() {
        Stamp stamp = new Stamp(1, 2, true);

        String expectedToString = "Stamp{stampID=1, userID=2, isObtained=true}";
        String actualToString = stamp.toString();

        assertEquals(expectedToString, actualToString);
    }
}
