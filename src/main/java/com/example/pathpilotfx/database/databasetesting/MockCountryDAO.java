package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockCountryDAO {
    private Map<Integer, Country> countries = new HashMap<>();
    private int nextCountryId = 1;

    public void insert(Country country) {
        int countryId = nextCountryId++;
        country.setCountryID(countryId);
        countries.put(countryId, country);
    }

    public void update(Country country) {
        countries.put(country.getCountryID(), country);
    }

    public void deleteSession(int id) {
        countries.remove(id);
    }

    public List<Country> getAll() {
        return new ArrayList<>(countries.values());
    }

    public Country getByCountryId(int countryID) {
        return countries.get(countryID);
    }


}
