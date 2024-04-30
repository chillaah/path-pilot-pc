package com.example.pathpilotfx.controller.timer;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class TimerSettingsController {
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private TextField workLength;
    @FXML
    private TextField breakLength;
    @FXML
    private Label focusLengthError;
    @FXML
    private Label breakLengthError;

    @FXML
    protected void onConfirmButtonClick () throws IOException {
        boolean passedValidation = true;
        //checks if focus duration is a whole number
        if(!workLength.getText().matches("\\d+")) {
            focusLengthError.setText("*please enter a whole number");
            passedValidation = false;
        } else {
            focusLengthError.setText("");
        };

        if(!breakLength.getText().matches("\\d+")) {
            breakLengthError.setText("*please enter a whole number");
            passedValidation = false;
        } else {
            focusLengthError.setText("");
        };

        if(passedValidation) {
            AnchorPane timerSettings = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml")));
            rootAnchorPane.getChildren().setAll(timerSettings);
        }

    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        AnchorPane timerSettings = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml")));
        rootAnchorPane.getChildren().setAll(timerSettings);
    }
}
