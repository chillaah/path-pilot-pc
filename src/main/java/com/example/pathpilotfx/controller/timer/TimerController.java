package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.Task;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Objects;


public class TimerController {
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    public Pomodoro sessionTimer;
    @FXML
    private Timeline timerTimeline;

    @FXML
    private Label timerDisplay;
    @FXML
    private Label timerType;
    @FXML
    private Button settingsButton;

    @FXML
    protected void onSettingsButtonClick() throws IOException {
            AnchorPane timerSettings = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/timerSettings.fxml")));
            rootAnchorPane.getChildren().setAll(timerSettings);
    }

    @FXML
    private AnchorPane taskPopUp;

    @FXML
    private Label taskPopUpLabel;


    @FXML
    private Button crossButton;

    private Task task;
    private boolean taskMode = false;

    private boolean isCrossButtonPressed = false;
    @FXML
    protected void onStartButtonClick() {
        timerTimeline.play();
    }

    @FXML
    protected void onStopButtonClick() {
        timerTimeline.stop();
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());
    }

    @FXML
    protected void onTypeButtonClick(){
        handleRestTimer();
    }

    private void showPopup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/TaskTimer.fxml"));
            Parent loadedContent = loader.load();
            TaskTimerController taskTimerController = loader.getController();
            taskTimerController.setTaskComplete(task); // Set the task before initializing the controller
            if(this.sessionTimer.getSeconds() == 0){
                taskTimerController.timer_finish = true;
            }
            taskTimerController.initialize();
            AnchorPane wrappedContent = new AnchorPane(loadedContent);
            rootAnchorPane.getChildren().setAll(wrappedContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupTimer() {
        sessionTimer = new Pomodoro(25, 5); // 5 minutes rest duration - UNCOMMENT
        //sessionTimer = new Pomodoro(10, 5,true);
        timerDisplay.setText(sessionTimer.getDisplay());
        timerTimeline = sessionTimer.setTimeline();
        timerTimeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), this::updateTimerDisplay));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void updateTimerDisplay(ActionEvent event) {
        sessionTimer.decreaseSeconds();
        timerDisplay.setText(sessionTimer.getDisplay());
        if (sessionTimer.getSeconds() == 0 || isCrossButtonPressed) {
            timerTimeline.stop();
            if (task != null){
                showPopup(); // Show popup only if it's rest time
            }
            else{
                timerType.setText(sessionTimer.toggleType());
                sessionTimer.resetTimer();
                timerDisplay.setText(sessionTimer.getDisplay());
            }
        }
    }

    public void handleRestTimer() {
        timerTimeline.stop();
        timerType.setText(sessionTimer.toggleType());
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());

    }
    @FXML
    public void initialize(){

        taskPopUp.setVisible(taskMode);
        setupTimer();
        crossButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            isCrossButtonPressed = true; // Set the flag to true when the crossButton is clicked
            showPopup(); // Show the popup
        });

        // Add mouse released event handler to set the flag to false when the crossButton is released
        crossButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            isCrossButtonPressed = false;
        });


   }


    public void setTaskMode(boolean taskMode, Task task) {
        this.taskMode = taskMode;
        this.task = task;
        taskPopUp.setVisible(this.taskMode); // update the visibility
        taskPopUpLabel.setText("Focus Task: " + this.task.getTask());

    }

}