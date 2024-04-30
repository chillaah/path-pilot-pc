package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Stamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockStampDAO {
    private Map<Integer, Stamp> stamps = new HashMap<>();
    private int nextStampId = 1;

    public void insert(Stamp stamp) {
        int stampId = nextStampId++;
        stamp.setStampID(stampId);
        stamps.put(stampId, stamp);
    }

    public void update(Stamp stamp) {
        stamps.put(stamp.getStampID(), stamp);
    }

    public void deleteStamp(int id) {
        stamps.remove(id);
    }

    public List<Stamp> getAll() {
        return new ArrayList<>(stamps.values());
    }

    public Stamp getByStampId(int id) {
        return stamps.get(id);
    }

}
