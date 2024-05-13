package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CountryDAO {
    private Connection connection;

    public CountryDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Country country) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO country (country_name,required_exp,country_details,stamp_name,bg_name) VALUES(?,?,?,?,?)");
            insertData.setString(1, country.getCountryDetails());
            insertData.setInt(2, country.getRequiredEXP());
            insertData.setString(3, country.getCountryDetails());
            insertData.setString(4, country.getStampImage());
            insertData.setString(5, country.getBgImage());
            insertData.execute();
        } catch (SQLException sqlexc) {
            System.err.println(sqlexc);
        }
    }


    public void update(Country country) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE country SET country_name = ?, required_exp = ?, country_details = ?, stamp_name = ?, bg_name = ?" +
                            " WHERE country_id = ?"
            );
            updateData.setString(1, country.getCountryName());
            updateData.setInt(2, country.getRequiredEXP());
            updateData.setString(3, country.getCountryDetails());
            updateData.setString(4, country.getStampImage());
            updateData.setString(5,country.getBgImage());
            updateData.setInt(6, country.getCountryID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void deleteSession(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM country WHERE country_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM country");
            while (rs.next()) {
                countries.add(
                        new Country(
                                rs.getString("country_name"),
                                rs.getInt("required_exp"),
                                rs.getString("stamp_name"),
                                rs.getString("bg_name")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return countries;
    }

    public Country getByCountryId(int countryID) {
        try {
            PreparedStatement getCountry = connection.prepareStatement(
                    "SELECT * FROM country WHERE country_id = ?");
            getCountry.setInt(1, countryID);
            ResultSet rs = getCountry.executeQuery();
            if (rs.next()) {
                return new Country(
                        rs.getString("country_name"),
                        rs.getInt("required_exp"),
                        rs.getString("stamp_name"),
                        rs.getString("bg_name")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }
    public List<String> getLockedCountryNamesByUserId(int userId) {
        List<String> lockedCountryNames = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.country_name FROM country c " +
                            "JOIN " + "exploration e ON e.country_id = c.country_id " +
                            "WHERE e.user_id = ? AND e.lockedStatus = 1");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lockedCountryNames.add(resultSet.getString("country_name"));
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lockedCountryNames;
    }

    public List<String> getUnlockedCountryNamesByUserId(int userId) {
        List<String> lockedCountryNames = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.country_name FROM country c " +
                            "JOIN " + "exploration e ON e.country_id = c.country_id " +
                            "WHERE e.user_id = ? AND e.lockedStatus = 0");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lockedCountryNames.add(resultSet.getString("country_name"));
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lockedCountryNames;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
