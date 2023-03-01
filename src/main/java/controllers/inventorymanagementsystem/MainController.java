package controllers.inventorymanagementsystem;

import jarod.inventorymanagementsystem.AddPartsApplication;
import jarod.inventorymanagementsystem.AddProductApplication;
import jarod.inventorymanagementsystem.ModifyPartApplication;
import jarod.inventorymanagementsystem.ModifyProductsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Part;
import models.inventorymanagementsystem.Product;

import java.net.URL;
import java.util.ResourceBundle;

import static models.inventorymanagementsystem.Inventory.*;


public class MainController implements Initializable {

    public TableView<Part> partsTable;
    
    public TableColumn partIDCol;
    public TableColumn partNameCol;
    public TableColumn partInventoryCol;
    public TableColumn partPriceCol;

    public TableView<Product> productsTable;
    public TableColumn productIDCol;
    public TableColumn productNameCol;
    public TableColumn productInventoryCol;
    public TableColumn productPriceCol;

    public Button deletePartsButton;
    public Button deleteProductsButton;

    public TextField searchPartsField;
    public TextField searchProductsField;

    public Button mainExit;






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));



        productsTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


    @FXML
    void addPartButtonClick(ActionEvent event) {
        try {
            new AddPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    void modifyPartButtonClick(ActionEvent event) {
        if (partsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/inventorymanagementsystem/modifyPart.fxml"));
                Parent root = loader.load();
                ModifyPartController controller = loader.getController();
                controller.retrievePartData(partsTable.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to modify");
            alert.showAndWait();
        }
    }

    @FXML
    void searchPartFieldClick(ActionEvent event) {
        if (!searchPartsField.getText().isEmpty()) {
            String search = searchPartsField.getText();
            ObservableList<Part> searchParts = FXCollections.observableArrayList();
            for (Part part : getAllParts()) {
                if (part.getName().toLowerCase().contains(search.toLowerCase())) {
                    searchParts.add(part);
                }
                else if (String.valueOf(part.getId()).contains(search)) {
                    searchParts.add(part);
                }
            }
            partsTable.setItems(searchParts);
        }
        else {
            partsTable.setItems(getAllParts());
        }

    }

    @FXML
    void deletePartsButtonClick(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if (part != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part");
            alert.setHeaderText("Are you sure you want to delete this part?");
            alert.setContentText("Click OK to confirm or Cancel to go back.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    deletePart(part);
                }
            });
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No part selected");
            alert.setContentText("Please select a part to delete");
            alert.showAndWait();
        }
    }


    @FXML
    void addProductButtonClick(ActionEvent event) {
        try {
            new AddProductApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    void modifyProductButtonClick(ActionEvent event) {
        if (productsTable.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/jarod/inventorymanagementsystem/modifyProduct.fxml"));
                Parent root = loader.load();
                ModifyProductsController controller = loader.getController();
                controller.retrieveProductData(productsTable.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product to modify");
            alert.showAndWait();
        }
    }

    @FXML
    void searchProductFieldClick(ActionEvent event) {
        String productName = searchProductsField.getText();
        ObservableList<Product> products = lookupProduct(productName);
        if (products.size() == 0) {
            try {
                int productId = Integer.parseInt(productName);
                Product product = lookupProduct(productId);
                if (product != null)
                    products.add(product);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        productsTable.setItems(products);
        //display error label if no products detected in the table
        if (products.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No products found");
            alert.setContentText("Please try again");
            alert.showAndWait();
        }
    }


    @FXML
    void deleteProductsButtonClick(ActionEvent event) {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Are you sure you want to delete this product?");
            alert.setContentText("Click OK to confirm or Cancel to go back.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    deleteProduct(product);
                }
            });
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No product selected");
            alert.setContentText("Please select a product to delete");
            alert.showAndWait();
        }
    }


    public void mainExitClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Inventory Management System");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) mainExit.getScene().getWindow();
                System.out.println("Jarod Schupp - C482 - Inventory Management System - 2023");
                stage.close();

            }
        });
    }
}