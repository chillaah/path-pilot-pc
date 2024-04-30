package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.TaskV;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTaskDAO {
    private Map<Integer, TaskV> tasks = new HashMap<>();
    private int nextTaskId = 1;

    public void insert(TaskV taskV) {
        int taskId = nextTaskId++;
        taskV.setTaskID(taskId);
        tasks.put(taskId, taskV);
    }

    public void update(TaskV taskV) {
        tasks.put(taskV.getTaskID(), taskV);
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public List<TaskV> getAll() {
        return new ArrayList<>(tasks.values());
    }

    public TaskV getByTaskId(int id) {
        return tasks.get(id);
    }

}
