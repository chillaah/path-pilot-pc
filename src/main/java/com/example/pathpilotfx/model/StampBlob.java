package com.example.pathpilotfx.model;

import java.sql.Blob;

public class StampBlob {
    private int blobID;
    private int countryID;
    private Blob stampBlob;

    public StampBlob(int blobID, int countryID, Blob stampBlob) {
        this.blobID = blobID;
        this.countryID = countryID;
        this.stampBlob = stampBlob;
    }

    public int getBlobID() {
        return blobID;
    }

    public void setBlobID(int blobID) {
        this.blobID = blobID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public Blob getStampBlob() {
        return stampBlob;
    }

    public void setStampBlob(Blob stampBlob) {
        this.stampBlob = stampBlob;
    }

    @Override
    public String toString() {
        return "StampBlob{" +
                "blobID=" + blobID +
                ", countryID=" + countryID +
                ", stampBlob=" + stampBlob +
                '}';
    }
}
