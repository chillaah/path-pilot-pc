package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.TaskV;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskVTest {

    @Test
    public void testUserIDGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setUserID(4);

        // Then
        assertEquals(4, taskV.getUserID());
    }
    @Test
    public void testDeadlineGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setDeadline(Date.valueOf("2022-01-10"));

        // Then
        assertEquals(Date.valueOf("2022-01-10"), taskV.getDeadline());
    }

    @Test
    public void testReminderGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setReminder(Date.valueOf("2022-01-05"));

        // Then
        assertEquals(Date.valueOf("2022-01-05"), taskV.getReminder());
    }

    @Test
    public void testSelectedGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setSelected(true);

        // Then
        assertTrue(taskV.isSelected());
    }


    @Test
    public void testTaskIDGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setTaskID(5);

        // Then
        assertEquals(5, taskV.getTaskID());
    }

    // Add similar test methods for other getters and setters

    @Test
    public void testToString() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        String expectedToString = "Task{" +
                "userID=1, taskID=2, assignmentID=3, taskName='TaskName', description='Description', " +
                "deadline=2022-01-01, reminder=2022-01-02, isSelected=false, status='Status', category='Category', " +
                "priority='Priority', extraNotes='ExtraNotes'}";
        String actualToString = taskV.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }
    @Test
    public void testAssignmentIDGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setAssignmentID(6);

        // Then
        assertEquals(6, taskV.getAssignmentID());
    }

    @Test
    public void testTaskNameGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setTaskName("NewTaskName");

        // Then
        assertEquals("NewTaskName", taskV.getTaskName());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        // Given
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        // When
        taskV.setDescription("NewDescription");

        // Then
        assertEquals("NewDescription", taskV.getDescription());
    }

    // Add similar test methods for other getters and setters

}
