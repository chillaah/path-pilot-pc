package com.example.pathpilotfx.model;

import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CountryTest {
    private CountryDAO countryDAO;
    @BeforeEach
    public void initialize() {
        countryDAO = new CountryDAO();


    }

    @Test
    public void testConstructorAndGetters() {

        int countryID = 1;
        String countryName = "TestCountry";
        int requiredEXP = 1000;


        Country country = new Country(countryID, countryName, requiredEXP);


        assertEquals(countryID, country.getCountryID());
        assertEquals(countryName, country.getCountryName());
        assertEquals(requiredEXP, country.getRequiredEXP());
    }

    @Test
    public void testSetters() {

        Country country = new Country(1, "TestCountry", 1000);


        country.setCountryID(2);
        country.setCountryName("NewTestCountry");
        country.setRequiredEXP(2000);


        assertEquals(2, country.getCountryID());
        assertEquals("NewTestCountry", country.getCountryName());
        assertEquals(2000, country.getRequiredEXP());
    }

    @Test
    public void testToString() {
        // Given
        Country country = new Country(1, "TestCountry", 1000);

        // Then
        assertEquals("Country{countryID=1, countryName='TestCountry', requiredEXP=1000}", country.toString());
    }


    @Test
    public void testNotEquals() {
        Country country1 = new Country(1, "TestCountry", 1000);
        Country country2 = new Country(2, "NewTestCountry", 2000);

        assertEquals(false, country1.equals(country2));
    }

    @Test
    public void testNullEquals() {
        Country country = new Country(1, "TestCountry", 1000);

        assertEquals(false, country.equals(null));
    }

    @Test
    public void testDifferentClassEquals() {
        Country country = new Country(1, "TestCountry", 1000);

        assertEquals(false, country.equals("Test"));
    }

    @Test
    public void testSelfEquals() {
        Country country = new Country(1, "TestCountry", 1000);

        assertEquals(true, country.equals(country));
    }


    @Test
    public void testNotEqualCountryObjects() {
        Country country1 = new Country("TestCountry", 1000);
        Country country2 = new Country("NewTestCountry", 2000);

        assertEquals(false, country1.equals(country2));
    }
    @AfterEach
    public void cleanup() {
        if (countryDAO != null) {
            countryDAO.close();
        }
    }
}
