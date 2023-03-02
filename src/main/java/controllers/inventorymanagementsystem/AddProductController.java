package controllers.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Part;
import models.inventorymanagementsystem.Product;

/**
 * Controller class for addProduct.fxml. This class is responsible for handling the addition of a new Product to the Products List.
 */

public class AddProductController {

    /**
     * Textfield for Product ID
     */
    public TextField addProductID;
    /**
     * Textfield for Product Name
     */
    public TextField addProductName;
    /**
     * Textfield for Product Inventory
     */
    public TextField addProductInventory;
    /**
     * Textfield for Product Price
     */
    public TextField addProductPrice;
    /**
     * Textfield for Product Max
     */
    public TextField addProductMax;
    /**
     * Textfield for Product Min
     */
    public TextField addProductMin;
    /**
     * Button to save the new Product
     */
    public Button saveAddProductButton;
    /**
     * Button to cancel the addition of a new Product
     */
    public Button cancelAddProductButton;
    /**
     * Button to remove associated parts
     */

    public Button addProductRemovePart;
    /**
     * Button to add associated parts
     */

    public Button addProductAddButton;
    /**
     * Textfield for searching for parts
     */

    public TextField searchPartsField;
    /**
     * Table to hold parts
     */

    public TableView partsTable;
    /**
     * Column for Part ID
     */
    public TableColumn partIDCol;
    /**
     * Column for Part Name
     */
    public TableColumn partNameCol;
    /**
     * Column for Part Inventory
     */
    public TableColumn partInventoryCol;
    /**
     * Column for Part Price
     */
    public TableColumn partPriceCol;
    /**
     * Table to hold associated parts
     */

    public TableView addProductTable;
    /**
     * Column for associated Part ID
     */
    public TableColumn addProductIDCol;
    /**
     * Column for associated Part Name
     */
    public TableColumn addProductNameCol;
    /**
     * Column for associated Part Inventory
     */
    public TableColumn addProductInventoryCol;
    /**
     * Column for associated Part Price
     */
    public TableColumn addProductPriceCol;

    /**
     * ObservableList to hold the associated parts
     */
    private ObservableList<Part> addProductParts = FXCollections.observableArrayList();

    /**
     * Method to initialize the table views and columns
     * Enters parts id, name, inventory, and price into the parts table
     * Enters associated parts id, name, inventory, and price into the associated parts table
     */

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


    /**
     * Method that allows user to input desired product name, inventory, price, min, and max
     * Validates user input
     * If input is valid, creates a new product and adds it to the products list
     * If input is invalid, displays an error message
     * Closes the window
     */
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
                if (min >= max) {
                    throw new NumberFormatException();
                }
                if (stock < min || stock > max) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please enter valid values for minimum, maximum, and inventory fields.\n(min < max and min <= stock <= max)");
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
            alert.setHeaderText("Invalid Input");
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

    /**
     * Method to search for products
     * Searches for products by name or id
     * If the search field is empty, the table will display all parts
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
     * Method to add a part to the associated parts list
     * If no part is selected, an error message will display
     */

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

    /**
     * Method to remove a part from the associated parts list
     * If no part is selected, an error message will display
     */

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


