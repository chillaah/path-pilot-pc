package com.example.pathpilotfx.controller.profile;

import com.example.pathpilotfx.MainApplication;
import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.*;
import com.example.pathpilotfx.model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 Class that Controls the profile/dashboard view
 **/
public class  ProfileController {
    @FXML
    private Label profileLabel;
    @FXML
    private Label email;
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
    private Label longestTimer;
    @FXML
    private Label avgTimer;
    @FXML
    private Label numExploredCountries;
    @FXML
    private Label busiestMonth;
    @FXML
    private PieChart timer;
    @FXML
    private PieChart priorities;
    @FXML
    private Button editButton;
    @FXML
    private Button logoutButton;
    private ExplorationDAO explorationDAO;
    private SessionDAO sessionDAO;
    private ToDoDAO toDoDAO;
    private UserDAO userDao;
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
//        longestTimer.setText("placeholder");
//        avgTimer.setText("placeholder");
//        numExploredCountries.setText(String.valueOf(explorationDAO.countExplored(userID)));
//        busiestMonth.setText("Most busy month: " + getMostCommonMonthByUserId(userID));
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

    /**
     * Handles the event when the Edit button is clicked.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void onEditButtonClick() throws IOException {

    }

    @FXML
    void logoutButton(ActionEvent event) {
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
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the most common month by user ID.
     *
     * @param userId The user ID.
     * @return The most common month.
     */
    public String getMostCommonMonthByUserId(int userId) {
        try {
            List<Date> dueDates = toDoDAO.getDueDatesByUserId(userId);
            System.out.println("duedates:" + dueDates);
            if (dueDates != null) {
                Map<Integer, Integer> monthCounts = new HashMap<>();
                int maxCount = 0;
                int mostCommonMonth = 0;

                // Count occurrences of each month. getordefault is for the null dates
                for (Date dueDate : dueDates) {
                    LocalDate localDueDate = dueDate.toLocalDate();
                    int month = localDueDate.getMonthValue();
                    monthCounts.put(month, monthCounts.getOrDefault(month, 0) + 1);
                }

                // Find the month with the highest occurrence count
                for (int month : monthCounts.keySet()) {
                    int count = monthCounts.get(month);
                    if (count > maxCount) {
                        mostCommonMonth = month;
                        maxCount = count;
                    }
                }

                // Return the most common month using unnecessary values for the year and day
                return LocalDate.of(1, mostCommonMonth, 1).getMonth().toString();
            }
            return "no task due dates";
        }
        catch(Exception exception){System.out.println("no due dates");};
        return "none";
    }
}
