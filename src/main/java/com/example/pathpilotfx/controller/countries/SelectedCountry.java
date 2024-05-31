package com.example.pathpilotfx.controller.countries;

import com.example.pathpilotfx.model.Country;


/**
 * Class to keep track of the selected country
 */
public class SelectedCountry {

    // Variable to store the selected country
    private static Country selectedCountry;

    /**
     * Retrieves the selected country.
     *
     * @return The selected country.
     */
    public static Country getSelectedCountry() {
        return selectedCountry;
    }

    /**
     * Sets the selected country.
     *
     * @param selectedCountry The selected country to be set.
     */
    public static void setSelectedCountry(Country selectedCountry) {
        SelectedCountry.selectedCountry = selectedCountry;
    }
}