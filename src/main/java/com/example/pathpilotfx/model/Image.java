package com.example.pathpilotfx.model;

public class Image {
    private int imageID;
    private int locationID;

    public Image(int imageID, int locationID) {
        this.imageID = imageID;
        this.locationID = locationID;
    }
    public Image(int locationID) {
        this.locationID = locationID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageID=" + imageID +
                ", locationID=" + locationID +
                '}';
    }
}