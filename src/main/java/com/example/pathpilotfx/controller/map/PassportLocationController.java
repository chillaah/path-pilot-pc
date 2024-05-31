package com.example.pathpilotfx.controller.map;

import java.net.URL;
import com.example.pathpilotfx.model.Country;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


/**
 * Controller class for the passport location view.
 */
public class PassportLocationController {

    @FXML
    private Pane locationPane;
    @FXML
    private Label locationLabel;
    @FXML
    private ImageView locationStamp;
    @FXML
    private Pane greyPane;

    private Country country;
    private PassportViewController passportViewController;
    private LockedController lockedController;

    /**
     * Sets the country information and updates the view.
     *
     * @param country The country for which the passport location is being displayed.
     */
    public void setCountry(Country country) {
        if (!country.isLocked()) {
            greyPane.setVisible(false);
        }
        this.country = country;
        locationLabel.setText(country.getCountryName());

        String fileName = this.country.getStampImage() + ".png";
        String relativeImagePath = "/com/example/pathpilotfx/assets/" + fileName;
        URL imageUrl = getClass().getResource(relativeImagePath);
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

    /**
     * Sets the LockedController instance.
     *
     * @param lockedController The LockedController instance to set.
     */
    public void setLockedController(LockedController lockedController) {
        this.lockedController = lockedController;
    }

    /**
     * Handles the mouse click event to select the country.
     */
    @FXML
    public void selectCountry() {
        if (passportViewController != null) {
            passportViewController.updateSelectedCountry(country);
        } else {
            lockedController.updateSelectedCountry(country);
        }
    }

    /**
     * Applies the border to indicate selection.
     */
    public void applyBorder() {
        Border border = new Border(new BorderStroke(Color.BLACK, // Border color
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
        locationPane.setBorder(null);
    }
}
