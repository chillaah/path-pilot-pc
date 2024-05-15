package com.example.pathpilotfx.model;

/**
 Class that initialises Country Object
 **/
public class Country {
    private int countryID;
    private String countryName;
    private int requiredEXP;

    private String countryDetails;

    private String stampImage;

    private String bgImage;

    /**
     * Initializes a Country object with specified attributes.
     *
     * @param countryName  The name of the country.
     * @param requiredEXP  The required experience points to unlock the country.
     * @param stampImage   The image representing the stamp of the country.
     * @param bgImage      The background image associated with the country.
     */
    public Country(String countryName, int requiredEXP, String stampImage, String bgImage) {
        this.countryName = countryName;
        this.requiredEXP = requiredEXP;
        this.stampImage = stampImage;
        this.bgImage = bgImage;
    }

    /**
     * Retrieves the ID of the country.
     *
     * @return The ID of the country.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Sets the ID of the country.
     *
     * @param countryID The ID of the country to be set.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Retrieves the name of the country.
     *
     * @return The name of the country.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the name of the country.
     *
     * @param countryName The name of the country to be set.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Retrieves the required experience points to unlock the country.
     *
     * @return The required experience points.
     */
    public int getRequiredEXP() {
        return requiredEXP;
    }

    /**
     * Sets the required experience points to unlock the country.
     *
     * @param requiredEXP The required experience points to be set.
     */
    public void setRequiredEXP(int requiredEXP) {
        this.requiredEXP = requiredEXP;
    }

    /**
     * Retrieves additional details about the country.
     *
     * @return Additional details about the country.
     */
    public String getCountryDetails() {
        return countryDetails;
    }

    /**
     * Sets additional details about the country.
     *
     * @param countryDetails Additional details about the country.
     */
    public void setCountryDetails(String countryDetails) {
        this.countryDetails = countryDetails;
    }

    /**
     * Retrieves the image representing the stamp of the country.
     *
     * @return The stamp image of the country.
     */
    public String getStampImage() {
        return stampImage;
    }

    /**
     * Sets the image representing the stamp of the country.
     *
     * @param stampImage The stamp image of the country to be set.
     */
    public void setStampImage(String stampImage) {
        this.stampImage = stampImage;
    }

    /**
     * Retrieves the background image associated with the country.
     *
     * @return The background image of the country.
     */
    public String getBgImage() {
        return bgImage;
    }

    /**
     * Sets the background image associated with the country.
     *
     * @param bgImage The background image of the country to be set.
     */
    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    /**
     * Returns a string representation of the Country object.
     *
     * @return A string representation of the Country object.
     */
    @Override
    public String toString() {
        return "Country{" +
                "countryID=" + countryID +
                ", countryName='" + countryName + '\'' +
                ", requiredEXP=" + requiredEXP +
                '}';
    }
}
