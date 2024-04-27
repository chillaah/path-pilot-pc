package com.example.pathpilotfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class HomeController {

    @FXML
    public static void authSuccess(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("navigation-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setScene(scene);
    }
}