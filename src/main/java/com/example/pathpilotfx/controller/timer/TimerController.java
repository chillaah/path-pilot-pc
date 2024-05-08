package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.todolist.AddItemFormController;
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
    private AnchorPane rootAnchorPane; // the beige pane within which all views are to be loaded
    @FXML
    private Timeline timerTimeline; //

    @FXML
    private Label timerDisplay; // timer display

    @FXML
    private Label timerType; // label describing either break or focus timer

    @FXML
    private Button settingsButton;

    @FXML
    private AnchorPane taskPopUp; // focus task popup (when a timer started corresponding to a task)

    @FXML
    private Label taskPopUpLabel; // task name or details in taskPopUp label

    @FXML
    private Button crossButton; // button to exit focus task

    public Pomodoro sessionTimer; // Pomodoro instance
    private Task task; // task instance (when a timer started corresponding to a task)
    private boolean taskMode = false; // determines if the timer started is corresponding to a task
    private boolean isCrossButtonPressed = false;

    @FXML
    public void initialize(){
        taskPopUp.setVisible(taskMode); // visibility of the task pop-up based on task mode
        setupTimer();
        // Add event handler for mouse click on the cross button
        crossButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            isCrossButtonPressed = true; // Set the flag to true when the crossButton is clicked
            showPopup(); // Show the popup
        });

        // Add mouse released event handler to set the flag to false when the crossButton is released
        crossButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
            isCrossButtonPressed = false; // Set the flag to false when the crossButton is released
        });
    }
    @FXML
    protected void onStartButtonClick() {
        timerTimeline.play();
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        //loads the respective settings page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/timerSettings.fxml"));
        AnchorPane timerSettings = loader.load();
        rootAnchorPane.getChildren().setAll(timerSettings);

        // create an instance of timersettingscontroller and preload the fields in that class
        TimerSettingsController timersettingscontroller = loader.getController();
        timersettingscontroller.preloadFields(sessionTimer);
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
    } // toggles between timer type

    private void showPopup() { // Pop up that asks if the focus task has been completed
        try {
            // load respective screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/TaskTimer.fxml"));
            Parent loadedContent = loader.load();
            // pass the focus task to the task TaskTimer view
            TaskTimerController taskTimerController = loader.getController();
            taskTimerController.setTaskTimer(task);
            // checks if the popup is displayed because the timer has finished or if the focus task is closed.
            // If the timer has finished (seconds == 0), sets timer_finish flag in the TaskTimerController to true
            if(this.sessionTimer.getSeconds() == 0){
                taskTimerController.timer_finish = true;
            }
            taskTimerController.initialize(); // initialise
            AnchorPane wrappedContent = new AnchorPane(loadedContent); // put the loaded content inside anchor pane

            rootAnchorPane.getChildren().setAll(wrappedContent); // set rootAnchorPane to display wrappedContent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // sets up the timer
    public void setupTimer() {
        //initialise the timer with default values
        if (this.sessionTimer == null){
            System.out.println("timer is null right now");
            //sessionTimer = new Pomodoro(25, 5);
            sessionTimer = new Pomodoro(1, 5);
        }
        else {
            sessionTimer.resetTimer();
        }
        // sets display text on the timer display label
        timerDisplay.setText(sessionTimer.getDisplay());
        timerTimeline = sessionTimer.setTimeline();
        //Initializes the timeline for the timer with a KeyFrame updating the display every second.
        timerTimeline.getKeyFrames().addAll(new KeyFrame(Duration.seconds(1), this::updateTimerDisplay));
        timerTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void setTimerAfterSettings(Pomodoro timer){
        this.sessionTimer = timer;
        timerDisplay.setText(sessionTimer.getDisplay());
//        setupTimer();
//        initialize();


    }

    // Updates the timer by decreasing the remaining seconds, setting the display text accordingly
    private void updateTimerDisplay(ActionEvent event) {
        sessionTimer.decreaseSeconds();
        timerDisplay.setText(sessionTimer.getDisplay());
        //If the timer finishes or cross button is pressed
        if (sessionTimer.getSeconds() == 0 || isCrossButtonPressed) {
            timerTimeline.stop();
            // If a task is associated with the timer, shows popup
            if (task != null){showPopup();}
            // toggle timer type and reset timer
            else{handleRestTimer();}
        }
    }

    // Toggles timer type and resets the timer
    public void handleRestTimer() {
        timerTimeline.stop();
        timerType.setText(sessionTimer.toggleType());
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());

    }

    /**
     * Sets the task mode and updates the task-related UI elements accordingly.
     *
     * @param taskMode Whether the task mode is active or not.
     * @param task     The task object associated with the task mode.
     */
    public void setTaskMode(boolean taskMode, Task task) {
        this.taskMode = taskMode;
        this.task = task;
        taskPopUp.setVisible(this.taskMode); // Update the visibility of the task pop-up
        taskPopUpLabel.setText("Focus Task: " + this.task.getTask()); // Update the task pop-up label
    }



}