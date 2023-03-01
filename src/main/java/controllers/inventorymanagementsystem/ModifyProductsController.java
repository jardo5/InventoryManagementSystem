package controllers.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductsController {

    public TextField modifyProductID;
    public TextField modifyProductName;
    public TextField modifyProductInventory;
    public TextField modifyProductPrice;
    public TextField modifyProductMax;
    public TextField modifyProductMin;
    
    public TextField searchPartsField;

    public TableView partsTable;
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryCol;
    public TableColumn partPriceCol;
    
    public Button modifyProductAddButton;
    
    public TableView modifyProductTable;
    public TableColumn modifyProductIDCol;
    public TableColumn modifyProductNameCol;
    public TableColumn modifyProductInventoryCol;
    public TableColumn modifyProductPriceCol;

    public Button modifyProductRemovePart;
    public Button saveModifyProductButton;
    public Button cancelModifyProductButton;

    private ObservableList<Part> modifyProductParts = FXCollections.observableArrayList();

    public void initialize() {
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


        modifyProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        modifyProductTable.setItems(modifyProductParts);
    }


    public void retrieveProductData(Product product) {
        modifyProductID.setText(String.valueOf(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductInventory.setText(String.valueOf(product.getStock()));
        modifyProductPrice.setText(String.valueOf(product.getPrice()));
        modifyProductMax.setText(String.valueOf(product.getMax()));
        modifyProductMin.setText(String.valueOf(product.getMin()));
        modifyProductParts.addAll(product.getAllAssociatedParts());
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

    public void modifyProductAddButtonClick() {
        Part part = (Part) partsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            modifyProductParts.add(part);
            modifyProductTable.setItems(modifyProductParts);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to add.");
            alert.showAndWait();
        }
    }

    public void modifyProductRemovePartClick() {
        Part part = (Part) modifyProductTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            // Alert to confirm removing part
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Remove Part?");
            alert.setHeaderText("Are you sure you want to remove this part?");
            alert.setContentText("This action is permanent.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    modifyProductParts.remove(part);
                    modifyProductTable.setItems(modifyProductParts);
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

    public void saveModifyProductButtonClick() {
        int min = 0;
        int max = 0;
        int stock = 0;
        double price;
        int id = Integer.parseInt(modifyProductID.getText());
        String name = modifyProductName.getText();
        if (name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Name field is empty");
            alert.setContentText("Please enter a name for the product.");
            alert.showAndWait();
        } else {
            try {
                stock = Integer.parseInt(modifyProductInventory.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Inventory field is empty");
                alert.setContentText("Please enter a number for the inventory.");
                alert.showAndWait();
            }
            try {
                max = Integer.parseInt(modifyProductMax.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Max field is empty");
                alert.setContentText("Please enter a number for the max.");
                alert.showAndWait();
            }
            try {
                min = Integer.parseInt(modifyProductMin.getText());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Min field is empty");
                alert.setContentText("Please enter a number for the min.");
                alert.showAndWait();
            }
            try{
                price = Double.parseDouble(modifyProductPrice.getText());
                if (max > min){
                    Product product = new Product(id, name, price, stock, min, max);
                    product.getAllAssociatedParts().addAll(modifyProductParts);
                    Inventory.updateProduct(id, product);
                    Stage stage = (Stage) saveModifyProductButton.getScene().getWindow();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Max is less than min");
                    alert.setContentText("Please enter a max that is greater than the min.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Price field is empty");
                alert.setContentText("Please enter a number for the price.");
                alert.showAndWait();
            }
        }


    }

    public void cancelModifyProductButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Add Product?");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("All unsaved changes will be lost.");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) cancelModifyProductButton.getScene().getWindow();
                stage.close();
            }
        });
    }

}
