module com.scam.scam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.scam.scam to javafx.fxml;
    exports com.scam.scam;
}