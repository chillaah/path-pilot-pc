package com.example.pathpilotfx;

import com.example.pathpilotfx.database.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;


/**
 * Main class for the Path Pilot application.
 */
public class MainApplication extends Application {

    private static final String TITLE = "Path Pilot";
    private static final double WIDTH = 700;
    private static final double HEIGHT = 400;
    private static final UserDAO db = new UserDAO();

    /**
     * Returns the width of the application window.
     */
    public static double getWidth() {
        return WIDTH;
    }

    /**
     * Returns the height of the application window.
     */
    public static double getHeight() {
        return HEIGHT;
    }

    /**
     * Returns the database.
     */
    public static UserDAO getDB() {
        return db;
    }

    /**
     * Starts the application.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an input or output exception occurred.
     */
    @Override
    public void start(Stage stage) throws IOException {

        // Load the authentication view
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authentication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/styles.css")).toExternalForm());

        // Set the stage title and scene
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // clears all database entries for testing purposes
        // db.deleteAllUsers();

        launch();
    }
}