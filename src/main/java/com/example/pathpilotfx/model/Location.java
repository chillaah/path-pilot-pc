package com.example.pathpilotfx.model;
/**
 Class that initialises Location Object
 **/
public class Location {
    private int locationID;
    private int countryID;
    private String locationName;
    private String locationDetails;
    private int stampID;
    private int backgroundID;
    private String backgroundName;
    private String stampName;

    public Location(int locationID, int countryID, String locationName, String locationDetails,
                    int stampID, int backgroundID, String stampName, String backgroundName) {
        this.locationID = locationID;
        this.countryID = countryID;
        this.locationName = locationName;
        this.locationDetails = locationDetails;
        this.stampID = stampID;
        this.backgroundID = backgroundID;
        this.stampName = stampName;
        this.backgroundName = backgroundName;
    }
    public Location(int countryID, String locationName, String locationDetails,
                    int stampID, int backgroundID, String stampName, String backgroundName) {
        this.countryID = countryID;
        this.locationName = locationName;
        this.locationDetails = locationDetails;
        this.stampID = stampID;
        this.backgroundID = backgroundID;
        this.stampName = stampName;
        this.backgroundName = backgroundName;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public int getStampID() {
        return stampID;
    }

    public void setStampID(int stampID) {
        this.stampID = stampID;
    }

    public int getBackgroundID() {
        return backgroundID;
    }

    public void setBackgroundID(int backgroundID) {
        this.backgroundID = backgroundID;
    }

    public String getBackgroundName() {
        return backgroundName;
    }

    public void setBackgroundName(String backgroundName) {
        this.backgroundName = backgroundName;
    }

    public String getStampName() {
        return stampName;
    }

    public void setStampName(String stampName) {
        this.stampName = stampName;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + locationID +
                ", countryID=" + countryID +
                ", locationName='" + locationName + '\'' +
                ", locationDetails='" + locationDetails + '\'' +
                ", stampID=" + stampID +
                ", backgroundID=" + backgroundID +
                ", backgroundName='" + backgroundName + '\'' +
                ", stampName='" + stampName + '\'' +
                '}';
    }
}
