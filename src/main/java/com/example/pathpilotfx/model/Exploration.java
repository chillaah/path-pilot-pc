package com.example.pathpilotfx.model;

public class Exploration {
    private int userID;
    private int countryID;
    private String countryName;
    private String status;
    private boolean isLocked;
    private boolean isFavourited;

    public Exploration(int userID, int countryID, String status, boolean isLocked, boolean isFavourited) {
        this.userID = userID;
        this.countryID = countryID;
        this.status = status;
        this.isLocked = isLocked;
        this.isFavourited = isFavourited;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public boolean isFavourited() {
        return isFavourited;
    }

    public void setFavourited(boolean favourited) {
        isFavourited = favourited;
    }

    @Override
    public String toString() {
        return "Exploration{" +
                "userID=" + userID +
                ", countryID=" + countryID +
                ", countryName='" + countryName + '\'' +
                ", status='" + status + '\'' +
                ", isLocked=" + isLocked +
                ", isFavourited=" + isFavourited +
                '}';
    }
}
