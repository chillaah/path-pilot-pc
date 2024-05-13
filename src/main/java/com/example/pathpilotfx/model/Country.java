package com.example.pathpilotfx.model;

public class Country {
    private int countryID;
    private String countryName;
    private int requiredEXP;

    private String countryDetails;

    private String stampImage;

    private String bgImage;

    public Country(String countryName, int requiredEXP, String stampImage, String bgImage) {
        this.countryName = countryName;
        this.requiredEXP = requiredEXP;
        this.stampImage = stampImage;
        this.bgImage = bgImage;
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

    public String getCountryDetails() {
        return countryDetails;
    }

    public void setCountryDetails(String countryDetails) {
        this.countryDetails = countryDetails;
    }

    public String getStampImage() {
        return stampImage;
    }

    public void setStampImage(String stampImage) {
        this.stampImage = stampImage;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
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
