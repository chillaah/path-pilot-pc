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
    private AnchorPane rootAnchorPane;

    @FXML
    private Label taskLabel;

    private Task task;
    public boolean timer_finish = false;

    @FXML
    void completeTaskAction(ActionEvent event) {
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

    @FXML
    void notcompleteTaskAction(ActionEvent event) {
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

    @FXML
    void initialize(){
        if (task != null) {
            taskLabel.setText("Task: " + task.getTask());
        } else {
            taskLabel.setText("Task: N/A");
        }
    }

    public void setTaskComplete(Task task) {
        this.task = task;
    }

}