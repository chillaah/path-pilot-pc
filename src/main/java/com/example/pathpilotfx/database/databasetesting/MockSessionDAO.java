package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockSessionDAO {
    private Map<Integer, Session> sessions = new HashMap<>();
    private int nextSessionId = 1;

    public void insert(Session session) {
        int sessionId = nextSessionId++;
        session.setSessionID(sessionId);
        sessions.put(sessionId, session);
    }

    public void update(Session session) {
        sessions.put(session.getSessionID(), session);
    }

    public void deleteSession(int id) {
        sessions.remove(id);
    }

    public List<Session> getAll() {
        return new ArrayList<>(sessions.values());
    }

    public Session getBySessionId(int sessionId) {
        return sessions.get(sessionId);
    }

}
