module com.scam.scam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.sacms to javafx.fxml;
    exports com.sacms;
    exports com.sacms.controllers;
    exports com.sacms.models;
    opens com.sacms.controllers to javafx.fxml;
}