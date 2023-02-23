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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.inventorymanagementsystem.Part;
import models.inventorymanagementsystem.Product;

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
        allParts.add(new Part(1, "Part 1", 1.00, 1,1,1));
        allParts.add(new Part(2, "Part 2", 2.00, 2,2,2));
        allParts.add(new Part(3, "Part 3", 3.00, 3,3,3));
        allParts.add(new Part(4, "Part 4", 4.00, 4,4,4));

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
}