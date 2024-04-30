package com.example.pathpilotfx.model;

public class Country {
    private int countryID;
    private String countryName;
    private int requiredEXP;

    public Country(int countryID, String countryName, int requiredEXP) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.requiredEXP = requiredEXP;
    }
    public Country(String countryName, int requiredEXP) {
        this.countryName = countryName;
        this.requiredEXP = requiredEXP;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getRequiredEXP() {
        return requiredEXP;
    }

    public void setRequiredEXP(int requiredEXP) {
        this.requiredEXP = requiredEXP;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", countryName='" + countryName + '\'' +
                ", requiredEXP=" + requiredEXP +
                '}';
    }
}
