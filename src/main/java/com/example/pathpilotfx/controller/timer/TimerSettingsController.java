package com.example.pathpilotfx.controller.timer;


import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.Task;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

    private Pomodoro timer; // This keeps the instance of the timer in the app

    @FXML
    void initialize(){

    }
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
            this.timer.setWork(Integer.parseInt(workLength.getText()));
            this.timer.setRest(Integer.parseInt(breakLength.getText()));

            loadTimerSettings();

        }

    }

    @FXML
    protected void onResetButtonClick(){
        preloadFields(this.timer);
    }

    @FXML
    protected void onCancelButtonClick() throws IOException {
        loadTimerSettings();
    }


    private  void loadTimerSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml"));
        Parent timerSettings = loader.load();

        TimerController timerController = loader.getController();
        timerController.setTimerAfterSettings(this.timer);
        timerController.initialize();


        AnchorPane timerSettingContent = new AnchorPane(timerSettings);
        rootAnchorPane.getChildren().setAll(timerSettingContent);
    }
    // Preloads the fields in the timerSettings.fxml current timer instance
    public void preloadFields(Pomodoro timer){
        this.timer = timer;
        workLength.setText(String.valueOf(timer.getWork()));
        breakLength.setText(String.valueOf(timer.getRest()));

    }

}
