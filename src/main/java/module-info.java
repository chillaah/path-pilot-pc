module com.example.authentication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;
    requires java.desktop;

    opens com.example.pathpilotfx to javafx.fxml;
    exports com.example.pathpilotfx;
//    exports com.example.pathpilotfx.controller.timer;
//    opens com.example.pathpilotfx.controller.timer to javafx.fxml;
    exports com.example.pathpilotfx.database;
    opens com.example.pathpilotfx.model to javafx.fxml;
    exports com.example.pathpilotfx.model;
    opens com.example.pathpilotfx.database to javafx.fxml;
    //exports com.example.pathpilotfx.model;
    //opens com.example.pathpilotfx.model to javafx.fxml;
    exports com.example.pathpilotfx.controller.todolist;
    opens com.example.pathpilotfx.controller.todolist to javafx.fxml;
    exports com.example.pathpilotfx.controller.navigation;
    opens com.example.pathpilotfx.controller.navigation to javafx.fxml;
    exports com.example.pathpilotfx.controller.authentication;
    opens com.example.pathpilotfx.controller.authentication to javafx.fxml;
}