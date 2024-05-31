package com.example.pathpilotfx.controller.profile;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.*;
import com.example.pathpilotfx.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

/**
 Class that Controls the profile/dashboard view
 **/
public class  ProfileController {
    @FXML
    private Label profileLabel;
    @FXML
    private Label exp;
    @FXML
    private Label creationDate;
    @FXML
    private Label tasksCompleted;
    @FXML
    private Label tasksUnCompleted;
    @FXML
    private Label focusSessions;
    @FXML
    private Label focusMinutes;
    @FXML
    private Label locationsLocked;
    @FXML
    private Label locationsUnlocked;
    @FXML
    private PieChart priorities;
    @FXML
    private Button logoutButton;
    private final ExplorationDAO explorationDAO;
    private final SessionDAO sessionDAO;
    private final ToDoDAO toDoDAO;
    private final UserDAO userDao;
    int userID = SessionManager.getLoggedInUserId();

    /**
     * Constructor for ProfileController.
     */
    public ProfileController() {
        this.explorationDAO = new ExplorationDAO();
        this.toDoDAO = new ToDoDAO();
        this.userDao = new UserDAO();
        this.sessionDAO = new SessionDAO();
    }

    /**
     Method to add values to all visible data and dashboard
     **/
    public void initialize() {
        User user = userDao.getByUserId(userID);
        profileLabel.setText(user.getEmail().substring(0, user.getEmail().indexOf('@')));
        exp.setText("EXP: " + user.getExp());
        creationDate.setText("Created: " + user.getCreationDate());
        tasksCompleted.setText(String.valueOf(toDoDAO.getCompletedTaskCount(userID)));
        tasksUnCompleted.setText(String.valueOf(toDoDAO.getUncompletedTaskCount(userID)));
        focusSessions.setText(String.valueOf(sessionDAO.getTotalFocusSessions(userID)));
        focusMinutes.setText(String.format("%d:00",sessionDAO.getTotalFocusMinutes(userID)));
        locationsLocked.setText(String.valueOf(explorationDAO.countLocked(userID)));
        locationsUnlocked.setText(String.valueOf(explorationDAO.countUnlocked(userID)));
        initializePrioritiesData();
    }

    /**
     Method that initialises the priorities pie chart
     **/
    private void initializePrioritiesData() {
        if(!toDoDAO.getAll().isEmpty()) {
            System.out.println(toDoDAO.getPriorityCountsByUserId(SessionManager.getLoggedInUserId()));
            ObservableList<PieChart.Data> prioritiesData = toDoDAO.getPriorityCountsByUserId(userID);
            priorities.setData(prioritiesData);
            priorities.setLabelLineLength(10);
        }
    }

    @FXML
    void logoutButton() {
        SessionManager.setLoggedInUserId(0);
        // Get the current stage
        Stage currentStage = (Stage) logoutButton.getScene().getWindow();

        // Close the current stage
        currentStage.close();

        // Start a new instance of the MainApplication
        MainApplication mainApp = new MainApplication();
        try {
            Stage newStage = new Stage();
            mainApp.start(newStage);
        } catch (IOException e) {
            System.out.println("Error starting the application");
            throw new RuntimeException(e);
        }
    }

}
