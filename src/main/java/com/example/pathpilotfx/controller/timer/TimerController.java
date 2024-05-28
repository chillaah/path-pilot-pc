package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.timer.TimerSettingsController;
import com.example.pathpilotfx.database.*;
import com.example.pathpilotfx.model.Pomodoro;
import com.example.pathpilotfx.model.Task;
import com.example.pathpilotfx.model.User;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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
    public Label timerType; // label describing either break or focus timer

    @FXML
    private Button settingsButton;

    @FXML
    private AnchorPane taskPopUp; // focus task popup (when a timer started corresponding to a task)

    @FXML
    private Label taskPopUpLabel; // task name or details in taskPopUp label

    @FXML
    private Button crossButton;

    @FXML
    private Label currentExp;

    @FXML
    private Label currentLocation;

    @FXML
    private Label needExp;

    @FXML
    private Label nextLocation;

    public Pomodoro sessionTimer; // Pomodoro instance
    private Task task; // task instance (when a timer started corresponding to a task)
    private boolean taskMode = false; // determines if the timer started is corresponding to a task
    private boolean isCrossButtonPressed = false;

    //establish connection with the timer
    PomodoroDAO timer = new PomodoroDAO();
    ExplorationDAO explorationDAO = new ExplorationDAO();
    UserDAO userDAO = new UserDAO();
    CountryDAO countryDAO = new CountryDAO();
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
        currentLocation.setText(explorationDAO.getCurrentExploring(userID));
        currentExp.setText(String.valueOf(user.getExp()));
        if (destination != null) {
            nextLocation.setVisible(true);
            nextLocation.setText(destination.get(0));
            needExp.setText(String.valueOf(expNeeded));

        }
        countryBackgroundImage();

        FadeTransition ft = new FadeTransition(Duration.millis(1000), rootAnchorPane);  // Adjust duration as desired
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

    private void countryBackgroundImage() {
        int userId = SessionManager.getLoggedInUserId();
        String countryName = explorationDAO.getCurrentExploring(userId);
        String backgroundImagePath = getClass().getResource("/com/example/pathpilotfx/assets/" + countryName + "-BG.jpg").toExternalForm();
        rootAnchorPane.setStyle("-fx-background-image: url('" + backgroundImagePath + "'); -fx-background-size: cover;");
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
        onStopButtonClick();
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

    /**
     * Sets the timer after settings are changed.
     *
     * @param timer the timer object to set.
     */
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
            if (sessionTimer.getSeconds() == 0)
            {
                // notify the user that the session has finished
                sessionFinishNotify();

                // get work duration
                int userID = SessionManager.getLoggedInUserId();
                int sessionExp = timer.getWorkDurationByUser(userID);

                // increment with user table for respective user
                userDAO.updateExp(userID, sessionExp);

                while (true) {
                    int nextCountryID = explorationDAO.getFirstLockedCountry(userID);
                    int nextCountryExp = countryDAO.getRequiredExpByCountryId(nextCountryID);
                    int userExp = userDAO.getExpByUserID(userID);

                    if (userExp >= nextCountryExp) {
                        explorationDAO.unlockCountry(userID, nextCountryID);
                    }
                    else {
                        break;
                    }
                }
                user = userDAO.getByUserId(userID);
                destination = explorationDAO.getNextDestination(userID);
                expNeeded = Integer.parseInt(destination.get(1)) - user.getExp();


                currentLocation.setText(explorationDAO.getCurrentExploring(userID));
                currentExp.setText(String.valueOf(user.getExp()));
                nextLocation.setText(destination.get(0));
                needExp.setText(String.valueOf(expNeeded));
            }
            timerTimeline.stop();
            // If a task is associated with the timer, shows popup
            if (task != null){showPopup();}
            // toggle timer type and reset timer
            else{handleRestTimer();}
        }
    }

    /**
     * Notifies the user that the session has finished.
     */
    private void sessionFinishNotify() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Session Finished");
            alert.setHeaderText("Session Finished");
            alert.setContentText("Your session has finished. Take a break! \uD83D\uDE42");
            alert.showAndWait();
        });
//        currentLocation.setText(explorationDAO.getCurrentExploring(userID));
//        currentExp.setText(String.valueOf(user.getExp()));
//        nextLocation.setText(destination.get(0));
//        needExp.setText(String.valueOf(expNeeded));

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