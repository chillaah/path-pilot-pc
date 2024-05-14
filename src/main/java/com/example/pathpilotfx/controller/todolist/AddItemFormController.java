// AddItemFormController.java
package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.controller.authentication.SessionManager;
import com.example.pathpilotfx.controller.timer.TimerController;
import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the form used to add or edit tasks in the to-do list.
 */
public class AddItemFormController {
    @FXML
    private ImageView cancelTaskButton;

    @FXML
    private JFXButton startTimerButton;

    @FXML
    private DatePicker dateButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private ChoiceBox<String> priorityOptions;

    @FXML
    private JFXButton savedTaskButton;

    @FXML
    private TextField taskField;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private JFXButton deleteTaskButton;

    private Task task;
    private boolean editMode = false;
    private Task editedTask;

    /**
     * Initializes the controller.
     * @throws SQLException If an SQL exception occurs.
     */
    @FXML
    void initialize() throws SQLException {
        // delete button hidden when adding a task
        if(!editMode){
            deleteTaskButton.setVisible(false);
            startTimerButton.setVisible(false);
        }

        // Initialize ToDoDOA
        ToDoDAO toDoDAO = new ToDoDAO();
        toDoDAO.createTaskTable();

        // Add priority options
        priorityOptions.getItems().addAll("HIGH", "MEDIUM", "LOW");

        // Disable past dates in the DatePicker
        dateButton.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Disable past dates
                if (date != null && date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;"); // Optional: Change style for disabled dates
                }
            }
        });

        // Cancel button event
        cancelTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

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
        });

        // Save button event
        savedTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{

            if(editMode){
                // Editing an existing task
                editedTask.setTask(taskField.getText());
                editedTask.setDescription(descriptionField.getText());
                editedTask.setPriority(priorityOptions.getValue());
                editedTask.setDueDate(dateButton.getValue());
                toDoDAO.update(editedTask); // Update the task in the database
            }else{
                // insert new task into DB
                Task newTask = new Task(taskField.getText(), SessionManager.getLoggedInUserId(), descriptionField.getText(), priorityOptions.getValue(), dateButton.getValue());
                toDoDAO.insert(newTask);
            }

            try {
                //Load the task page- todo(taskPage).fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(taskPage).fxml"));
                AnchorPane todoTaskPage = loader.load();

                rootAnchorPane.getChildren().setAll(todoTaskPage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });



    }


    @FXML
    void deleteTaskAction() {

        // Initialize ToDoDOA
        ToDoDAO toDoDAO = new ToDoDAO();
        // Delete Task in DB
        toDoDAO.delete(task.getID());

        // reload updated task page
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/todo(taskPage).fxml"));
            AnchorPane todoTaskPage = loader.load();
            rootAnchorPane.getChildren().setAll(todoTaskPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Preloads the fields in the todo(addItemForm).fxml with the selected task to edit.
     * @param task The task to edit.
     */
    public void preloadFields(Task task){

        this.task = task;
        taskField.setText(task.getTask());
        descriptionField.setText(task.getDescription());
        priorityOptions.setValue(task.getPriority());
        dateButton.setValue(task.getDueDate()!= null ? task.getDueDate().toLocalDate() : null);


        startTimerButton.setVisible(!task.getStatus());


    }

    /**
     * Sets todo(addItemForm) to edit mode when a task is being edited.
     * @param editMode True if in edit mode, false otherwise.
     * @param editedTask The task being edited.
     */
    public void setEditMode(boolean editMode, Task editedTask) {
        this.editMode = editMode;
        this.editedTask = editedTask;
        deleteTaskButton.setVisible(true);

    }

    @FXML
    void startTimerAction(){
        System.out.println("button clicked start timer");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pathpilotfx/timer-view.fxml"));
            AnchorPane timerPage = loader.load();

             //Get the controller instance
            TimerController timerController = loader.getController();
            timerController.setTaskMode(true,this.task);
            rootAnchorPane.getChildren().setAll(timerPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}