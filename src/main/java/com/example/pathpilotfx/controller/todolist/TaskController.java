package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.util.ResourceBundle;


/**
 * Controller class for managing individual tasks in the to-do list.
 */
public class TaskController {

    @FXML
    private CheckBox checkboxField;

    private TaskChangeListener listener;

    @FXML
    private Label taskNameField;

    @FXML
    private Label taskPrioField;

    @FXML
    private Label taskDueDate;

    private Task task;


    /**
     * Initializes the task controller.
     */
    @FXML
    void initialize() {
        // Set up an event handler for the checkbox to handle status change
        checkboxField.setOnAction(event -> {
            handleStatusChange();

            System.out.println("Checkbox action triggered: " + checkboxField.isSelected());
            boolean isSelected = checkboxField.isSelected();

        });
    }

    /**
     * Handles the change in status of the task when the checkbox is clicked.
     */
    @FXML
    void handleStatusChange() {
        // changes the value of the checkbox when clicked
        boolean newStatus = checkboxField.isSelected();
        task.setStatus(newStatus);
        //updates Db about task status
        ToDoDAO toDoDAO = new ToDoDAO();
        toDoDAO.update(task);

        notifyTaskChangeListener(task.getStatus());
    }

    /**
     * Sets the task associated with this controller.
     * @param task The task to set.
     */
    public void setTask(Task task) {
        this.task = task;
        taskNameField.setText(task.getTask());

        checkboxField.setSelected(task.getStatus());


        if(task.getPriority() != null && task.getDueDate() == null ){
            taskPrioField.setText(task.getPriority());
            taskDueDate.setVisible(false);
        }
        else if(task.getPriority() != null && task.getDueDate() != null ) {
            taskPrioField.setText(task.getPriority());
            taskDueDate.setText((task.getDueDate()).toString());
        }
        else if(task.getPriority() == null && task.getDueDate() != null){
            taskPrioField.setText((task.getDueDate()).toString());
            taskDueDate.setVisible(false);
        }
        else {
            taskPrioField.setVisible(false);
            taskDueDate.setVisible(false);
        }
    }

    /**
     * Sets the task change listener.
     * @param listener The task change listener.
     */
    public void setTaskChangeListener(TaskChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Notifies the task change listener about the change in task status.
     * If the listener is not null, invokes the onTaskChange method of the listener interface.
     * @param isSelected The new status of the task.
     */
    private void notifyTaskChangeListener(boolean isSelected) {
        if (listener != null) {
            listener.onTaskChange(isSelected);
        }
    }
}
