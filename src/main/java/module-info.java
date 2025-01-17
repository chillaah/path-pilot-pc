module com.example.pathpilotfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires java.desktop;
    requires jargon2.api;
    requires jdk.compiler;

    opens com.example.pathpilotfx to javafx.fxml;
    opens com.example.pathpilotfx.model to javafx.fxml;
    opens com.example.pathpilotfx.database to javafx.fxml;
    opens com.example.pathpilotfx.controller.todolist to javafx.fxml;
    opens com.example.pathpilotfx.controller.navigation to javafx.fxml;
    opens com.example.pathpilotfx.controller.authentication to javafx.fxml;
    opens com.example.pathpilotfx.controller.map to javafx.fxml;
    opens com.example.pathpilotfx.controller.timer to javafx.fxml;
    opens com.example.pathpilotfx.controller.countries to javafx.fxml;
    opens com.example.pathpilotfx.controller.profile to javafx.fxml;


    exports com.example.pathpilotfx;
    exports com.example.pathpilotfx.database;
    exports com.example.pathpilotfx.model;
    exports com.example.pathpilotfx.controller.todolist;
    exports com.example.pathpilotfx.controller.navigation;
    exports com.example.pathpilotfx.controller.authentication;
    exports com.example.pathpilotfx.controller.timer;
    exports com.example.pathpilotfx.controller.map;
    exports com.example.pathpilotfx.controller.countries;
    exports com.example.pathpilotfx.controller.profile;
}