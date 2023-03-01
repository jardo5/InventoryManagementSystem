package controllers.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Part;
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

    public TableView partsTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryCol;
    public TableColumn partPriceCol;

    public TableView addProductTable;
    public TableColumn addProductIDCol;
    public TableColumn addProductNameCol;
    public TableColumn addProductInventoryCol;
    public TableColumn addProductPriceCol;

    private ObservableList<Part> addProductParts = FXCollections.observableArrayList();

    public void initialize() {
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        addProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        addProductTable.setItems(addProductParts);
    }


    public void saveAddProductButtonClick() {
        int stock = 0;
        int min;
        int max = 0;
        double price;
        int id = Inventory.increaseProductID();
        String name = addProductName.getText();
        try {
            stock = Integer.parseInt(addProductInventory.getText());
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
            price = Double.parseDouble(addProductPrice.getText());
            min = Integer.parseInt(addProductMin.getText());
            max = Integer.parseInt(addProductMax.getText());
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
        String search = searchPartsField.getText();
        ObservableList<Part> parts = Inventory.lookupPart(search);
        if (parts.size() != 0) {
            partsTable.setItems(parts);
        } else {
            try {
                int id = Integer.parseInt(search);
                Part part = Inventory.lookupPart(id);
                if (part != null) {
                    parts.add(part);
                }
            } catch (NumberFormatException e) {
            }
            partsTable.setItems(parts);
        }
    }

    public void addProductAddButtonClick() {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            addProductParts.add(part);
            addProductTable.setItems(addProductParts);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
        }

    }

    public void addProductRemovePartClick() {
        Part part = (Part) addProductTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            // Alert to confirm removing part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Part?");
            alert.setHeaderText("Are you sure you want to remove this part?");
            alert.setContentText("This action is permanent.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    addProductParts.remove(part);
                    addProductTable.setItems(addProductParts);
                }
            });
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to remove.");
            alert.showAndWait();
        }
    }

}


