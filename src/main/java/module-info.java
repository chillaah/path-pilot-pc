module com.example.pathpilotfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pathpilotfx to javafx.fxml;
    exports com.example.pathpilotfx;
    exports com.example.pathpilotfx.controller;
    opens com.example.pathpilotfx.controller to javafx.fxml;
    //exports com.example.pathpilotfx.model;
    //opens com.example.pathpilotfx.model to javafx.fxml;
}