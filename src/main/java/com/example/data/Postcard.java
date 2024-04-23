package com.example.data;

import java.sql.Blob;

public class Postcard {
    private int userID;
    private int postcardID;
    private Blob postcardBlob;

    public Postcard(int userID, int postcardID, Blob postcardBlob) {
        this.userID = userID;
        this.postcardID = postcardID;
        this.postcardBlob = postcardBlob;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostcardID() {
        return postcardID;
    }

    public void setPostcardID(int postcardID) {
        this.postcardID = postcardID;
    }

    public Blob getPostcardBlob() {
        return postcardBlob;
    }

    public void setPostcardBlob(Blob postcardBlob) {
        this.postcardBlob = postcardBlob;
    }

    @Override
    public String toString() {
        return "Postcard{" +
                "userID=" + userID +
                ", postcardID=" + postcardID +
                ", postcardBlob=" + postcardBlob +
                '}';
    }
}
