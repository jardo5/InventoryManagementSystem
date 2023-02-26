package controllers.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.inventorymanagementsystem.InHouse;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartsController implements Initializable {
    @FXML
    public Button saveAddPartButton;
    public Button cancelAddPartButton;

    public RadioButton radioInHouse; //Machine ID
    public RadioButton radioOutsource; //Company Name
    public Text idOrName;

    public TextField addPartID;
    public TextField addPartName;
    public TextField addPartInventory;
    public TextField addPartPrice;
    public TextField addPartMax;
    public TextField addPartMin;
    public TextField addPartIDOrName;


    public void onSaveAddPartButton(ActionEvent actionEvent) throws IOException {
        int stock = 0;
        int min;
        int max = 0;
        int id = Inventory.increasePartID();
        String companyName;
        int machineId;
        double price;
        // Collect data from text fields
        // Add data to allParts list
        // Close window
        try {
            String name = addPartName.getText();
            stock = Integer.parseInt(addPartInventory.getText());
            try {
                if (stock < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Inventory");
                alert.setContentText("Inventory must be greater than 0.");
                alert.showAndWait();
                return;
            }
            price = Double.parseDouble(addPartPrice.getText());
            min = Integer.parseInt(addPartMin.getText());
            max = Integer.parseInt(addPartMax.getText());
            try {
                if (min > max) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Inventory");
                alert.setContentText("Inventory must be between minimum and maximum values.");
                alert.showAndWait();
                return;
            }
            if (radioInHouse.isSelected()) {
                machineId = Integer.parseInt(addPartIDOrName.getText());
                InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.addPart(inHouse);
            } else if (radioOutsource.isSelected()) {
                companyName = addPartIDOrName.getText();
                Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                Inventory.addPart(outsourced);
            }
            Stage stage = (Stage) saveAddPartButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please enter valid values for each field.");
            alert.showAndWait();
            return;
        }
    }

    public void onCancelAddPartButton(ActionEvent actionEvent) {
        // Alert to confirm closing window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Add Part?");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("All unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelAddPartButton.getScene().getWindow();
                stage.close();
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void radioOutsourceToggle(ActionEvent actionEvent) {
        if (radioOutsource.isSelected()) {
            idOrName.setText("Company Name");
        }
    }

    public void radioInHouseToggle(ActionEvent actionEvent) {
        if (radioInHouse.isSelected()) {
            idOrName.setText("Machine ID");
        }
    }
}
