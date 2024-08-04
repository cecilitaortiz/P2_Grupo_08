module controllers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens controllers to javafx.fxml;
    exports controllers;
}
