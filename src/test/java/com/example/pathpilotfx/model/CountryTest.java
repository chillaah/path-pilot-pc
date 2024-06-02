package com.example.pathpilotfx.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    private Country country;

    @BeforeEach
    void setUp() {
        country = new Country("TestCountry", 1000, "Details of TestCountry", "stamp.png", "bg.png");
    }

    @Test
    void testCountryConstructorWithAllFields() {
        Country countryFull = new Country(1, "TestCountry", 1000, "Details of TestCountry", "stamp.png", "bg.png");
        assertEquals(1, countryFull.getCountryID());
        assertEquals("TestCountry", countryFull.getCountryName());
        assertEquals(1000, countryFull.getRequiredEXP());
        assertEquals("Details of TestCountry", countryFull.getCountryDetails());
        assertEquals("stamp.png", countryFull.getStampImage());
        assertEquals("bg.png", countryFull.getBgImage());
    }

    @Test
    void testCountryConstructorWithoutID() {
        assertEquals("TestCountry", country.getCountryName());
        assertEquals(1000, country.getRequiredEXP());
        assertEquals("Details of TestCountry", country.getCountryDetails());
        assertEquals("stamp.png", country.getStampImage());
        assertEquals("bg.png", country.getBgImage());
    }

    @Test
    void testGetCountryID() {
        country.setCountryID(1);
        assertEquals(1, country.getCountryID());
    }

    @Test
    void testSetCountryID() {
        country.setCountryID(2);
        assertEquals(2, country.getCountryID());
    }

    @Test
    void testGetCountryName() {
        assertEquals("TestCountry", country.getCountryName());
    }

    @Test
    void testSetCountryName() {
        country.setCountryName("NewCountry");
        assertEquals("NewCountry", country.getCountryName());
    }

    @Test
    void testGetRequiredEXP() {
        assertEquals(1000, country.getRequiredEXP());
    }

    @Test
    void testSetRequiredEXP() {
        country.setRequiredEXP(2000);
        assertEquals(2000, country.getRequiredEXP());
    }

    @Test
    void testGetCountryDetails() {
        assertEquals("Details of TestCountry", country.getCountryDetails());
    }

    @Test
    void testSetCountryDetails() {
        country.setCountryDetails("New Details");
        assertEquals("New Details", country.getCountryDetails());
    }

    @Test
    void testGetStampImage() {
        assertEquals("stamp.png", country.getStampImage());
    }

    @Test
    void testSetStampImage() {
        country.setStampImage("new_stamp.png");
        assertEquals("new_stamp.png", country.getStampImage());
    }

    @Test
    void testGetBgImage() {
        assertEquals("bg.png", country.getBgImage());
    }

    @Test
    void testSetBgImage() {
        country.setBgImage("new_bg.png");
        assertEquals("new_bg.png", country.getBgImage());
    }

    @Test
    void testToString() {
        country.setCountryID(1);
        String expectedString = "Country{countryID=1, countryName='TestCountry', requiredEXP=1000}";
        assertEquals(expectedString, country.toString());
    }

    @Test
    void testIsCurrent_loc() {
        country.setCurrent_loc(true);
        assertTrue(country.isCurrent_loc());
    }

    @Test
    void testSetCurrent_loc() {
        country.setCurrent_loc(true);
        assertTrue(country.isCurrent_loc());
    }

    @Test
    void testIsLocked() {
        country.setLocked(true);
        assertTrue(country.isLocked());
    }

    @Test
    void testSetLocked() {
        country.setLocked(true);
        assertTrue(country.isLocked());
    }
}
