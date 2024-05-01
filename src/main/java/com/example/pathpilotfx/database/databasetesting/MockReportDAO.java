package com.example.pathpilotfx.database.databasetesting;

import com.example.pathpilotfx.model.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockReportDAO {
    private Map<Integer, Report> reports = new HashMap<>();
    private int nextReportId = 1;

    public void insert(Report report) {
        int reportId = nextReportId++;
        report.setReportID(reportId);
        reports.put(reportId, report);
    }

    public void update(Report report) {
        reports.put(report.getReportID(), report);
    }

    public void deleteReport(int id) {
        reports.remove(id);
    }

    public List<Report> getAll() {
        return new ArrayList<>(reports.values());
    }

    public Report getByReportId(int reportID) {
        return reports.get(reportID);
    }

}
