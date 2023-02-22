module jarod.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens controllers.inventorymanagementsystem to javafx.fxml;
    exports jarod.inventorymanagementsystem;
}