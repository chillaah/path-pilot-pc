package com.example.pathpilotfx.controller.map;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pathpilotfx.model.Country;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Controller class for the passport location view.
 */
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

    @FXML
    private Pane greyPane;

    private Country country;
    private boolean isSelected = false;

    private PassportViewController passportViewController;
    private LockedController lockedController;
    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {

    }

    /**
     * Sets the country information and updates the view.
     *
     * @param country The country for which the passport location is being displayed.
     */
    public void setCountry(Country country) {
        System.out.println("Country Name: "+ country.getCountryName() + " , Locked:" + country.isLocked());
        if(!country.isLocked()){
            greyPane.setVisible(false);
        }
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

    /**
     * Sets the PassportViewController instance.
     *
     * @param passportViewController The PassportViewController instance to set.
     */
    public void setPassportViewController(PassportViewController passportViewController) {
        this.passportViewController = passportViewController;
    }

    public void setLockedController(LockedController lockedController) {
        this.lockedController = lockedController;
    }

    /**
     * Handles the mouse click event to select the country.
     *
     * @param event The mouse event.
     */
    @FXML
    void selectCountry(MouseEvent event) {
        if (passportViewController != null){
            passportViewController.updateSelectedCountry(country);
        }
        else{
            lockedController.updateSelectedCountry(country);
        }

    }

    /**
     * Applies the border to indicate selection.
     */
    public void applyBorder() {
        // Set border for the selected country
        Border border = new Border(new BorderStroke(
                Color.BLACK, // Border color
                BorderStrokeStyle.SOLID, // Border style
                CornerRadii.EMPTY, // Corner radii
                new BorderWidths(5) // Border widths (thickness)
        ));
        locationPane.setBorder(border);
    }

    /**
     * Removes the border to indicate deselection.
     */
    public void removeBorder() {
        // If already selected, remove border
        locationPane.setBorder(null);
    }
}


