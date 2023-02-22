package controllers.inventorymanagementsystem;

import jarod.inventorymanagementsystem.AddPartsApplication;
import jarod.inventorymanagementsystem.AddProductApplication;
import jarod.inventorymanagementsystem.ModifyPartsApplication;
import jarod.inventorymanagementsystem.ModifyProductsApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MainController {
    @FXML
    private Label welcomeText;
    //Add Parts Button
    @FXML
    void addPartButtonClick(ActionEvent event) {
        try {
            new AddPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    //Modify Part Button
    @FXML
    void modifyPartButtonClick(ActionEvent event) {
        try {
            new ModifyPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Add Product Button
    @FXML
    void addProductButtonClick(ActionEvent event) {
        try {
            new AddProductApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Modify Product Button
    @FXML
    void modifyProductButtonClick(ActionEvent event) {
        try {
            new ModifyProductsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}