package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Image;
import com.example.pathpilotfx.model.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {
    private Connection connection;

    public LocationDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Location location) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO location VALUES(?,?,?,?,?,?,?)");
            insertData.setInt(1, location.getCountryID());
            insertData.setString(2, location.getLocationName());
            insertData.setString(3, location.getLocationDetails());
            insertData.setInt(4, location.getStampID());
            insertData.setInt(5, location.getBackgroundID());
            insertData.setString(6, location.getStampName());
            insertData.setString(7, location.getBackgroundName());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Location location) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE location SET country_id = ?, location_name = ?, " +
                        "location_details = ?, stamp_id = ?, background_id = ?, " +
                            "stamp_name = ?, background_name = ?" +
                            " WHERE location_id = ?"
            );
            updateData.setInt(1, location.getCountryID());
            updateData.setString(2, location.getLocationName());
            updateData.setString(3, location.getLocationDetails());
            updateData.setInt(4, location.getStampID());
            updateData.setInt(5, location.getBackgroundID());
            updateData.setInt(6, location.getLocationID());
            updateData.setString(6, location.getStampName());
            updateData.setString(7, location.getBackgroundName());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteLocation(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM location WHERE location_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Location> getAll() {
        List<Location> locations = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM location");
            while (rs.next()) {
                locations.add(
                        new Location(
                                rs.getInt("location_id"),
                                rs.getInt("country_id"),
                                rs.getString("location_name"),
                                rs.getString("location_details"),
                                rs.getInt("stamp_id"),
                                rs.getInt("background_id"),
                                rs.getString("stamp_name"),
                                rs.getString("background_name")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return locations;
    }

    public Location getByLocationId(int id) {
        try {
            PreparedStatement getLocation = connection.prepareStatement(
                    "SELECT * FROM location WHERE location_id = ?");
            getLocation.setInt(1, id);
            ResultSet rs = getLocation.executeQuery();
            if (rs.next()) {
                return new Location(
                        rs.getInt("location_id"),
                        rs.getInt("country_id"),
                        rs.getString("location_name"),
                        rs.getString("location_details"),
                        rs.getInt("stamp_id"),
                        rs.getInt("background_id"),
                        rs.getString("stamp_name"),
                        rs.getString("background_name")
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
