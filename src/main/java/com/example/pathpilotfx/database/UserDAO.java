package com.example.pathpilotfx.database;

import com.example.pathpilotfx.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {

    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }

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

//    public boolean userExists(String email) throws SQLException {
//        // SQL query to check if a user with the given email exists
//        String query = "SELECT COUNT(*) AS count FROM users WHERE email = ?";
//
//        try (
//                PreparedStatement statement = connection.prepareStatement(query)
//        ) {
//            statement.setString(1, email);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    int count = resultSet.getInt("count");
//                    return count > 0; // If count > 0, user exists; otherwise, user does not exist
//                }
//            }
//        }
//        return false; // Return false if there was an error or no result was found
//    }

    public void insert(User user) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO user (user_id, username, email, password, creation_date, exp) VALUES(?,?,?,?,?,?)");
            insertData.setInt(1, user.getUserID());
            insertData.setString(2, user.getUsername());
            insertData.setString(3, user.getEmail());
            insertData.setString(4, user.getPassword());
            insertData.setTimestamp(5, user.getCreationDate());
            insertData.setInt(6, user.getExp());
            insertData.execute();
        } catch (SQLException sqlexc) {
            System.err.println(sqlexc);
        }
    }

    public void update(User user) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE user SET user_id = ?, username = ?, " +
                            "email = ?, password = ?, creation_date = ?, " +
                            "exp = ?"
            );
            updateData.setInt(1, user.getUserID());
            updateData.setString(2, user.getUsername());
            updateData.setString(3, user.getEmail());
            updateData.setString(4, user.getPassword());
            updateData.setTimestamp(5, user.getCreationDate());
            updateData.setInt(6, user.getExp());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    public void UpdateEXP(int userID, int exp) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE user SET exp = ? WHERE user_id = ?"
            );
            updateData.setInt(1, exp);
            updateData.setInt(2, userID);
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteAllUsers() {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM user");
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

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

    public User getLatestID(int id) {
        try {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM user order by DESC");
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

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

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