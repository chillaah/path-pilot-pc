package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 Class for User Data Access Object for SQLite queries
 **/
public class UserDAO {

    private Connection connection;

    /**
     * Initializes a new instance of the UserDAO class.
     */
    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     * Creates the 'user' table if it does not exist.
     */
    public void createTable() {

        Statement statement = null;

        try {
            statement = connection.createStatement();

            // SQL query
            String createTableSQL = "CREATE TABLE IF NOT EXISTS user (" +
                    "user_id INT AUTOINCREMENT PRIMARY KEY," +
                    "username TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "password TEXT NOT NULL," +
                    "creation_date TIMESTAMP NOT NULL," +
                    "exp INT NOT NULL" +
                    ")";


            statement.executeUpdate(createTableSQL);
            System.out.println("Table Created Successfully");

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    /**
     Method that inserts user data
     @param user the user instance with the data
     **/
    public void insert(User user) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO user (username, email, password, creation_date, exp) VALUES(?,?,?,?,?)");
            insertData.setString(1, user.getUsername());
            insertData.setString(2, user.getEmail());
            insertData.setString(3, user.getPassword());
            insertData.setTimestamp(4, user.getCreationDate());
            insertData.setInt(5, user.getExp());
            insertData.execute();
        } catch (SQLException sqlexc) {
            System.err.println(sqlexc);
        }
    }

    /**
     Method that updates user data
     @param user the user instance with the updated data
     **/
    public void update(User user) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE user SET username = ?, email = ?, password = ?, creation_date = ?, exp = ? WHERE user_id = ?"
            );
            updateData.setString(1, user.getUsername());
            updateData.setString(2, user.getEmail());
            updateData.setString(3, user.getPassword());
            updateData.setTimestamp(4, user.getCreationDate());
            updateData.setInt(5, user.getExp());
            updateData.setInt(6, user.getUserID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes a specific user's data based on the user ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteUser(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     * Deletes all user data from the database.
     */
    public void deleteAllUsers() {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM user");
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    /**
     Method that gets all user data
     @return a list with all user data
     **/
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM bankAccounts");
            while (rs.next()) {
                users.add(
                        new User(
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getTimestamp("creation_date"),
                                rs.getInt("exp")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }
    //to get the exp,you first get a user based on their ID and then using the returned user,
    // you can call the class getters e.g getExp() or getRequiredExp(). Concatenating them
    //can help with the exp / requiredexp part of the passport.

    /**
     * Retrieves user data for a specific user based on the user ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user instance containing the retrieved data.
     */
    public User getByUserId(int id) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getTimestamp("creation_date"),
                        rs.getInt("exp")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    /**
     * Retrieves the ID of a user based on their email.
     *
     * @param email The email of the user.
     * @return The ID of the user.
     */
    public int getIdByEmail(String email) {
        try {
            PreparedStatement getUser = connection.prepareStatement(
                    "SELECT user_id FROM user WHERE email = ?");
            getUser.setString(1, email);
            ResultSet rs = getUser.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
    }


    /**
     * Retrieves the ID of the latest user from the database.
     *
     * @return The ID of the latest user.
     */
    public int getLatestUser() {
        try {
            PreparedStatement getUserID = connection.prepareStatement(
                    "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1");
            ResultSet rs = getUserID.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return 0;
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

    /**
     * Checks if an email is available (i.e., not already in use) in the database.
     *
     * @param email The email to check.
     * @return True if the email is available; false otherwise.
     */
    public boolean isEmailAvailable(String email) {

        boolean available = false;

        try (
                PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?");
        ) {
            // Set the email parameter
            stmt.setString(1, email);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                // count = 1 if email available
                if (rs.next() && rs.getInt(1) > 0) {
                    available = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return available;
    }

    /**
     * Retrieves the hashed password associated with a given email from the database.
     *
     * @param email The email associated with the password.
     * @return The hashed password.
     */
    public String getStoredHashedPassword(String email) {

        try (PreparedStatement stmt = connection.prepareStatement("SELECT password FROM user WHERE email = ?")) {
            stmt.setString(1, email);

            // Execute the SQL query
            ResultSet rs = stmt.executeQuery();

            // If user exists, return the hashed password; otherwise, return null
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return "";
    }
}