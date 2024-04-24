module com.example.authentication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires java.desktop;

    opens com.example.pathpilotfx to javafx.fxml;
    exports com.example.pathpilotfx;
    exports com.example.pathpilotfx.controller;
    opens com.example.pathpilotfx.controller to javafx.fxml;
    exports com.example.pathpilotfx.Database;
    opens com.example.pathpilotfx.model to javafx.fxml;
    exports com.example.pathpilotfx.model;
    opens com.example.pathpilotfx.Database to javafx.fxml;
    //exports com.example.pathpilotfx.model;
    //opens com.example.pathpilotfx.model to javafx.fxml;
    opens com.example.authentication to javafx.fxml;
    exports com.example.authentication;

}