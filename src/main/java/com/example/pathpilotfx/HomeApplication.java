package com.example.pathpilotfx;

import com.example.pathpilotfx.Database.ToDoDOA;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HomeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("navigation-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Path Pilot");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();

    }
}