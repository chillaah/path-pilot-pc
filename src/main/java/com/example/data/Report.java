package com.example.data;

public class Report {
    private int reportID;
    private int reporterID;
    private String category;
    private String details;
    private String priority;

    public Report(int reportID, int reporterID, String category, String details, String priority) {
        this.reportID = reportID;
        this.reporterID = reporterID;
        this.category = category;
        this.details = details;
        this.priority = priority;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getReporterID() {
        return reporterID;
    }

    public void setReporterID(int reporterID) {
        this.reporterID = reporterID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportID=" + reportID +
                ", reporterID=" + reporterID +
                ", category='" + category + '\'' +
                ", details='" + details + '\'' +
                ", priority='" + priority + '\'' +
                '}';
    }
}
