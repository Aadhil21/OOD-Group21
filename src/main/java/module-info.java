module com.scam.scam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.sacms to javafx.fxml;
    exports com.sacms;
    exports com.sacms.controllers;
    opens com.sacms.controllers to javafx.fxml;
}