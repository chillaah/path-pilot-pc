package com.example.pathpilotfx;

import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Exploration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class MainApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Path Pilot";
    public static final double WIDTH = 700;
    public static final double HEIGHT = 400;
    public static UserDAO db = new UserDAO();
    public static ExplorationDAO db2 = new ExplorationDAO();

    @Override
    public void start(Stage stage) throws IOException {
//        Connection connection = DatabaseConnection.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authentication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
//        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


//      clears all database entries
//        db.deleteAllUsers();
//        db2.deleteAllExplorations();

        launch();
    }
}