package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Session;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    @Test
    public void testGettersAndSetters() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        session.setUserID(3);
        session.setSessionID(4);
        session.setSessionStart(Date.valueOf("2022-04-17"));
        session.setSessionEnd(Date.valueOf("2022-04-18"));
        session.setSessionLength(90);

        // Then
        assertEquals(3, session.getUserID());
        assertEquals(4, session.getSessionID());
        assertEquals(Date.valueOf("2022-04-17"), session.getSessionStart());
        assertEquals(Date.valueOf("2022-04-18"), session.getSessionEnd());
        assertEquals(90, session.getSessionLength());
    }

    @Test
    public void testToString() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        String expectedToString = "Session{userID=1, sessionID=2, sessionStart=2022-04-15, sessionEnd=2022-04-16, sessionLength=60}";
        String actualToString = session.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void testSessionIDGetterAndSetter() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        session.setSessionID(3);

        // Then
        assertEquals(3, session.getSessionID());
    }

    @Test
    public void testSessionStartGetterAndSetter() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        session.setSessionStart(Date.valueOf("2022-04-17"));

        // Then
        assertEquals(Date.valueOf("2022-04-17"), session.getSessionStart());
    }

    @Test
    public void testSessionEndGetterAndSetter() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        session.setSessionEnd(Date.valueOf("2022-04-17"));

        // Then
        assertEquals(Date.valueOf("2022-04-17"), session.getSessionEnd());
    }

    @Test
    public void testSessionLengthGetterAndSetter() {
        // Given
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        // When
        session.setSessionLength(90);

        // Then
        assertEquals(90, session.getSessionLength());
    }
}
