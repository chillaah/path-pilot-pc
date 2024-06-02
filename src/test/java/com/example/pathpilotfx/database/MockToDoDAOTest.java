package com.example.pathpilotfx.database;

import static org.junit.jupiter.api.Assertions.*;

import com.example.pathpilotfx.model.MockToDoDAO;
import com.example.pathpilotfx.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Nested
class MockToDoDAOTest {

    private MockToDoDAO mockToDoDAO;

    @BeforeEach
    void setUp() {
        mockToDoDAO = new MockToDoDAO();
    }

    @Test
    void testAddTask() {
        // Test adding a task
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);
        List<Task> allTasks = mockToDoDAO.getAllTasks();
        assertEquals(1, allTasks.size());
        assertTrue(allTasks.contains(task));
    }

    @Test
    void testUpdateTaskName() {
        // Test updating task name
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);

        Task updatedTask = new Task("Updated Task", task.getDescription(), task.getPriority(), task.getDueDate().toLocalDate());
        updatedTask.setId(task.getID());
        mockToDoDAO.updateTask(updatedTask);

        Task retrievedTask = mockToDoDAO.getTaskById(task.getID());
        assertEquals("Updated Task", retrievedTask.getTask());
    }

    @Test
    void testUpdateTaskDescription() {
        // Test updating task description
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);

        Task updatedTask = new Task(task.getTask(), "Updated Description", task.getPriority(), task.getDueDate().toLocalDate());
        updatedTask.setId(task.getID());
        mockToDoDAO.updateTask(updatedTask);

        Task retrievedTask = mockToDoDAO.getTaskById(task.getID());
        assertEquals("Updated Description", retrievedTask.getDescription());
    }

    @Test
    void testUpdateTaskPriority() {
        // Test updating task priority
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);

        Task updatedTask = new Task(task.getTask(), task.getDescription(), "LOW", task.getDueDate().toLocalDate());
        updatedTask.setId(task.getID());
        mockToDoDAO.updateTask(updatedTask);

        Task retrievedTask = mockToDoDAO.getTaskById(task.getID());
        assertEquals("LOW", retrievedTask.getPriority());
    }

    @Test
    void testUpdateTaskDueDate() {
        // Test updating task due date
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);

        LocalDate newDueDate = LocalDate.now().plusDays(1);
        Task updatedTask = new Task(task.getTask(), task.getDescription(), task.getPriority(), newDueDate);
        updatedTask.setId(task.getID());
        mockToDoDAO.updateTask(updatedTask);

        Task retrievedTask = mockToDoDAO.getTaskById(task.getID());
        assertEquals(Date.valueOf(newDueDate), retrievedTask.getDueDate());
    }

    @Test
    void testDeleteTask() {
        // Test deleting a task
        Task task1 = new Task("Task 1", "Description", "HIGH", LocalDate.now());
        Task task2 = new Task("Task 2", "Description", "MEDIUM", LocalDate.now());

        mockToDoDAO.addTask(task1);
        mockToDoDAO.addTask(task2);

        mockToDoDAO.deleteTask(task1.getID());

        assertNull(mockToDoDAO.getTaskById(task1.getID()));
        assertNotNull(mockToDoDAO.getTaskById(task2.getID()));
    }

    @Test
    void testGetAllTasks() {
        // Test retrieving all tasks
        Task task1 = new Task("Task 1", "Description", "HIGH", LocalDate.now());
        Task task2 = new Task("Task 2", "Description", "MEDIUM", LocalDate.now());

        mockToDoDAO.addTask(task1);
        mockToDoDAO.addTask(task2);

        List<Task> allTasks = mockToDoDAO.getAllTasks();
        assertEquals(2, allTasks.size());
        assertTrue(allTasks.contains(task1));
        assertTrue(allTasks.contains(task2));
    }

    @Test
    void testGetTaskById() {
        // Test retrieving a task by ID
        Task task = new Task("Test Task", "Description", "HIGH", LocalDate.now());
        mockToDoDAO.addTask(task);

        Task retrievedTask = mockToDoDAO.getTaskById(task.getID());
        assertNotNull(retrievedTask);
        assertEquals(task.getTask(), retrievedTask.getTask());
    }
}

