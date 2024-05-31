package com.example.pathpilotfx.model;
/**
 Class that initialises Exploration Object
 **/
public class Exploration {
    private int userID;
    private int countryID;
    private String status;
    private boolean isLocked;
    private boolean isFavourite;

    /**
     * Initializes a Exploration object with specified attributes.
     *
     * @param userID    The ID of the user.
     * @param countryID The ID of the country.
     * @param status    The exploration status.
     * @param isLocked  The status of the location.
     * @param isFavourite  Favourite status for location.
     */
    public Exploration(int userID, int countryID, String status, boolean isLocked, boolean isFavourite) {
        this.userID = userID;
        this.countryID = countryID;
        this.status = status;
        this.isLocked = isLocked;
        this.isFavourite = isFavourite;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    /**
     * toString method for Exploration.
     * @return A string representation the Exploration object.
     */
    @Override
    public String toString() {
        return "Exploration{" +
                "userID=" + userID +
                ", countryID=" + countryID +
                ", status='" + status + '\'' +
                ", isLocked=" + isLocked +
                ", isFavourited=" + isFavourite +
                '}';
    }
}
