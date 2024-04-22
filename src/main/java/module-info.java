module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< Updated upstream
=======
    requires java.sql;
    requires java.desktop;
>>>>>>> Stashed changes


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}