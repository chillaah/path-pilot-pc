package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.TaskV;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskVTest {

    @Test
    public void testUserIDGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setUserID(4);

        assertEquals(4, taskV.getUserID());
    }
    @Test
    public void testDeadlineGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setDeadline(Date.valueOf("2022-01-10"));

        assertEquals(Date.valueOf("2022-01-10"), taskV.getDeadline());
    }

    @Test
    public void testReminderGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setReminder(Date.valueOf("2022-01-05"));

        assertEquals(Date.valueOf("2022-01-05"), taskV.getReminder());
    }

    @Test
    public void testSelectedGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setSelected(true);

        assertTrue(taskV.isSelected());
    }


    @Test
    public void testTaskIDGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setTaskID(5);

        assertEquals(5, taskV.getTaskID());
    }


    @Test
    public void testToString() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        String expectedToString = "Task{" +
                "userID=1, taskID=2, assignmentID=3, taskName='TaskName', description='Description', " +
                "deadline=2022-01-01, reminder=2022-01-02, isSelected=false, status='Status', category='Category', " +
                "priority='Priority', extraNotes='ExtraNotes'}";
        String actualToString = taskV.toString();

        assertEquals(expectedToString, actualToString);
    }
    @Test
    public void testAssignmentIDGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setAssignmentID(6);

        assertEquals(6, taskV.getAssignmentID());
    }

    @Test
    public void testTaskNameGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setTaskName("NewTaskName");

        assertEquals("NewTaskName", taskV.getTaskName());
    }

    @Test
    public void testDescriptionGetterAndSetter() {
        TaskV taskV = new TaskV(1, 2, "TaskName", "Description", Date.valueOf("2022-01-01"),
                Date.valueOf("2022-01-02"), false, 3, "Status", "Category", "Priority", "ExtraNotes");

        taskV.setDescription("NewDescription");

        assertEquals("NewDescription", taskV.getDescription());
    }


}
