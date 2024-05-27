package com.example.pathpilotfx.controller.map;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.countries.SelectedCountry;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.model.Country;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.pathpilotfx.MainApplication.db;

/**
 * Controller for managing the map view.
 */
public class MapController {

    public Button passportButton;
    public Button lockedButton;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView stamp;


    @FXML
    void initialize() {
        ExplorationDAO explorationDAO = new ExplorationDAO();
        Country currentCountry = explorationDAO.getCountryCurrent(SessionManager.getLoggedInUserId());

        String fileName = currentCountry.getStampImage() +".png";

        String relativeImagePath = "/com/example/pathpilotfx/assets/" + fileName;
        URL imageUrl = getClass().getResource(relativeImagePath);

        Image image = new Image(String.valueOf(imageUrl));
        stamp.setImage(image);
    }
    /**
     * Handles the event when the Passport button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    protected void onPassportSelect() throws IOException {
        //Stage stage = (Stage) passportButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("passport-view.fxml"));
        AnchorPane addPassportView = loader.load();
        rootAnchorPane.getChildren().setAll(addPassportView);

    }

    /**
     * Handles the event when the Locked button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    public void onLockedSelect() throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/lockedlocation-view.fxml"));
            AnchorPane lockedLocView = loader.load();
            rootAnchorPane.getChildren().setAll(lockedLocView);

        } catch(IOException e){
            System.out.println("LockedLocation View has not been found");
            throw new RuntimeException(e);
        }
    }


}
