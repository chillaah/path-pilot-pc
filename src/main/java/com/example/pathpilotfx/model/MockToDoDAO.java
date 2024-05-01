package com.example.pathpilotfx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockToDoDAO {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private int idCounter = 1;

    // New method to add a task
    public void addTask(Task task) {
        task.setId(idCounter++);
        taskMap.put(task.getID(), task);
    }

    // New method to update a task
    public void updateTask(Task task) {
        taskMap.put(task.getID(), task);
    }

    // New method to delete a task
    public void deleteTask(int id) {
        taskMap.remove(id);
    }

    // New method to get all tasks
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    // New method to get a task by id
    public Task getTaskById(int id) {
        return taskMap.get(id);
    }
}
