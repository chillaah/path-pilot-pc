package com.example.pathpilotfx.controller.timer;

import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TaskTimerController {

    @FXML
    private JFXButton completeTaskButton;

    @FXML
    private JFXButton notcompleteTaskButton;

    @FXML
    private AnchorPane rootAnchorPane; // the beige pane within which all views are to be loaded

    @FXML
    private Label taskLabel;

    private Task task;
    public boolean timer_finish = false; // flag to determine if this screen has been loaded because timer has finished

    @FXML
    void initialize(){
        // setting the task label if a task is available
        if (task != null) {
            taskLabel.setText("Task: " + task.getTask());
        } else {
            taskLabel.setText("Task: N/A");
        }
    }
    /**
     * Action handler for completing the task.
     * Updates the task status in the database and navigates to the task page.
     */
    @FXML
    void completeTaskAction() {
        task.setStatus(true);
        //updates Db about task status
        ToDoDAO toDoDAO = new ToDoDAO();
        toDoDAO.update(task);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(taskPage).fxml"));
            AnchorPane todoTaskPage = loader.load();
            rootAnchorPane.getChildren().setAll(todoTaskPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Action handler for not completing the task.
     * Navigates back to the timer view or starts the rest timer, depending on the timer finish flag..
     */
    @FXML
    void notcompleteTaskAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml"));
            Parent timerPage = loader.load();
            TimerController timerController = loader.getController();
            if(timer_finish){
                timerController.handleRestTimer(); // Start the rest timer
            }
            AnchorPane wrappedContent = new AnchorPane(timerPage);
            rootAnchorPane.getChildren().setAll(wrappedContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sets the task for Task Timer screen.
     *
     * @param task The task for which the screen is being loaded.
     */
    public void setTaskTimer(Task task) {
        this.task = task;
    }

}