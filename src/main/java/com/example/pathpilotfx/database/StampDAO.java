package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Stamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class StampDAO {
    private Connection connection;

    public StampDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Stamp stamp) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO stamp VALUES(?,?,?,?)");
            insertData.setInt(1, stamp.getStampID());
            insertData.setInt(2, stamp.getUserID());
            insertData.setInt(3, stamp.getBlobID());
            insertData.setBoolean(4, stamp.isObtained());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Stamp stamp) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE stamp SET stamp_id = ?, user_id = ?, blob_id = ?," +
                        " obtained = ? WHERE stamp_id = ?"
            );
            updateData.setInt(1, stamp.getStampID());
            updateData.setInt(2, stamp.getUserID());
            updateData.setInt(3, stamp.getBlobID());
            updateData.setBoolean(4, stamp.isObtained());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteStamp(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM stamp WHERE stamp_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Stamp> getAll() {
        List<Stamp> stamps = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM stamp");
            while (rs.next()) {
                stamps.add(
                        new Stamp(
                                rs.getInt("stamp_id"),
                                rs.getInt("user_id"),
                                rs.getInt("blob_id"),
                                rs.getBoolean("obtained")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return stamps;
    }

    public Stamp getByStampId(int id) {
        try {
            PreparedStatement getStamp = connection.prepareStatement(
                    "SELECT * FROM stamp WHERE stamp_id = ?");
            getStamp.setInt(1, id);
            ResultSet rs = getStamp.executeQuery();
            if (rs.next()) {
                return new Stamp(
                        rs.getInt("stamp_id"),
                        rs.getInt("user_id"),
                        rs.getInt("blob_id"),
                        rs.getBoolean("obtained")
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
