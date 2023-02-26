package controllers.inventorymanagementsystem;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Product;

public class AddProductController {

    public TextField addProductID;
    public TextField addProductName;
    public TextField addProductInventory;
    public TextField addProductPrice;
    public TextField addProductMax;
    public TextField addProductMin;

    public Button saveAddProductButton;
    public Button cancelAddProductButton;
    public Button addProductRemovePart;
    public Button addProductAddButton;
    public TextField searchPartsField;

    public void saveAddProductButtonClick() {
        int stock = 0;
        int min;
        int max = 0;
        double price;
        int id = Inventory.increaseProductID();
        String name = addProductName.getText();
        try {
            stock = Integer.parseInt(addProductInventory.getText());
            if (stock < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Inventory must be greater than 0.");
                alert.showAndWait();
            }
            price = Double.parseDouble(addProductPrice.getText());
            min = Integer.parseInt(addProductMin.getText());
            max = Integer.parseInt(addProductMax.getText());
            if (min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("Min cannot be greater than Max.");
                alert.show();
            }
            Product product = new Product(id, name, price, stock, min, max);
            Inventory.addProduct(product);
            Stage stage = (Stage) saveAddProductButton.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid input");
            alert.setContentText("Please enter a valid value for each field.");
            alert.showAndWait();
        }
    }

    public void cancelAddProductButtonClick() {
        // Alert to confirm closing window
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Add Product?");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("All unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelAddProductButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void searchPartFieldClick() {
        // Search for part by name or ID
        // Display results in table
    }

    public void addProductAddButtonClick() {
        // Add selected part to associated parts list
    }

    public void addProductRemovePartClick() {
        // Remove selected part from associated parts list
    }

}


