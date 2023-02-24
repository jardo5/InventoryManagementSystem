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


    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private  ObservableList<Product> allProducts = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Populate Parts Table to ArrayList
        partsTable.setItems(allParts);
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));



        //Populate Products Table to ArrayList
        productsTable.setItems(allProducts);
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Add Temp Parts to Table
        allParts.add(new Part(1, "Rim", 314.35, 4,1,1));
        allParts.add(new Part(2, "Tire", 112.12, 4,2,2));
        allParts.add(new Part(3, "Lugs", 76.89, 8,3,3));
        allParts.add(new Part(4, "Socket", 6.50, 1,4,4));

        //Add Temp Products to Table
        allProducts.add(new Product(1, "Product 1", 1.00, 1,1,1));
        allProducts.add(new Product(2, "Product 2", 2.00, 2,2,2));
        allProducts.add(new Product(3, "Product 3", 3.00, 3,3,3));
        allProducts.add(new Product(4, "Product 4", 4.00, 4,4,4));

    }

    //Launch Add Parts Scene Button
    @FXML
    void addPartButtonClick(ActionEvent event) {
        try {
            new AddPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    //Launch Modify Part Scene Button
    @FXML
    void modifyPartButtonClick(ActionEvent event) {
        try {
            new ModifyPartsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Search Parts Field with name or ID
    @FXML
    void searchPartFieldClick(ActionEvent event) {

    }



    //Launch Add Product Scene Button
    @FXML
    void addProductButtonClick(ActionEvent event) {
        try {
            new AddProductApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Launch Modify Product Scene Button
    @FXML
    void modifyProductButtonClick(ActionEvent event) {
        try {
            new ModifyProductsApplication().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    //Search Products Field with name or ID
    @FXML
    void searchProductFieldClick(ActionEvent event) {

    }



    //Delete Highlighted Part Button
    @FXML
    void deletePartsButtonClick(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        allParts.remove(part);
    }

    //Delete Highlighted Product Button
    @FXML
    void deleteProductsButtonClick(ActionEvent event) {
        Product product = productsTable.getSelectionModel().getSelectedItem();
        allProducts.remove(product);
    }


    public void mainExitClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Close Inventory Management System");
        alert.setHeaderText("Are you sure you want to close this window?");
        alert.setContentText("");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) mainExit.getScene().getWindow();
                stage.close();
                System.out.println("Jarod Schupp - C482 - Inventory Management System - 2023");
            }
        });
    }
}