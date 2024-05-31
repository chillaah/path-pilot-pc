package com.example.pathpilotfx.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MockToDoDAO {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private int idCounter = 1;

    /**
     * Method to add a new task
     * @param task Task object to be added to the mock DAO.
     */
    public void addTask(Task task) {
        task.setId(idCounter++);
        taskMap.put(task.getID(), task);
    }

    /**
     * Method to update a task
     * @param task Task to be updated in the DAO.
     */
    public void updateTask(Task task) {
        taskMap.put(task.getID(), task);
    }

    /**
     * Method to delete a task
     * @param id ID of the task to be deleted from the DAO.
     */
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    /**
     * Method to get all tasks from the DAO.
     * @return A list of tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    /**
     * Method to get a task by ID
     * @param id ID of the task being searched for.
     * @return Task object with matching ID
     */
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }
}
