package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockCountryDAO;
import com.example.pathpilotfx.model.Country;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockCountryDAOTest {

    private MockCountryDAO mockCountryDAO;

    @BeforeEach
    public void setUp() {
        mockCountryDAO = new MockCountryDAO();
    }

    @Test
    public void testInsert() {
        Country country = new Country("USA",11);
        mockCountryDAO.insert(country);
        assertNotNull(mockCountryDAO.getByCountryId(country.getCountryID()));
    }

    @Test
    public void testUpdate() {
        Country country = new Country("Canada",22);
        mockCountryDAO.insert(country);
        country.setCountryName("New Canada");
        mockCountryDAO.update(country);
        assertEquals("New Canada", mockCountryDAO.getByCountryId(country.getCountryID()).getCountryName());
    }

    @Test
    public void testDeleteSession() {
        Country country = new Country("Australia",0);
        mockCountryDAO.insert(country);
        mockCountryDAO.deleteSession(country.getCountryID());
        assertNull(mockCountryDAO.getByCountryId(country.getCountryID()));
    }

    @Test
    public void testGetAll() {
        Country country1 = new Country("India", 1);
        Country country2 = new Country("Japan", 2);
        mockCountryDAO.insert(country1);
        mockCountryDAO.insert(country2);
        List<Country> countries = mockCountryDAO.getAll();
        assertEquals(2, countries.size());
    }

    @Test
    public void testGetByCountryId() {
        Country country = new Country("Germany",30);
        mockCountryDAO.insert(country);
        assertNotNull(mockCountryDAO.getByCountryId(country.getCountryID()));
    }

    @Test
    public void testGetByCountryId_NotFound() {
        assertNull(mockCountryDAO.getByCountryId(100));
    }

    @Test
    public void testGetByCountryId_Null() {
        assertNull(mockCountryDAO.getByCountryId(-1));
    }

    @Test
    public void testGetByCountryId_NegativeId() {
        assertNull(mockCountryDAO.getByCountryId(-5));
    }
}
