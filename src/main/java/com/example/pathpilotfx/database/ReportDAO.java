package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ReportDAO {
    private Connection connection;

    public ReportDAO() {
        connection = DatabaseConnection.getInstance();
    }


    public void insert(Report report) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO report (reporter_id, category, details, priority) VALUES (?, ?, ?, ?)");
            insertData.setInt(1, report.getReporterID());
            insertData.setString(2, report.getCategory());
            insertData.setString(3, report.getDetails());
            insertData.setString(4, report.getPriority());
            insertData.execute();
        } catch (SQLException sqlexc) {
            System.err.println(sqlexc);
        }
    }


    public void update(Report report) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE report SET reporter_id = ?, category = ?, details = ?, priority = ? WHERE report_id = ?"
            );
            updateData.setInt(1, report.getReporterID());
            updateData.setString(2, report.getCategory());
            updateData.setString(3, report.getDetails());
            updateData.setString(4, report.getPriority());
            updateData.setInt(5, report.getReportID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }


    public void deleteReport(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM report WHERE report_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Report> getAll() {
        List<Report> reports = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM report");
            while (rs.next()) {
                reports.add(
                        new Report(
                                rs.getInt("report_id"),
                                rs.getInt("reporter_id"),
                                rs.getString("category"),
                                rs.getString("details"),
                                rs.getString("priority")
                        )
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return reports;
    }

    public Report getByReportId(int reportID) {
        try {
            PreparedStatement getReport = connection.prepareStatement("SELECT * FROM report WHERE report_id = ?");
            getReport.setInt(1, reportID);
            ResultSet rs = getReport.executeQuery();
            if (rs.next()) {
                return new Report(
                        rs.getInt("report_id"),
                        rs.getInt("reporter_id"),
                        rs.getString("category"),
                        rs.getString("details"),
                        rs.getString("priority")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
