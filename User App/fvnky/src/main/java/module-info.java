module com.mycompany.fvnky {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.fvnky to javafx.fxml;
    exports com.mycompany.fvnky;
}
