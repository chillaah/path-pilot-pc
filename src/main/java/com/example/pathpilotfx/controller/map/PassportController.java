package com.example.pathpilotfx.controller.map;
import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.CountryDAO;
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
    @FXML
    private Button viewButton;
    private CountryDAO countryDAO;
    private Object newVal;
    public PassportController() {
        // Initialize the countryDAO object
        this.countryDAO = new CountryDAO();
    }

    public void initialize() {
        ObservableList<String> countries = FXCollections.observableArrayList(countryDAO.getUnlockedCountryNamesByUserId(SessionManager.getLoggedInUserId()));
        System.out.println(countryDAO.getUnlockedCountryNamesByUserId(SessionManager.getLoggedInUserId()));
        countryListView.setItems(countries);
        countryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            newVal = newValue;
            System.out.println("Selected item: " + newValue);
            viewButton.setVisible(true);
        });

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
    public void onViewButtonClick() throws IOException {
        Stage stage = (Stage) viewButton.getScene().getWindow();
        if(newVal.toString().equals("Sri Lanka")){
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("srilanka-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
        }
        else
        {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(newVal.toString().toLowerCase() + "-view.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 700, 400);
            stage.setScene(scene);
        }


    }
}
