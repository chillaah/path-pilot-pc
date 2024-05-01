package com.example.pathpilotfx.controller.map;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.navigation.SideBarController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;


public class LockedController {

    @FXML
    public ListView countryListView;
    @FXML
    private Button backButton;

    public void initialize() {
        ObservableList<String> countries = FXCollections.observableArrayList(
                "United States", "Canada", "United Kingdom", "Germany", "France",
                "Japan", "China", "Brazil", "India", "South Africa", "Russia", "Sri Lanka", "Italy",
                "Spain", "Mexico", "Argentina", "Egypt", "Nigeria", "Kenya", "Ghana", "Ethiopia");

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
