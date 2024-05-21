package com.example.pathpilotfx.controller.countries;

import com.example.pathpilotfx.model.Country;

public class SelectedCountry {
    private static Country selectedCountry;
    public static Country getSelectedCountry() {
        return selectedCountry;
    }

    public static void setSelectedCountry(Country selectedCountry) {
        SelectedCountry.selectedCountry = selectedCountry;
    }




}