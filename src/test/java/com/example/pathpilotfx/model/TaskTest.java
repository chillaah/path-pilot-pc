package com.example.pathpilotfx.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
//
//    @Test
//    void testTaskConstructorWithDueDate() {
//        LocalDate dueDate = LocalDate.of(2024, 4, 30);
//        Task task = new Task("Test Task", "Test Description", "HIGH", dueDate);
//        assertEquals("Test Task", task.getTask());
//        assertEquals("Test Description", task.getDescription());
//        assertEquals("HIGH", task.getPriority());
//        assertFalse(task.getStatus());
//        assertEquals(java.sql.Date.valueOf(LocalDate.now()), task.getDatecreated());
//        assertEquals(java.sql.Date.valueOf(dueDate), task.getDueDate());
//    }
//
//    @Test
//    void testTaskConstructorWithoutDueDate() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        assertNull(task.getDueDate());
//    }
//
//    @Test
//    void testSetAndGetID() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setId(1);
//        assertEquals(1, task.getID());
//    }
//
//    @Test
//    void testSetAndGetTask() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setTask("New Task");
//        assertEquals("New Task", task.getTask());
//    }
//
//    @Test
//    void testSetAndGetStatus() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setStatus(true);
//        assertTrue(task.getStatus());
//    }
//
//    @Test
//    void testSetAndGetDescription() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setDescription("New Description");
//        assertEquals("New Description", task.getDescription());
//    }
//
//    @Test
//    void testSetAndGetPriority() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setPriority("LOW");
//        assertEquals("LOW", task.getPriority());
//    }
//
//    @Test
//    void testSetAndGetDateCreated() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        Date date = new Date();
//        task.setDatecreated(date);
//        assertEquals(new java.sql.Date(date.getTime()), task.getDatecreated());
//    }
//
//    @Test
//    void testSetAndGetDueDate() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        LocalDate dueDate = LocalDate.of(2024, 4, 30);
//        task.setDueDate(dueDate);
//        assertEquals(java.sql.Date.valueOf(dueDate), task.getDueDate());
//    }
//
//    @Test
//    void testSetDueDateWithNull() {
//        Task task = new Task("Test Task", "Test Description", "HIGH", LocalDate.of(2024, 4, 30));
//        task.setDueDate((LocalDate)null);
//        assertNull(task.getDueDate());
//    }
//
//    @Test
//    void testSetDueDateWithPastDate() {
//        LocalDate pastDate = LocalDate.now().minusDays(1);
//        Task task = new Task("Test Task", "Test Description", "HIGH", null);
//        task.setDueDate(pastDate);
//        assertNull(task.getDueDate());
//    }
}