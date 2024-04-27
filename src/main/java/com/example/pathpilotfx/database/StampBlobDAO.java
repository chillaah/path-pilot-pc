package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Session;
import com.example.pathpilotfx.model.StampBlob;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StampBlobDAO {
    private Connection connection;

    public StampBlobDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(StampBlob stampBlob) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO stamp_blob VALUES(?,?,?)");
            insertData.setInt(1, stampBlob.getBlobID());
            insertData.setInt(2, stampBlob.getCountryID());
            insertData.setBlob(3, stampBlob.getStampBlob());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(StampBlob stampBlob) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE stamp_blob SET blob_id = ?, country_id = ?, " +
                            "stamp_blob = ?"
            );
            updateData.setInt(1, stampBlob.getBlobID());
            updateData.setInt(2, stampBlob.getCountryID());
            updateData.setBlob(3, stampBlob.getStampBlob());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                "DELETE FROM stamp_blob" +
                    " WHERE blob_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<StampBlob> getAll() {
        List<StampBlob> stampBlobs = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM stamp_blob");
            while (rs.next()) {
                stampBlobs.add(
                        new StampBlob(
                                rs.getInt("blob_id"),
                                rs.getInt("country_id"),
                                rs.getBlob("stamp_blob")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return stampBlobs;
    }

    public StampBlob getByBlobId(int blobID) {
        try {
            PreparedStatement getBlob = connection.prepareStatement(
                    "SELECT * FROM stamp_blob WHERE blob_id = ?");
            getBlob.setInt(1, blobID);
            ResultSet rs = getBlob.executeQuery();
            if (rs.next()) {
                return new StampBlob(
                        rs.getInt("blob_id"),
                        rs.getInt("country_id"),
                        rs.getBlob("stamp_blob")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


}