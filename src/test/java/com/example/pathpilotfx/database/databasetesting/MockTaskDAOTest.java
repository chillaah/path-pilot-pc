package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.TaskV;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockTaskDAOTest {

    private MockTaskDAO taskDAO;

    @BeforeEach
    public void setUp() {
        taskDAO = new MockTaskDAO();
    }

    @Test
    public void testInsertTask_Success() {
        TaskV task = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        taskDAO.insert(task);
        assertNotNull(taskDAO.getByTaskId(task.getTaskID()));
    }

    @Test
    public void testUpdateTask_Success() {
        TaskV task = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        taskDAO.insert(task);

        task.setTaskName("Updated Task Name");
        taskDAO.update(task);

        assertEquals("Updated Task Name", taskDAO.getByTaskId(task.getTaskID()).getTaskName());
    }

    @Test
    public void testDeleteTask_Success() {
        TaskV task = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        taskDAO.insert(task);

        taskDAO.deleteTask(task.getTaskID());

        assertNull(taskDAO.getByTaskId(task.getTaskID()));
    }

    @Test
    public void testGetAllTasks_NotEmpty() {
        TaskV task1 = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        TaskV task2 = new TaskV(1,2,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                false, 2, "In Progress","Study",
                "Medium", "extra_notes"  );
        taskDAO.insert(task1);
        taskDAO.insert(task2);

        List<TaskV> tasks = taskDAO.getAll();
        assertFalse(tasks.isEmpty());
        assertEquals(2, tasks.size());
    }

    @Test
    public void testGetByTaskId_ExistingTask() {
        TaskV task = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        taskDAO.insert(task);

        TaskV retrievedTask = taskDAO.getByTaskId(task.getTaskID());

        assertEquals(task, retrievedTask);
    }

    @Test
    public void testGetByTaskId_NonExistingTask() {
        assertNull(taskDAO.getByTaskId(4));
    }


    @Test
    public void testInsertAndUpdateTask_CheckAllFields() {
        TaskV task = new TaskV(1,1,"taskname","description",
                Date.valueOf("2024-05-01"),Date.valueOf("2024-06-01"),
                true, 5, "Complete","Work",
                "Medium", "extra_notes"  );
        taskDAO.insert(task);

        TaskV retrievedTask = taskDAO.getByTaskId(task.getTaskID());
        retrievedTask.setTaskName("Updated Task Name");
        taskDAO.update(retrievedTask);

        TaskV updatedTask = taskDAO.getByTaskId(task.getTaskID());
        assertEquals("Updated Task Name", updatedTask.getTaskName());
    }

    @Test
    public void testDeleteTask_NotExistingTask() {
        assertDoesNotThrow(() -> taskDAO.deleteTask(4));
    }
}
