package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Image;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ImageDAO {
    private Connection connection;

    public ImageDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Image image) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO image VALUES(?)");
            insertData.setInt(1, image.getLocationID());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Image image) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE image SET location_id = ? WHERE image_id = ?"
            );
            updateData.setInt(1, image.getImageID());
            updateData.setInt(2, image.getLocationID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteImage(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement(
                    "DELETE FROM image WHERE image_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Image> getAll() {
        List<Image> images = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM image");
            while (rs.next()) {
                images.add(
                        new Image(
                                rs.getInt("image_id"),
                                rs.getInt("location_id")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return images;
    }

    public Image getByImageId(int id) {
        try {
            PreparedStatement getImage = connection.prepareStatement(
                    "SELECT * FROM image WHERE image_id = ?");
            getImage.setInt(1, id);
            ResultSet rs = getImage.executeQuery();
            if (rs.next()) {
                return new Image(
                        rs.getInt("image_id"),
                        rs.getInt("location_id")
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
