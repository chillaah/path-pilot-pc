package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.ExplorationDAO;
import com.example.pathpilotfx.database.PomodoroDAO;
import com.example.pathpilotfx.database.UserDAO;
import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.Task;
import com.example.pathpilotfx.model.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


import java.io.IOException;
import java.util.List;

/**
 * Controller class for managing the timer functionality.
 */
public class TimerController {
    @FXML
    private Button stopButton;
    @FXML
    private Button startButton;

    @FXML
    private AnchorPane rootAnchorPane; // the beige pane within which all views are to be loaded
    @FXML
    private Timeline timerTimeline;

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

//    @FXML
//    private Label taskPopUpLabel1;
    @FXML
    private Label currentDestination;
    @FXML
    private Label nextDestination;

    @FXML
    private Button crossButton;

    public Pomodoro sessionTimer; // Pomodoro instance
    private Task task; // task instance (when a timer started corresponding to a task)
    private boolean taskMode = false; // determines if the timer started is corresponding to a task
    private boolean isCrossButtonPressed = false;

    //establish connection with the timer
    PomodoroDAO timer = new PomodoroDAO();
    ExplorationDAO explorationDAO = new ExplorationDAO();
    UserDAO userDAO = new UserDAO();
    private int userID = SessionManager.getLoggedInUserId();
    private User user = userDAO.getByUserId(userID);

    List<String> destination = explorationDAO.getNextDestination(userID);
    private Integer expNeeded = Integer.parseInt(destination.get(1)) - user.getExp();

    /**
     * Initializes the timer controller.
     */
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
        currentDestination.setText("Currently exploring: \n" + explorationDAO.getCurrentExploring(userID)
        + "\n" + "Current exp: \n" + user.getExp());
        if (destination != null) {
            nextDestination.setVisible(true);
            nextDestination.setText("Next destination: \n" +
                destination.get(0) + "\n" + "Needed exp: \n" + expNeeded);

        }
    }

    /**
     * Starts the timer.
     */
    @FXML
    protected void onStartButtonClick() {
        timerTimeline.play();
        startButton.setVisible(false);
        stopButton.setVisible(true);

    }

    /**
     * Opens the settings page for the timer.
     *
     * @throws IOException if an I/O error occurs.
     */
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

    /**
     * Stops the timer.
     */
    @FXML
    protected void onStopButtonClick() {
        timerTimeline.stop();
        sessionTimer.resetTimer();
        timerDisplay.setText(sessionTimer.getDisplay());
        startButton.setVisible(true);
        stopButton.setVisible(false);
    }

    /**
     * Toggles between timer types.
     */
    @FXML
    protected void onTypeButtonClick(){

        handleRestTimer();
        startButton.setVisible(true);
        stopButton.setVisible(false);
    }

    /**
     * Shows a popup asking if the focus task has been completed.
     */
    private void showPopup() {
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

    /**
     * Sets up the timer.
     */
    public void setupTimer() {
        //initialise the timer with default values
        if (this.sessionTimer == null){
            System.out.println("timer is null right now");
            sessionTimer = timer.getTimerByUser(SessionManager.getLoggedInUserId());
            if(sessionTimer == null){ //Timer settings not found. Using default settings.
                sessionTimer = new Pomodoro(25, 5);
            }
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



    }

    /**
     * Updates the timer by decreasing the remaining seconds, setting the display text accordingly.
     *
     * @param event the event triggering the timer update.
     */
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

    /**
     * Toggles the timer type between focus and break and resets the timer accordingly.
     */
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
        taskPopUp.setVisible(this.taskMode); // update the visibility
        taskPopUpLabel.setText(this.task.getTask());
//        taskPopUpLabel1.setText(this.task.getDescription());

    }



}