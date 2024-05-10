package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountryDAOTest {
//
//    private CountryDAO countryDAO;
//    private Connection connection;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        // Initialize CountryDAO
//        countryDAO = new CountryDAO();
//        connection = DatabaseConnection.getInstance();
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//        // Close the database connection after each test
//        try {
//            connection.close();
//        } catch (SQLException ex) {
//            System.err.println(ex);
//        }
//    }
//
//
//    @Test
//    public void testInsertAndGetByCountryId() {
//        // Given
//        Country countryToInsert = new Country("TestCountry", 1000);
//
//        // When
//        countryDAO.insert(countryToInsert);
//
//        // Then
//        Country retrievedCountry = countryDAO.getByCountryId(1);
//        assertNotNull(retrievedCountry);
//        assertEquals("TestCountry", retrievedCountry.getCountryName());
//        assertEquals(1000, retrievedCountry.getRequiredEXP());
//    }
//
//    @Test
//    public void testUpdate() {
//        // Given
//        Country country = new Country("ExistingCountry", 2000);
//        countryDAO.insert(country);
//        country.setCountryName("UpdatedCountry");
//        country.setRequiredEXP(3000);
//
//        // When
//        countryDAO.update(country);
//
//        // Then
//        Country updatedCountry = countryDAO.getByCountryId(1);
//        assertNotNull(updatedCountry);
//        assertEquals("UpdatedCountry", updatedCountry.getCountryName());
//        assertEquals(3000, updatedCountry.getRequiredEXP());
//    }
//
//    @Test
//    public void testDeleteSession() {
//        // Given
//        Country country = new Country("ToDeleteCountry", 3000);
//        countryDAO.insert(country);
//
//        // When
//        countryDAO.deleteSession(1);
//
//        // Then
//        assertNull(countryDAO.getByCountryId(1));
//    }
//
//    @Test
//    public void testGetAll() {
//        // Given
//        Country country1 = new Country("Country1", 4000);
//        Country country2 = new Country("Country2", 5000);
//        countryDAO.insert(country1);
//        countryDAO.insert(country2);
//
//        // When
//        List<Country> countries = countryDAO.getAll();
//
//        // Then
//        assertEquals(2, countries.size());
//        assertTrue(countries.contains(country1));
//        assertTrue(countries.contains(country2));
//    }
//
//    @Test
//    public void testGetByCountryIdNonExistent() {
//        // When
//        Country retrievedCountry = countryDAO.getByCountryId(999);
//
//        // Then
//        assertNull(retrievedCountry);
//    }
}
