module com.example.pathpilotfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pathpilotfx to javafx.fxml;
    exports com.example.pathpilotfx;
}