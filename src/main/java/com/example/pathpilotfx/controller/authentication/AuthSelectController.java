package com.example.pathpilotfx.controller.authentication;

import com.example.pathpilotfx.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controller for handling user authentication selection.
 */
public class AuthSelectController {

    public ToggleGroup group;
    @FXML
    private Button nextButton;
    @FXML
    private RadioButton loginButton;
    @FXML
    private RadioButton createAccountButton;

    private static int authType;

    /**
     * Gets the authentication type.
     *
     * @return The authentication type.
     */
    public static int getAuthType() {
        return authType;
    }

    /**
     * Sets the authentication type.
     *
     * @param value The authentication type value to be set.
     */
    public static void setAuthType(int value) {
        authType = value;
    }

    /**
     * Handles the event when an authentication selection is made.
     */
    @FXML
    protected void onAuthSelectClick() {

        if (loginButton.isSelected()) {
            setAuthType(0);
        }

        else if (createAccountButton.isSelected()) {
            setAuthType(1);
        }

        boolean accepted = loginButton.isSelected() || createAccountButton.isSelected();
        nextButton.setDisable(!accepted);
    }

    /**
     * Handles the event when the Next button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authentication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
    }
}