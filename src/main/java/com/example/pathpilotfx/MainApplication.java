package com.example.pathpilotfx;

import com.example.pathpilotfx.database.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class MainApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Path Pilot";
    public static final double WIDTH = 640/2.0;
    public static final double HEIGHT = 360/1.5;
    public static UserDAO db = new UserDAO();
    public static String AusImagePath = "src/main/resources/assets/australia-stamp.jpeg";
    public static String FrImagePath ="src/main/resources/assets/France.jpeg";
    public static String JPImagePath ="src/main/resources/assets/Japan.jpeg";
    public static String SLImagePath ="src/main/resources/assets/srilanka-stamp.jpeg";

    @Override
    public void start(Stage stage) throws IOException {
//        Connection connection = DatabaseConnection.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("auth-select.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // clears all database entries
//        db.deleteAllUsers();

        //Logic follows the lecture material, so updating/inserting work the same.
        launch();
    }
}