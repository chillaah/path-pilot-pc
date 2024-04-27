package com.example.pathpilotfx.model;

import java.sql.Blob;

public class Stamp {
    private int stampID;
    private int userID;
    private int blobID;
    private boolean isObtained;

    public Stamp(int stampID, int userID, int blobID, boolean isObtained) {
        this.stampID = stampID;
        this.userID = userID;
        this.blobID = blobID;
        this.isObtained = isObtained;
    }

    public int getStampID() {
        return stampID;
    }

    public void setStampID(int stampID) {
        this.stampID = stampID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getBlobID() {
        return blobID;
    }

    public void setBlobID(int blobID) {
        this.blobID = blobID;
    }

    public boolean isObtained() {
        return isObtained;
    }

    public void setObtained(boolean obtained) {
        isObtained = obtained;
    }

    @Override
    public String toString() {
        return "Stamp{" +
                "stampID=" + stampID +
                ", userID=" + userID +
                ", blobID=" + blobID +
                ", isObtained=" + isObtained +
                '}';
    }
}