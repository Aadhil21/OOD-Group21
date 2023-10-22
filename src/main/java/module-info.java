module com.scam.scam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.scam.scam to javafx.fxml;
    exports com.scam.scam;
}