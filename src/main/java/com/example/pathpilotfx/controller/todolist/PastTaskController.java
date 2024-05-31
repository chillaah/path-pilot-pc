// ToDoTaskController.java
package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;


@SuppressWarnings("javaFxVersionMismatch")
public class PastTaskController implements TaskChangeListener{

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vBoxContainer;

    @FXML
    private VBox CompletedTask;

    @FXML
    private Button BackButton;

    private TaskController child;
    private List<Task> taskList;
    private List<Task> CompletedList = new ArrayList<>();

    /**
     * Initializes the controller.
     * @throws IOException If an IO exception occurs.
     */
    @FXML
    void initialize() throws IOException {

        //intialise DB connection
        ToDoDAO toDoDAO = new ToDoDAO();
        // fetch all existing tasks from DB
        taskList = toDoDAO.getComplete(SessionManager.getLoggedInUserId());

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
    }

    /**
     * Method to create a new task
     *
     * @param task Task to be created.
     */
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

    /**
     * Method to edit a task
     *
     * @param task Task to be edited
     * @throws IOException If an IO exception occurs.
     */
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

    /**
     * Method to load the main task page
     *
     * @param event mouse click on back button
     * @throws IOException if IO Exception occurs
     */
    @FXML
    void BackToMain(ActionEvent event) throws IOException {

        ToDoDAO toDoDAO = new ToDoDAO();

        try {
            List<Task> taskList = toDoDAO.getAll();
            AnchorPane formPane;
            // loads appropriate page depending if tasks exist
            if(taskList.isEmpty()){
                formPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/todo(addItem).fxml")));
            }
            else{
                formPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/pathpilotfx/todo(taskPage).fxml")));
            }
            rootAnchorPane.getChildren().setAll(formPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to reload task page is a tick box is clicked.
     * @param isSelected status of tick box.
     */
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
