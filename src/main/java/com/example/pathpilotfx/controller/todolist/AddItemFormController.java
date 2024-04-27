// AddItemFormController.java
package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

@SuppressWarnings("javaFxVersionMismatch")
public class AddItemFormController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private JFXButton cancelTaskButton;

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
    @FXML
    void initialize() throws SQLException {

        // delete button hidden when adding a task
        if(!editMode){
            deleteTaskButton.setVisible(false);
        }

        // Initialize ToDoDOA
        ToDoDAO toDoDAO = new ToDoDAO();
        toDoDAO.createTaskTable();

        // Add priority options
        priorityOptions.getItems().addAll("HIGH", "MEDIUM", "LOW");

        // Cancel button event
        cancelTaskButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Cancel Clicked!");
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
            System.out.println("Save Pressed!");

            if(editMode){
                // Editing an existing task
                editedTask.setTask(taskField.getText());
                editedTask.setDescription(descriptionField.getText());
                editedTask.setPriority(priorityOptions.getValue());
                editedTask.setDueDate(dateButton.getValue());
                toDoDAO.update(editedTask); // Update the task in the database
            }else{
                // insert new task into DB
                Task newTask = new Task(taskField.getText(), descriptionField.getText(), priorityOptions.getValue(), dateButton.getValue());
                toDoDAO.insertTask(newTask);

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
    void deleteTaskAction(ActionEvent event) {

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

    // Preloads the fields in the todo(addItemForm).fxml with the selected task to edit
    public void preloadFields(Task task){

        this.task = task;
        taskField.setText(task.getTask());
        descriptionField.setText(task.getDescription());
        priorityOptions.setValue(task.getPriority());
        dateButton.setValue(task.getDueDate()!= null ? task.getDueDate().toLocalDate() : null);
    }

    // sets todo(addItemForm) to edit mode when task is being edited
    public void setEditMode(boolean editMode, Task editedTask) {
        this.editMode = editMode;
        this.editedTask = editedTask;
        deleteTaskButton.setVisible(true);

    }
}