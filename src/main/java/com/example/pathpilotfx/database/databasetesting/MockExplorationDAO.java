package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Exploration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockExplorationDAO {
    private Map<Integer, Exploration> explorations = new HashMap<>();

    public void insert(Exploration exploration) {
        int hash = exploration.getUserID() * 31 + exploration.getCountryID(); // Generating a unique hash for composite key
        explorations.put(hash, exploration);
    }

    public void update(Exploration exploration) {
        int hash = exploration.getUserID() * 31 + exploration.getCountryID(); // Generating a unique hash for composite key
        explorations.put(hash, exploration);
    }

    public void deleteCountryData(int id) {
        // Iterate over the map to find and remove entries with the given country_id
        explorations.entrySet().removeIf(entry -> entry.getValue().getCountryID() == id);
    }

    public List<Exploration> getAll() {
        return new ArrayList<>(explorations.values());
    }

    public List<Exploration> getAllLocked() {
        List<Exploration> lockedExplorations = new ArrayList<>();
        for (Exploration exploration : explorations.values()) {
            if (exploration.isLocked()) {
                lockedExplorations.add(exploration);
            }
        }
        return lockedExplorations;
    }

    public List<Exploration> getAllUnlocked() {
        List<Exploration> unlockedExplorations = new ArrayList<>();
        for (Exploration exploration : explorations.values()) {
            if (!exploration.isLocked()) {
                unlockedExplorations.add(exploration);
            }
        }
        return unlockedExplorations;
    }

    public Exploration getByCountryId(int countryID) {
        for (Exploration exploration : explorations.values()) {
            if (exploration.getCountryID() == countryID) {
                return exploration;
            }
        }
        return null;
    }

}
