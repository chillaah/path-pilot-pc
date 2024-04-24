package com.example.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PostcardDAO {
    private Connection connection;

    public PostcardDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Postcard postcard) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO postcard VALUES(?,?,?)");
            insertData.setInt(1, postcard.getUserID());
            insertData.setInt(2, postcard.getPostcardID());
            insertData.setBlob(3, postcard.getPostcardBlob());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Postcard postcard) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE postcard SET user_id = ?, postcard_id = ?, postcard_blob = ? WHERE postcard_id = ?"
            );
            updateData.setInt(1, postcard.getUserID());
            updateData.setInt(2, postcard.getPostcardID());
            updateData.setBlob(3, postcard.getPostcardBlob());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deletePostcard(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM postcard WHERE postcard_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Postcard> getAll() {
        List<Postcard> postcards = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM postcard");
            while (rs.next()) {
                postcards.add(
                        new Postcard(
                                rs.getInt("user_id"),
                                rs.getInt("postcard_id"),
                                rs.getBlob("postcard_blob")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return postcards;
    }

    public Postcard getByPostcardId(int postcardID) {
        try {
            PreparedStatement getPostcard = connection.prepareStatement("SELECT * FROM postcard WHERE postcard_id = ?");
            getPostcard.setInt(1, postcardID);
            ResultSet rs = getPostcard.executeQuery();
            if (rs.next()) {
                return new Postcard(
                        rs.getInt("user_id"),
                        rs.getInt("postcard_id"),
                        rs.getBlob("postcard_blob")
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
