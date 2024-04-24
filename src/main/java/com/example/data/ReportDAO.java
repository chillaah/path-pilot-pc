package com.example.data;
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
                    "INSERT INTO report VALUES(?,?,?,?,?)");
            insertData.setInt(1, report.getReportID());
            insertData.setInt(2, report.getReporterID());
            insertData.setString(3, report.getCategory());
            insertData.setString(4, report.getDetails());
            insertData.setString(5, report.getPriority());
            insertData.execute();
        }
        catch (SQLException sqlexc){System.err.println(sqlexc);}
    }

    public void update(Report report) {
        try {
            PreparedStatement updateData = connection.prepareStatement(
                    "UPDATE report SET report_id = ?, reporter_id = ?, category = ?, details = ?, priority = ? WHERE id = ?"
            );
            updateData.setInt(1, report.getReportID());
            updateData.setInt(2, report.getReporterID());
            updateData.setString(3, report.getCategory());
            updateData.setString(4, report.getDetails());
            updateData.setString(5, report.getPriority());
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
