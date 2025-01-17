package com.example.pathpilotfx.database;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.model.Country;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 Class for Country for SQLite queries
 **/
public class CountryDAO {
    private final Connection connection;

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
            insertData.setString(1, country.getCountryName());
            insertData.setInt(2, country.getRequiredEXP());
            insertData.setString(3, country.getCountryDetails());
            insertData.setString(4, country.getStampImage());
            insertData.setString(5, country.getBgImage());
            insertData.execute();
        } catch (SQLException e) {
            System.err.println(e);
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
                                rs.getString("country_details"),
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
     * Retrieves a list of countries that are locked for the currently logged-in user.
     * <p>
     * This method executes a SQL query to fetch all countries associated with the
     * currently logged-in user that have a locked status. The results are mapped
     * to a list of {@link Country} objects and returned.
     *
     * @return a list of {@link Country} objects that are locked for the currently logged-in user.
     *         If an SQL exception occurs, an empty list is returned.
     */
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
                                rs.getString("country_details"),
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
     * Retrieves the required experience points for a specific country ID.
     *
     * @param countryID The ID of the country.
     * @return The required experience points for the specified country ID.
     */
    public int getRequiredExpByCountryId(int countryID) {
        try {
            PreparedStatement getRequiredExp = connection.prepareStatement(
                    "SELECT required_exp FROM country WHERE country_id = ?");
            getRequiredExp.setInt(1, countryID);
            ResultSet rs = getRequiredExp.executeQuery();
            if (rs.next()) {
                return rs.getInt("required_exp");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return -1;
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
