package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private Connection connection;

    public CountryDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Country country) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO country VALUES(?,?,?)");
            insertData.setInt(1, country.getCountryID());
            insertData.setString(2, country.getCountryName());
            insertData.setInt(3, country.getRequiredEXP());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Country country) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE country SET country_id = ?, country_name = ?, " +
                            "required_exp = ?"
            );
            updateData.setInt(1, country.getCountryID());
            updateData.setString(2, country.getCountryName());
            updateData.setInt(3, country.getRequiredEXP());
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
                                rs.getInt("country_id"),
                                rs.getString("country_name"),
                                rs.getInt("required_exp")
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
                        rs.getInt("country_id"),
                        rs.getString("country_name"),
                        rs.getInt("required_exp")
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
