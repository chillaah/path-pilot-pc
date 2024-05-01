package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Session;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

    @Test
    public void testGettersAndSetters() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        session.setUserID(3);
        session.setSessionID(4);
        session.setSessionStart(Date.valueOf("2022-04-17"));
        session.setSessionEnd(Date.valueOf("2022-04-18"));
        session.setSessionLength(90);

        assertEquals(3, session.getUserID());
        assertEquals(4, session.getSessionID());
        assertEquals(Date.valueOf("2022-04-17"), session.getSessionStart());
        assertEquals(Date.valueOf("2022-04-18"), session.getSessionEnd());
        assertEquals(90, session.getSessionLength());
    }

    @Test
    public void testToString() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        String expectedToString = "Session{userID=1, sessionID=2, sessionStart=2022-04-15, sessionEnd=2022-04-16, sessionLength=60}";
        String actualToString = session.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void testSessionIDGetterAndSetter() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        session.setSessionID(3);

        assertEquals(3, session.getSessionID());
    }

    @Test
    public void testSessionStartGetterAndSetter() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        session.setSessionStart(Date.valueOf("2022-04-17"));

        assertEquals(Date.valueOf("2022-04-17"), session.getSessionStart());
    }

    @Test
    public void testSessionEndGetterAndSetter() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        session.setSessionEnd(Date.valueOf("2022-04-17"));

        assertEquals(Date.valueOf("2022-04-17"), session.getSessionEnd());
    }

    @Test
    public void testSessionLengthGetterAndSetter() {
        Session session = new Session(1, 2, Date.valueOf("2022-04-15"), Date.valueOf("2022-04-16"), 60);

        session.setSessionLength(90);

        assertEquals(90, session.getSessionLength());
    }
}
