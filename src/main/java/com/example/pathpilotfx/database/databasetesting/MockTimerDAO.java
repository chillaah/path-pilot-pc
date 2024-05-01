package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Timer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTimerDAO {
    private Map<Integer, Timer> timers = new HashMap<>();
    private int nextTimerId = 1;

    public void insert(Timer timer) {
        int timerId = nextTimerId++;
        timer.setTimerID(timerId);
        timers.put(timerId, timer);
    }

    public void update(Timer timer) {
        timers.put(timer.getTimerID(), timer);
    }

    public void deleteTimer(int id) {
        timers.remove(id);
    }

    public List<Timer> getAll() {
        return new ArrayList<>(timers.values());
    }

    public Timer getByTimerId(int id) {
        return timers.get(id);
    }

}
