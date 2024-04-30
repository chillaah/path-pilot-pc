package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    @Test
    public void testGettersAndSetters() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setReporterID(2);
        report.setCategory("Spam or Scam");
        report.setDetails("details");
        report.setPriority("Medium");

        // Then
        assertEquals(2, report.getReporterID());
        assertEquals("Spam or Scam", report.getCategory());
        assertEquals("details", report.getDetails());
        assertEquals("Medium", report.getPriority());
    }

    @Test
    public void testToString() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        String expectedToString = "Report{reportID=1, reporterID=1, category='Bug', details='Application crashes', priority='High'}";
        String actualToString = report.toString();

        // Then
        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void testReportIDGetterAndSetter() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setReportID(2);

        // Then
        assertEquals(2, report.getReportID());
    }

    @Test
    public void testReporterIDGetterAndSetter() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setReporterID(2);

        // Then
        assertEquals(2, report.getReporterID());
    }

    @Test
    public void testCategoryGetterAndSetter() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setCategory("Feature Request");

        // Then
        assertEquals("Feature Request", report.getCategory());
    }

    @Test
    public void testDetailsGetterAndSetter() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setDetails("Add dark mode feature");

        // Then
        assertEquals("Add dark mode feature", report.getDetails());
    }

    @Test
    public void testPriorityGetterAndSetter() {
        // Given
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        // When
        report.setPriority("Medium");

        // Then
        assertEquals("Medium", report.getPriority());
    }
}
