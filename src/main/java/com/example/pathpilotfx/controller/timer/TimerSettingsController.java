package com.example.pathpilotfx.controller.timer;


import com.example.pathpilotfx.database.PomodoroDAO;
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

/**
 * Controller class for managing timer settings.
 */
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

    private Label timerType;

    private Pomodoro timer; // This keeps the instance of the timer in the app
    PomodoroDAO pomodoroDAO = new PomodoroDAO();

    /**
     * Initializes the controller.
     */
    @FXML
    void initialize(){

    }

    /**
     * Event handler for the confirm button click.
     * Validates the input, updates timer settings, and reloads the timer settings page.
     *
     * @throws IOException If an I/O error occurs.
     */
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
            int newWorkLength = Integer.parseInt(workLength.getText());
            int newRestLength = Integer.parseInt(breakLength.getText());
            this.timer.setWork(newWorkLength);
            this.timer.setRest(newRestLength);
            System.out.println("W "+ newWorkLength);
            pomodoroDAO.update(timer);

            loadTimerSettings();

        }

    }

    /**
     * Event handler for the reset button click.
     * Preloads the timer settings with the current timer instance.
     */
    @FXML
    protected void onResetButtonClick(){
        preloadFields(this.timer);
    }

    /**
     * Event handler for the cancel button click.
     * Loads the timer settings page.
     *
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    protected void onCancelButtonClick() throws IOException {
        loadTimerSettings();
    }

    /**
     * Loads the timer settings page.
     *
     * @throws IOException If an I/O error occurs.
     */
    private  void loadTimerSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml"));
        Parent timerSettings = loader.load();

        TimerController timerController = loader.getController();
        timerController.setTimerAfterSettings(this.timer);
        timerController.timerType.setText(this.timer.getTimerType());
        timerController.initialize();


        AnchorPane timerSettingContent = new AnchorPane(timerSettings);
        rootAnchorPane.getChildren().setAll(timerSettingContent);
    }
    /**
     * Preloads the fields in the timerSettings.fxml with the current timer instance.
     *
     * @param timer The current timer instance.
     */
    public void preloadFields(Pomodoro timer){
        this.timer = timer;
        workLength.setText(String.valueOf(timer.getWork()));
        breakLength.setText(String.valueOf(timer.getRest()));

    }

    public void setTimerType(Label tasktype){

    }

}
