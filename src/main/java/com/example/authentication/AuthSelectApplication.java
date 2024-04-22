package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class AuthSelectApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Path Pilot";
    public static final double WIDTH = 640/2.0;
    public static final double HEIGHT = 360/1.5;

    @Override
    public void start(Stage stage) throws IOException {
//        Connection connection = DatabaseConnection.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(AuthSelectApplication.class.getResource("auth-select.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}