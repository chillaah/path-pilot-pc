package com.example.pathpilotfx.database;
import com.example.pathpilotfx.model.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 Class for Report Data Access Object for SQLite queries
 **/
public class ReportDAO {
    private Connection connection;

    public ReportDAO() {
        connection = DatabaseConnection.getInstance();
    }

    /**
     Method that inserts report data
     **/
    public void insert(Report report) {
        try {
            PreparedStatement insertData = connection.prepareStatement(
                    "INSERT INTO report VALUES(?,?,?,?)");
            insertData.setInt(1, report.getReporterID());
            insertData.setString(2, report.getCategory());
            insertData.setString(3, report.getDetails());
            insertData.setString(4, report.getPriority());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }
    /**
     Method that updates report data
     **/
    public void update(Report report) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE report SET category = ?, details = ?, priority = ? WHERE report_id = ?"
            );
            updateData.setString(1, report.getCategory());
            updateData.setString(2, report.getDetails());
            updateData.setString(3, report.getPriority());
            updateData.setInt(4, report.getReportID());
            updateData.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /**
     Method that deletes specific report data
     @param id the report id
     **/

    public void deleteReport(int id) {
        try {
            PreparedStatement delete = connection.prepareStatement("DELETE FROM report WHERE report_id = ?");
            delete.setInt(1, id);
            delete.execute();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
    /**
     Method that gets all report data
     @return Arraylist of reports
     **/

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
    /**
     Method that gets all report data for a report id
     @return Report instance
     **/

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
    /**
     Method that closes the database connection
     **/
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
