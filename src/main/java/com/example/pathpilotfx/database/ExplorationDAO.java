package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Exploration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExplorationDAO {
    private Connection connection;

    public ExplorationDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Exploration exploration) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO exploration VALUES(?,?,?,?,?)");
            insertData.setInt(1, exploration.getUserID());
            insertData.setInt(2, exploration.getCountryID());
            insertData.setString(3, exploration.getStatus());
            insertData.setBoolean(4, exploration.isLocked());
            insertData.setBoolean(5, exploration.isFavourited());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Exploration exploration) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE exploration SET status = ?, lockedStatus = ?, " +
                            "favourited = ? WHERE country_id = ? AND user_id = ?"
            );
            updateData.setString(1, exploration.getStatus());
            updateData.setBoolean(2, exploration.isLocked());
            updateData.setBoolean(3, exploration.isFavourited());
            updateData.setInt(4, exploration.getCountryID());
            updateData.setInt(5, exploration.getUserID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteCountryData(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM exploration WHERE country_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Exploration> getAll() {
        List<Exploration> explorationData = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM exploration");
            while (rs.next()) {
                explorationData.add(
                        new Exploration(
                                rs.getInt("user_id"),
                                rs.getInt("country_id"),
                                rs.getString("status"),
                                rs.getBoolean("lockedStatus"),
                                rs.getBoolean("favourited")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return explorationData;
    }
    public List<Exploration> getAllLocked() {
        List<Exploration> explorationData = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery(
                    "SELECT * FROM exploration where lockedStatus = 1");
            while (rs.next()) {
                explorationData.add(
                        new Exploration(
                                rs.getInt("user_id"),
                                rs.getInt("country_id"),
                                rs.getString("status"),
                                rs.getBoolean("lockedStatus"),
                                rs.getBoolean("favourited")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return explorationData;
    }
    public List<Exploration> getAllUnlocked() {
        List<Exploration> explorationData = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery(
                    "SELECT * FROM exploration where lockedStatus = 0");
            while (rs.next()) {
                explorationData.add(
                        new Exploration(
                                rs.getInt("user_id"),
                                rs.getInt("country_id"),
                                rs.getString("status"),
                                rs.getBoolean("lockedStatus"),
                                rs.getBoolean("favourited")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return explorationData;
    }

    public Exploration getByCountryId(int countryID) {
        try {
            PreparedStatement explorationData = connection.prepareStatement(
                    "SELECT * FROM exploration WHERE country_id = ?");
            explorationData.setInt(1, countryID);
            ResultSet rs = explorationData.executeQuery();
            if (rs.next()) {
                return new Exploration(
                        rs.getInt("user_id"),
                        rs.getInt("country_id"),
                        rs.getString("status"),
                        rs.getBoolean("lockedStatus"),
                        rs.getBoolean("favourited")
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
