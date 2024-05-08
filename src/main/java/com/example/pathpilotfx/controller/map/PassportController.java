package com.example.pathpilotfx.controller.map;
import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Exploration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.net.URL;

public class PassportController {

    @FXML
    public ListView countryListView;
    @FXML
    private Button backButton;

    public void initialize() {
        ObservableList<String> countries = FXCollections.observableArrayList(
                "Australia");

        countryListView.setItems(countries);
    }

    public void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("navigation-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);

//        SideBarController sbc = new SideBarController();
//        sbc.loadPage("map-view.fxml");
    }
}
