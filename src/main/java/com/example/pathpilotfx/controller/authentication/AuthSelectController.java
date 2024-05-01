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


public class AuthSelectController {

    public ToggleGroup group;
    @FXML
    private Button nextButton;
    @FXML
    private RadioButton loginButton;
    @FXML
    private RadioButton createAccountButton;

    private static int authType;

    public static int getAuthType() {
        return authType;
    }

    public static void setAuthType(int value) {
        authType = value;
    }

    @FXML
    protected void onAuthSelectClick() {

        if (loginButton.isSelected()) {
            setAuthType(0);
            System.out.println("login selected");
        }

        else if (createAccountButton.isSelected()) {
            setAuthType(1);
            System.out.println("CA selected");
        }

        boolean accepted = loginButton.isSelected() || createAccountButton.isSelected();
        nextButton.setDisable(!accepted);
    }

    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("authentication.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MainApplication.WIDTH, MainApplication.HEIGHT);
//        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
    }
}