// ToDoTaskController.java
package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.*;


@SuppressWarnings("javaFxVersionMismatch")
public class ToDoTaskController implements TaskChangeListener {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private VBox vBoxContainer;

    private TaskController child;
    private List<Task> taskList;


    /**
     * Initializes the to-do task controller.
     * Fetches existing tasks from the database and displays them.
     * If no tasks exist, loads the add item form.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void initialize() throws IOException {

        //initialize DB connection
        ToDoDAO toDoDAO = new ToDoDAO();
        // fetch all existing tasks from DB
        taskList = toDoDAO.getUncomplete(SessionManager.getLoggedInUserId());

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
     * Adds a task to the task page.
     *
     * @param task The task to add.
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
     * Edits a task.
     * Loads addItemForm.fxml and preloads fields with task details.
     * @param task The task to edit.
     * @throws IOException If an I/O error occurs.
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
     * Handles the action when the "Add Task" button is clicked.
     * @param event The action event.
     * @throws IOException If an I/O error occurs.
     */
    @FXML
    void addTaskAction(ActionEvent event) throws IOException {
        // Load addItemForm.fxml and preload fields with task details
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(addItemForm).fxml"));
        AnchorPane addItemFormRoot = loader.load();
        rootAnchorPane.getChildren().setAll(addItemFormRoot);
    }

    /**
     * Method to load the completed (past) tasks
     *
     * @throws IOException If an IO exception occurs.
     */
    @FXML
    void ViewPast() throws IOException {
        // Load todo(pastTask).fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(pastTask).fxml"));
        AnchorPane addItemFormRoot = loader.load();
        rootAnchorPane.getChildren().setAll(addItemFormRoot);
    }

    /**
     * Method to reload updated task page.
     * @param isSelected boolean to represent if tick box is selected
     */
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
