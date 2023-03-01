package controllers.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

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

/**
 * Controller class for addPart.fxml. This class is responsible for handling the addition of a new Part to the Parts List.
 */

public class AddPartsController implements Initializable {
    /**
     * Button to save the new Part
     */
    public Button saveAddPartButton;
    /**
     * Button to cancel the addition of a new Part
     */
    public Button cancelAddPartButton;
    /**
     * Radio button for InHouse
     */

    public RadioButton radioInHouse; //Machine ID
    /**
     * Radio button for Outsourced
     */
    public RadioButton radioOutsource; //Company Name
    /**
     * Textfield for Company Name or Machine ID
     */
    public Text idOrName;
    /**
     * Textfield for Part ID
     */

    public TextField addPartID;
    /**
     * Textfield for Part Name
     */
    public TextField addPartName;
    /**
     * Textfield for Part Inventory
     */
    public TextField addPartInventory;
    /**
     * Textfield for Part Price
     */
    public TextField addPartPrice;
    /**
     * Textfield for Part Max
     */
    public TextField addPartMax;
    /**
     * Textfield for Part Min
     */
    public TextField addPartMin;
    /**
     * Textfield for Company Name or Machine ID
     */
    public TextField addPartIDOrName;

    /**
     * Method that on save button click, saves the new Part to the Parts List.
     * Validates user input and displays error messages if input is invalid.
     * Closes the window on save.
     * @param actionEvent on save button click adds new Part to Parts List
     * @throws IOException if input is invalid
     */

    public void onSaveAddPartButton(ActionEvent actionEvent) throws IOException {
        int stock = 0;
        int min;
        int max = 0;
        int id = Inventory.increasePartID();
        String companyName;
        int machineId;
        double price;
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
                if (min > stock && stock > max) {
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
    /**
     * Method that on cancel button click, closes the window.
     * @param actionEvent on cancel button click closes the window
     */

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
        //ignore
    }

    /**
     * Method that on radio button toggle, changes the label for the Company Name or Machine ID textfield.
     * @param actionEvent on radio button toggle changes the label for the Company Name or Machine ID textfield
     */

    public void radioOutsourceToggle(ActionEvent actionEvent) {
        if (radioOutsource.isSelected()) {
            idOrName.setText("Company Name");
        }
    }

    /**
     * Method that on radio button toggle, changes the label for the Company Name or Machine ID textfield.
     * @param actionEvent on radio button toggle changes the label for the Company Name or Machine ID textfield
     */

    public void radioInHouseToggle(ActionEvent actionEvent) {
        if (radioInHouse.isSelected()) {
            idOrName.setText("Machine ID");
        }
    }
}
