package com.example.pathpilotfx.model;

import com.example.pathpilotfx.model.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    @Test
    public void testGettersAndSetters() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setReporterID(2);
        report.setCategory("Spam or Scam");
        report.setDetails("details");
        report.setPriority("Medium");

        assertEquals(2, report.getReporterID());
        assertEquals("Spam or Scam", report.getCategory());
        assertEquals("details", report.getDetails());
        assertEquals("Medium", report.getPriority());
    }

    @Test
    public void testToString() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        String expectedToString = "Report{reportID=1, reporterID=1, category='Bug', details='Application crashes', priority='High'}";
        String actualToString = report.toString();

        assertEquals(expectedToString, actualToString);
    }

    @Test
    public void testReportIDGetterAndSetter() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setReportID(2);

        assertEquals(2, report.getReportID());
    }

    @Test
    public void testReporterIDGetterAndSetter() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setReporterID(2);

        assertEquals(2, report.getReporterID());
    }

    @Test
    public void testCategoryGetterAndSetter() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setCategory("Feature Request");

        assertEquals("Feature Request", report.getCategory());
    }

    @Test
    public void testDetailsGetterAndSetter() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setDetails("Add dark mode feature");

        assertEquals("Add dark mode feature", report.getDetails());
    }

    @Test
    public void testPriorityGetterAndSetter() {
        Report report = new Report(1, 1, "Bug", "Application crashes", "High");

        report.setPriority("Medium");

        assertEquals("Medium", report.getPriority());
    }
}
