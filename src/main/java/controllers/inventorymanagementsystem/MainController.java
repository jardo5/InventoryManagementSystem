package controllers.inventorymanagementsystem;

import jarod.inventorymanagementsystem.AddPartsApplication;
import jarod.inventorymanagementsystem.AddProductApplication;
import jarod.inventorymanagementsystem.ModifyPartsApplication;
import jarod.inventorymanagementsystem.ModifyProductsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Inventory;
import models.inventorymanagementsystem.Part;
import models.inventorymanagementsystem.Product;

import java.io.Console;
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

    private static boolean firstTime = true;





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
    //Add Temporary Parts to Table
    public void addTempParts() {

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
        try {
            new ModifyPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

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
    void addProductButtonClick(ActionEvent event) {
        try {
            new AddProductApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    void modifyProductButtonClick(ActionEvent event) {
        try {
            new ModifyProductsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

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
    void deletePartsButtonClick(ActionEvent event) {

    }


    @FXML
    void deleteProductsButtonClick(ActionEvent event) {

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