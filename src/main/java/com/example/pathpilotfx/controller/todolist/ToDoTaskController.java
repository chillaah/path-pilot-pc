// ToDoTaskController.java
package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.control.Button;
//import jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

@SuppressWarnings("javaFxVersionMismatch")
public class ToDoTaskController implements TaskChangeListener {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBoxContainer;

    @FXML
    private ScrollPane scrollPane2;

    @FXML
    private VBox CompletedTask;

    @FXML
//    private JFXButton addTaskButton;
    private Button addTaskButton;

    private TaskController child;
    private List<Task> taskList;
    private List<Task> CompletedList = new ArrayList<>();

    @FXML
    void initialize() throws IOException {

        //intialise DB connection
        ToDoDAO toDoDAO = new ToDoDAO();
        // fetch all existing tasks from DB
        int userID = SessionManager.getLoggedInUserId();
        taskList = toDoDAO.getUncomplete(userID);

        if (taskList.isEmpty()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(addItem).fxml"));
            AnchorPane addItemFormRoot = loader.load();
            rootAnchorPane.getChildren().setAll(addItemFormRoot);
        }
        else{ // add each task to the task page
            for (Task task : taskList) {
                addTask(task);
            }
        }

        //toDoDOA.close();

    }

    private void addTask(Task task) {
        // create a task using todo(task).fxml
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(task).fxml"));
            Pane taskPane = loader.load();
            // when pane is clicked - it triggers edit action
            taskPane.setOnMouseClicked(event -> {
                try {
                    editTask(task);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            //add task to the vBox container in taskPage
            vBoxContainer.getChildren().add(taskPane);
            TaskController controller = loader.getController();
            controller.setTask(task);
            // Set bottom margin for the taskPane for space between each task
            VBox.setMargin(taskPane, new Insets(0, 0, 5, 0)); // 5px bottom margin

            child = controller;
            child.setTaskChangeListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void editTask(Task task) throws IOException {
        // Load addItemForm.fxml and preload fields with task details
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(addItemForm).fxml"));
        AnchorPane addItemFormRoot = loader.load();
        rootAnchorPane.getChildren().setAll(addItemFormRoot);

        AddItemFormController addItemFormController = loader.getController();
        // Preload fields with task details
        addItemFormController.preloadFields(task);
        addItemFormController.setEditMode(true,task);

    }


    @FXML
    void addTaskAction(ActionEvent event) throws IOException {
        // Load addItemForm.fxml and preload fields with task details
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(addItemForm).fxml"));
        AnchorPane addItemFormRoot = loader.load();
        rootAnchorPane.getChildren().setAll(addItemFormRoot);
    }


    @Override
    public void onTaskChange(boolean isSelected) {
        // reload updated task page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(taskPage).fxml"));
            AnchorPane todoTaskPage = loader.load();
            rootAnchorPane.getChildren().setAll(todoTaskPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
