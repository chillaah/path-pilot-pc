package com.example.pathpilotfx.database;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 Class for Country for SQLite queries
 **/
public class CountryDAO {
    private Connection connection;

    /**
     * Constructs a new ToDoDAO object and initializes the database connection.
     */
    public CountryDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Inserts country data into the database.
     *
     * @param country The Country object containing the data to be inserted.
     */
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

    /**
     * Updates country data in the database.
     *
     * @param country The Country object containing the updated data.
     */
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

    /**
     * Deletes country data from the database.
     *
     * @param id The ID of the country to be deleted.
     */
    public void delete(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM country WHERE country_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Retrieves all country data from the database.
     *
     * @return A list of Country objects containing all country data.
     */
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

    public List<Country> getLocked() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            PreparedStatement getLocked = connection.prepareStatement("SELECT * FROM country c " +
                    "JOIN " + "exploration e ON e.country_id = c.country_id " +
                    "WHERE e.user_id = ? AND e.lockedStatus = 1");
            getLocked.setInt(1, SessionManager.getLoggedInUserId());
            ResultSet rs = getLocked.executeQuery();
            while (rs.next()) {
                countries.add(
                        new Country(
                                rs.getInt("country_id"),
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



    /**
     * Retrieves country data for a specific country ID from the database.
     *
     * @param countryID The ID of the country.
     * @return A Country object containing the data for the specified country ID.
     */
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

    /**
     * Retrieves the names of locked countries for a specific user ID.
     *
     * @param userId The ID of the user.
     * @return A list of locked country names.
     */
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

    /**
     * Retrieves the names of unlocked countries for a specific user ID.
     *
     * @param userId The ID of the user.
     * @return A list of unlocked country names.
     */
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

    public String getStampByCID(int countryID) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT stamp_name from Country where country_id = ?");
            statement.setInt(1, countryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("stamp_name");
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }


    /**
     * Closes the database connection.
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
