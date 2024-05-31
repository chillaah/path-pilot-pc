package com.example.pathpilotfx.controller.navigation;

import com.example.pathpilotfx.controller.timer.TimerController;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


/**
 * Controller class for the sidebar navigation.
 */
public class SideBarController implements Initializable {

    @FXML
    private BorderPane bp;

    private TimerController timerController;

    /**
     * Initializes the sidebar navigation.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPage("timer-view.fxml"); //Default page when application is loaded
    }

    /**
     * Loads the home page when the home icon is clicked.
     */
    @FXML
    public void home() {
        loadPage("timer-view.fxml");
    }

    /**
     * Loads the to-do list page when the to-do icon is clicked.
     * If there are no tasks, loads the page for adding tasks; otherwise, loads the page for viewing existing tasks.
     */
    @FXML
    public void todo() {
        // establish a connection with the database
        ToDoDAO toDoDAO = new ToDoDAO();
        List<Task> taskList = toDoDAO.getAll();

        if (taskList.isEmpty()) {
            loadPage("todo(addItem).fxml");   // empty page asking users to add tasks
        } else {
            loadPage("todo(taskPage).fxml"); // to show existing tasks
        }
    }

    /**
     * Loads the map page when the map icon is clicked.
     */
    @FXML
    public void map() {
        loadPage("map-view.fxml");
    }

    /**
     * Loads the profile page when the profile icon is clicked.
     */
    @FXML
    public void profile() {
        loadPage("profile.fxml");
    }

    /**
     * Loads the specified FXML page into the center of the border pane.
     *
     * @param page The name of the FXML page to load.
     */
    public void loadPage(String page) {
        Parent root;

        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/" + page)));
            root = loader.load();
            // Check if the page being loaded is the timer page
            if (page.equals("timer-view.fxml")) {
                timerController = loader.getController(); // Get the controller instance
            } else if (timerController != null) {
                // Stop the timer if it's running
                timerController.onStopButtonClick();
                timerController = null; // Reset the reference
            }
        } catch (IOException e) {
            System.out.println("root has not been found");
            throw new RuntimeException(e);
        }
        // Set the root in the center of the border pane
        bp.setCenter(root);
        BorderPane.setAlignment(root, Pos.CENTER);
    }
}

