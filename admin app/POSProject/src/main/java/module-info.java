module com.example.posproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.posproject to javafx.fxml;
    exports com.example.posproject;
}