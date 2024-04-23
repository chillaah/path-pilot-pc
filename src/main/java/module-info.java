module com.example.authentication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.example.authentication to javafx.fxml;
    exports com.example.authentication;
}