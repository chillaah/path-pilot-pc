package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.database.databasetesting.MockReportDAO;
import com.example.pathpilotfx.model.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MockReportDAOTest {

    private MockReportDAO mockReportDAO;

    @BeforeEach
    public void setUp() {
        mockReportDAO = new MockReportDAO();
    }

    @Test
    public void testInsert() {
        Report report = new Report(1, "category", "Some details", "Medium");
        mockReportDAO.insert(report);
        assertNotNull(mockReportDAO.getByReportId(report.getReportID()));
    }

    @Test
    public void testUpdate() {
        Report report = new Report(1, "category", "Some details", "Medium");
        mockReportDAO.insert(report);
        report.setDetails("Updated details");
        mockReportDAO.update(report);
        assertEquals("Updated details", mockReportDAO.getByReportId(report.getReportID()).getDetails());
    }

    @Test
    public void testDeleteReport() {
        Report report1 = new Report(1, "category", "Some details", "Medium");
        Report report2 = new Report(2, "category", "Some details", "High");
        mockReportDAO.insert(report1);
        mockReportDAO.insert(report2);
        mockReportDAO.deleteReport(report1.getReportID());
        assertNull(mockReportDAO.getByReportId(report1.getReportID()));
        assertNotNull(mockReportDAO.getByReportId(report2.getReportID()));
    }

    @Test
    public void testGetAll() {
        Report report1 = new Report(1, "category", "Some details", "Medium");
        Report report2 = new Report(2, "category", "Some details", "Medium");
        mockReportDAO.insert(report1);
        mockReportDAO.insert(report2);
        List<Report> allReports = mockReportDAO.getAll();
        assertEquals(2, allReports.size());
    }

    @Test
    public void testGetByReportId() {
        Report report = new Report(1, "category", "Some details", "Medium");
        mockReportDAO.insert(report);
        assertNotNull(mockReportDAO.getByReportId(report.getReportID()));
    }

    @Test
    public void testGetByReportId_NotFound() {
        assertNull(mockReportDAO.getByReportId(100)); // Assuming 100 is not a valid report ID
    }
}
