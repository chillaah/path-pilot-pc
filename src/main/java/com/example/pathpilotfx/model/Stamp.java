package com.example.pathpilotfx.model;

import java.sql.Blob;

public class Stamp {
    private int stampID;
    private int userID;
    private Blob stampBlob;
    private boolean isObtained;

    public Stamp(int stampID, int userID, Blob stampBlob, boolean isObtained) {
        this.stampID = stampID;
        this.userID = userID;
        this.stampBlob = stampBlob;
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

    public Blob getStampBlob() {
        return stampBlob;
    }

    public void setStampBlob(Blob stampBlob) {
        this.stampBlob = stampBlob;
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
                ", stampBlob=" + stampBlob +
                ", isObtained=" + isObtained +
                '}';
    }
}
