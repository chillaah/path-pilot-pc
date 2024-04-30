package com.example.pathpilotfx.model;

import java.nio.file.FileAlreadyExistsException;

public class CompletedTask {

    private int count = 0;

    private Task[] TaskList;

    public CompletedTask() {
        TaskList = new Task[0];
    }

    public void AddCompelete(Task task) {

        task.setStatus(true);
        TaskList = new Task[TaskList.length + 1];
        TaskList[TaskList.length - 1] = task;

    }

    public void RemoveComplete(Task task){
        task.setStatus(false);
        TaskList = new Task[TaskList.length -1];
    }
}
