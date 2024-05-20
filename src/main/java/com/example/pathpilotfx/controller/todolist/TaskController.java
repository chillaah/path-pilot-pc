package com.example.pathpilotfx.controller.todolist;

import com.example.pathpilotfx.database.ToDoDAO;
import com.example.pathpilotfx.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.example.pathpilotfx.controller.todolist.TaskChangeListener;
import javafx.scene.paint.Color;

import java.io.Console;
import java.lang.runtime.SwitchBootstraps;
import java.net.URL;
import java.util.ResourceBundle;


public class TaskController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkboxField;

    private TaskChangeListener listener;

    @FXML
    private Label taskNameField;

    @FXML
    private Label taskPrioField;

    @FXML
    private Label taskDueDate;

    @FXML
    private Pane taskPane;

    private Task task;

    private ToDoTaskController parent;
    private boolean isCompleted;

    @FXML
    void initialize() {
        // Set up an event handler for the checkbox to handle status change
        checkboxField.setOnAction(event -> {
            handleStatusChange();

            System.out.println("Checkbox action triggered: " + checkboxField.isSelected());
            boolean isSelected = checkboxField.isSelected();

        });
    }
    @FXML
    void handleStatusChange() {
        // changes the value of the checkbox when clicked
        boolean newStatus = checkboxField.isSelected();
        task.setStatus(newStatus);
        //updates Db about task status
        ToDoDAO toDoDAO = new ToDoDAO();
        toDoDAO.update(task);
        //toDoDOA.close();

        notifyTaskChangeListener(task.getStatus());
    }

    public void setTask(Task task) {
        this.task = task;
        taskNameField.setText(task.getTask());

        checkboxField.setSelected(task.getStatus());


        if(task.getPriority() != null && task.getDueDate() == null ){
            setPrio();
            taskDueDate.setVisible(false);
        }
        else if(task.getPriority() != null && task.getDueDate() != null ) {
            setPrio();
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

    public void setTaskChangeListener(TaskChangeListener listener) {
        this.listener = listener;
    }

    private void notifyTaskChangeListener(boolean isSelected) {
        if (listener != null) {
            listener.onTaskChange(isSelected);
        }
    }

    private void setPrio(){
        taskPrioField.setText((task.getPriority()));
        switch(task.getPriority()){
            case "HIGH":
                taskPrioField.setTextFill(Color.color(1, 0, 0));
                break;
            case "MEDIUM":
                taskPrioField.setTextFill(Color.color(1, 1, 0));
                break;
            case  "LOW":
                taskPrioField.setTextFill(Color.color(0, 1, 0));
                break;
            default:
                taskPrioField.setTextFill(Color.color(1, 0, 0));
        }
    }

//    //public Task getTask(){
//        return this.task;
//    }
}
