package com.example.pathpilotfx.controller.map;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.navigation.SideBarController;
import com.example.pathpilotfx.database.CountryDAO;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Exploration;
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
import java.util.ArrayList;


public class LockedController {

    @FXML
    public ListView countryListView;
    @FXML
    private Button backButton;
    @FXML
    private Button viewButton;
    private Object newVal;

    private CountryDAO countryDAO;

    public LockedController() {
        // Initialize the countryDAO object
        this.countryDAO = new CountryDAO();
    }

    public void initialize() {
        if (countryDAO != null) {
            ObservableList<String> countries = FXCollections.observableArrayList(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()));
            System.out.println(countryDAO.getLockedCountryNamesByUserId(SessionManager.getLoggedInUserId()));
            countryListView.setItems(countries);
            countryListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
            {
                newVal = newValue;
                System.out.println("Selected item: " + newValue);
                viewButton.setVisible(true);
            });
        } else {
            System.out.println("countryDAO is null.");
        }
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
