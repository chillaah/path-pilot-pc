package com.example.pathpilotfx.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.Date;

class SessionTest {

    @Test
    void testSessionConstructorAndGetters() {
        Date start = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2024-01-02");
        int length = 24;

        Session session = new Session(1, start, end, length);

        assertEquals(1, session.getUserID());
        assertEquals(start, session.getSessionStart());
        assertEquals(end, session.getSessionEnd());
        assertEquals(length, session.getSessionLength());
    }

    @Test
    void testSetAndGetUserID() {
        Session session = new Session(1, null, null, 0);
        session.setUserID(2);
        assertEquals(2, session.getUserID());
    }

    @Test
    void testSetAndGetSessionID() {
        Session session = new Session(1, null, null, 0);
        session.setSessionID(3);
        assertEquals(3, session.getSessionID());
    }

    @Test
    void testSetAndGetSessionStart() {
        Date start = Date.valueOf("2024-01-01");
        Session session = new Session(1, null, null, 0);
        session.setSessionStart(start);
        assertEquals(start, session.getSessionStart());
    }

    @Test
    void testSetAndGetSessionEnd() {
        Date end = Date.valueOf("2024-01-02");
        Session session = new Session(1, null, null, 0);
        session.setSessionEnd(end);
        assertEquals(end, session.getSessionEnd());
    }

    @Test
    void testSetAndGetSessionLength() {
        Session session = new Session(1, null, null, 0);
        session.setSessionLength(24);
        assertEquals(24, session.getSessionLength());
    }

    @Test
    void testToString() {
        Date start = Date.valueOf("2024-01-01");
        Date end = Date.valueOf("2024-01-02");
        int length = 24;

        Session session = new Session(1, start, end, length);
        session.setSessionID(5);

        String expected = "Session{" +
                "userID=1" +
                ", sessionID=5" +
                ", sessionStart=" + start +
                ", sessionEnd=" + end +
                ", sessionLength=" + length +
                '}';

        assertEquals(expected, session.toString());
    }
}
