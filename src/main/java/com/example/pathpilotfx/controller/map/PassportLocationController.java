package com.example.pathpilotfx.controller.map;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PassportLocationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane locationPane;

    @FXML
    private Label locationLabel;

    @FXML
    private ImageView locationStamp;

    private Country country;


    @FXML
    void initialize() {


    }

    public void setCountry(Country country) {
        this.country = country;
        locationLabel.setText(country.getCountryName());

        String fileName = this.country.getStampImage() +".png";
        System.out.println("stamp: "+ fileName);

        String relativeImagePath = "/com/example/pathpilotfx/assets/" + fileName;
        System.out.println("stamp: "+ relativeImagePath);

        URL imageUrl = getClass().getResource(relativeImagePath);
        System.out.println("stamp: "+ String.valueOf(imageUrl));

        Image passport = new Image(String.valueOf(imageUrl));
        locationStamp.setImage(passport);

    }
}
