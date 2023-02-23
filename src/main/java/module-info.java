module jarod.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    opens controllers.inventorymanagementsystem to javafx.fxml, java.base;
    opens models.inventorymanagementsystem to javafx.base, javafx.fxml;
    exports jarod.inventorymanagementsystem;
}