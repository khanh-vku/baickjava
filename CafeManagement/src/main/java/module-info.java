module org.example.demo {

    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;

    opens org.example.demo to javafx.fxml;
    opens org.example.demo.client.controller to javafx.fxml;

    opens org.example.demo.model to javafx.base;

    exports org.example.demo;
    exports org.example.demo.client.controller;
}