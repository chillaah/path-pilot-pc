package main.java.com.example.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class UserDAO {
    private Connection connection;

    public UserDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(User user) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO user VALUES(?,?,?,?,?,?)");
            insertData.setInt(1, user.getUserID());
            insertData.setString(2, user.getUsername());
            insertData.setString(3, user.getEmail());
            insertData.setString(4, user.getPassword());
            insertData.setDate(5, user.getCreationDate());
            insertData.setInt(6, user.getExp());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(User user) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE user SET user_id = ?, username = ?, " +
                        "email = ?, password = ?, email = ?, creation_date = ?, " +
                        "exp = ? WHERE id = ?"

            );
            updateData.setInt(1, user.getUserID());
            updateData.setString(2, user.getUsername());
            updateData.setString(3, user.getEmail());
            updateData.setString(4, user.getPassword());
            updateData.setDate(5, user.getCreationDate());
            updateData.setInt(6, user.getExp());
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
                                rs.getDate("creation_date"),
                                rs.getInt("exp")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return users;
    }

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
                        rs.getDate("creation_date"),
                        rs.getInt("exp")
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
