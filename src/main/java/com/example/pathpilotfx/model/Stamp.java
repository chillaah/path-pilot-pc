package com.example.pathpilotfx.model;

public class Stamp {
    private int stampID;
    private int userID;
    private boolean isObtained;

    public Stamp(int stampID, int userID, boolean isObtained) {
        this.stampID = stampID;
        this.userID = userID;
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
                ", isObtained=" + isObtained +
                '}';
    }
}