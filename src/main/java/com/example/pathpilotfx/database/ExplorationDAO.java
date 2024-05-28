package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;
import com.example.pathpilotfx.model.User;

import java.sql.*;
import java.util.*;
/**
 Class for Exploration Data Access Object for SQLite queries
 **/
public class ExplorationDAO {
    private Connection connection;

    public ExplorationDAO() {
        connection = DatabaseConnection.getInstance();
    }

    //default: Exploration exploration = new Exploration(1, 'Exploring', 0, 0)
    //ExplorationDAO explorationdao = new ExplorationDAO
    //explorationdao.insert(exploration)
    /**
     Method that inserts exploration data
     **/

    public void insert(Exploration exploration) {
        UserDAO userDAO = new UserDAO();
        try {
            int userId = userDAO.getLatestUser();
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO exploration VALUES(?,?,?,?,?)");
            insertData.setInt(1, userId);
            insertData.setInt(2, exploration.getCountryID());
            insertData.setString(3, exploration.getStatus());
            insertData.setBoolean(4, exploration.isLocked());
            insertData.setBoolean(5, exploration.isFavourited());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }
    /**
     Method that updates the exploration data
     **/

    public void update(Exploration exploration) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE exploration SET status = ?, lockedStatus = ?, favourited = ? WHERE user_id = ? AND country_id = ?"
            );
            updateData.setString(1, exploration.getStatus());
            updateData.setBoolean(2, exploration.isLocked());
            updateData.setBoolean(3, exploration.isFavourited());
            updateData.setInt(4, exploration.getUserID());
            updateData.setInt(5, exploration.getCountryID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     Method that deletes the exploration data
     **/
    public void delete(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM exploration WHERE country_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /**
     Method that gets all exploration data
     **/
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


    /**
     Method that gets specific country exploration data
     **/
    public List<Exploration> getAllbyUser(int user_ID) {
        List<Exploration> explorationData = new ArrayList<>();
        try {
            PreparedStatement getAllStatement = connection.prepareStatement("SELECT * FROM exploration WHERE user_id = ?");
            getAllStatement.setInt(1, user_ID);
            ResultSet rs = getAllStatement.executeQuery();
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

    /**
     Method that gets  country exploration data for a specific user
     **/
    public Exploration getByUserIdCountryId(int userID, int countryID) {
        try {
            PreparedStatement explorationData = connection.prepareStatement("SELECT * FROM exploration WHERE user_id = ? and country_id = ?");
            explorationData.setInt(1, userID);
            explorationData.setInt(2, countryID);
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
    /**
     Method that gets the country the user is currently exploring
     @return returns the countryName
     **/
    public String getCurrentExploring(int id) {
        String countryName = "";
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "SELECT c.country_name " +
                            "FROM exploration e LEFT JOIN country c " +
                            "ON e.country_id = c.country_id " +
                            "WHERE e.status = 'Exploring' AND user_id = ?");
            currExploring.setInt(1, id);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                countryName = resultSet.getString("country_name");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        catch (Exception exception){return "";}
        return countryName;
    }

    /**
     * Retrieves the first locked country ID for a specific user from the database.
     *
     * @param id the ID of the user whose locked country is to be retrieved
     * @return the country ID of the first locked country for the specified user,
     *         or -1 if no locked country is found or an error occurs
     */
    public int getFirstLockedCountry(int id) {
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "SELECT country_id " +
                            "FROM exploration " +
                            "WHERE lockedStatus = 1 AND user_id = ?");
            currExploring.setInt(1, id);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("country_id");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return -1;
    }

    /**
     * Unlocks a country for a specific user by updating the locked status in the database.
     *
     * @param userID the ID of the user who is unlocking the country
     * @param countryID the ID of the country to be unlocked
     */
    public void unlockCountry(int userID, int countryID) {
        try {
            PreparedStatement unlock = connection.prepareStatement(
                    "UPDATE exploration SET lockedStatus = 0 WHERE user_id = ? AND country_id = ?");
            unlock.setInt(1, userID);
            unlock.setInt(2, countryID);
            unlock.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     Method that deletes all exploration data
     **/
    public void deleteAllExplorations() {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM exploration");
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    
    public Country getCountryCurrent(int userID){
        Country country = null;
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "SELECT * " +
                            "FROM country c LEFT JOIN exploration e " +
                            "ON c.country_id = e.country_id " +
                            "WHERE e.status = 'Exploring' AND e.user_id = ?");
            currExploring.setInt(1, userID);
            ResultSet rs = currExploring.executeQuery();
            if (rs.next()) {
                country = new Country(
                        rs.getString("country_name"),
                        rs.getInt("required_exp"),
                        rs.getString("country_details"),
                        rs.getString("stamp_name"),
                        rs.getString("bg_name")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return country;

    }


    /**
     Method that counts the number of explored countries.
     @param userID the userID
     @return the count of explored countries
     **/
    public int countExplored(int userID) {
        int count = 0;
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "Select COUNT(*) AS count FROM exploration " +
                            "WHERE status = 'Explored' AND user_id = ?");
            currExploring.setInt(1, userID);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return count;
    }

    /**
     Method that counts the number of unexplored countries.
     @param userID the userID
     @return the count of unexplored countries
     **/
    public int countLocked(int userID) {
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "Select COUNT(*) AS count FROM exploration " +
                            "WHERE lockedStatus = 1 AND user_id = ?");
            currExploring.setInt(1, userID);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return -1;
    }

    /**
     Method that counts the number of unlocked countries.
     @param userID the userID
     @return the count of unlocked countries
     **/
    public int countUnlocked(int userID) {
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "Select COUNT(*) AS count FROM exploration " +
                            "WHERE lockedStatus = 0 AND user_id = ?");
            currExploring.setInt(1, userID);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return -1;
    }

    /**
     Method that gets the first locked destination with the lowest exp
     @return Returns a list of the country name and the required exp
     @param id the userID
     **/
    public  List<String> getNextDestination(int id) {
        String countryName = "";
        int countryExp = 0;
        List<String> destination = new ArrayList<>();
        try {
            PreparedStatement currExploring = connection.prepareStatement(
                    "SELECT c.country_name, c.required_exp " +
                            "FROM exploration e LEFT JOIN country c " +
                            "ON e.country_id = c.country_id " +
                            "WHERE e.lockedStatus = 1 AND e.user_id = ?" +
                            "ORDER BY c.required_exp ASC LIMIT 1");
            currExploring.setInt(1, id);
            ResultSet resultSet = currExploring.executeQuery();
            if (resultSet.next()) {
                countryName = resultSet.getString("country_name");
                countryExp = resultSet.getInt("required_exp");
                destination.add(countryName);
                destination.add(String.valueOf(countryExp));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        catch (Exception exception){return null;}

        return destination;
    }
    /**
     Method that closes the database connection
     **/
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
