package controllers.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for ModifyProducts scene. Allows for modification of pre-exsisting products.
 */

public class ModifyProductsController implements Initializable {

    /**
     * modifyProductID textfield for the product id
     */
    public TextField modifyProductID;
    /**
     * modifyProductName textfield for the product name
     */

    public TextField modifyProductName;
    /**
     * modifyProductInventory textfield for the product inventory
     */

    public TextField modifyProductInventory;
    /**
     * modifyProductPrice textfield for the product price
     */

    public TextField modifyProductPrice;
    /**
     * modifyProductMax textfield for the product max
     */

    public TextField modifyProductMax;
    /**
     * modifyProductMin textfield for the product min
     */

    public TextField modifyProductMin;
    /**
     * searchPartsField textfield for the search parts field
     */
    
    public TextField searchPartsField;
    /**
     * partsTable tableview for the parts table
     */

    public TableView partsTable;
    /**
     * partIDCol tablecolumn for the part id column
     */
    public TableColumn partIDCol;
    /**
     * partNameCol tablecolumn for the part name column
     */
    public TableColumn partNameCol;
    /**
     * partInventoryCol tablecolumn for the part inventory column
     */
    public TableColumn partInventoryCol;
    /**
     * partPriceCol tablecolumn for the part price column
     */
    public TableColumn partPriceCol;
    /**
     * modifyProductAddButton button for the add button
     */
    
    public Button modifyProductAddButton;
    /**
     * modifyProductTable tableview for the modify product table
     */
    
    public TableView modifyProductTable;
    /**
     * modifyProductIDCol table column for the modify product id column
     */
    public TableColumn modifyProductIDCol;
    /**
     * modifyProductNameCol table column for the modify product name column
     */
    public TableColumn modifyProductNameCol;
    /**
     * modifyProductInventoryCol table column for the modify product inventory column
     */
    public TableColumn modifyProductInventoryCol;
    /**
     * modifyProductPriceCol table column for the modify product price column
     */
    public TableColumn modifyProductPriceCol;
    /**
     * modifyProductRemovePart button for the remove part button
     */

    public Button modifyProductRemovePart;
    /**
     * saveModifyProductButton button for the save button
     */
    public Button saveModifyProductButton;
    /**
     * cancelModifyProductButton button for the cancel button
     */
    public Button cancelModifyProductButton;

    /**
     * Initializes a new ObservableList of type Part for the modifyProductParts list
     */
    private ObservableList<Part> modifyProductParts = FXCollections.observableArrayList();

    /**
     * Retrieves the product data from the ObservableList of type Product
     * @param product the product to retrieve data from
     *
     */
    public void retrieveProductData(Product product) {
        modifyProductID.setText(String.valueOf(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductInventory.setText(String.valueOf(product.getStock()));
        modifyProductPrice.setText(String.valueOf(product.getPrice()));
        modifyProductMax.setText(String.valueOf(product.getMax()));
        modifyProductMin.setText(String.valueOf(product.getMin()));
        modifyProductParts.addAll(product.getAllAssociatedParts());
    }

    /**
     * Search for a part in the parts table via Name or ID
     * @param event on enter event to search for a part
     */

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

    /**
     * Add a part to the modifyProductParts list
     * @param event on click event to add a part
     */

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

    /**
     * Remove a part from the modifyProductParts list
     * @param event on click event to remove a part
     */

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

    /**
     * Save the modified product
     * @param event on click event to save the modified product. Returns to the main screen.
     * @throws IOException if input error occurs
     */

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
                if (max > stock && stock > min){
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

    /**
     * Cancel the modification of a product
     * @param event on click event to cancel the modification of a product. Return to the main screen.
     */

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

    /**
     * Fill the text fields of both tables with the product information
     * @param url passes data to the controller
     * @param resourceBundle manages data associated with the controller
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

}
