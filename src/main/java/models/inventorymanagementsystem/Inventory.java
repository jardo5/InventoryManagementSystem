package models.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {


    private static ObservableList<Part> allParts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
        if (partId > 0) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partId) {
                    return allParts.get(i);
                }
            }
        }
        return null;
    }
    public static ObservableList<Part> lookupPart(String partName) {
        if (!partName.isEmpty()) {
            ObservableList<Part> allParts = getAllParts();
            ObservableList<Part> foundParts = FXCollections.observableArrayList();
            for (Part part : allParts) {
                if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                    foundParts.add(part);
                }
            }
            return foundParts;
        }
        return null;
    }
    public static void updatePart(int index, Part selectedPart) {

    }
    public static boolean deletePart(Part selectedPart) {
        return false;
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    public static Product lookupProduct(int productId) {

        return null;
    }
    public static ObservableList<Product> lookupProduct(String productName) {
        return null;
    }
    public static void updateProduct(int index, Product newProduct) {

    }
    public static boolean deleteProduct(Product selectedProduct) {
        return false;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    public static int setPartID = 0;
    public static int increasePartID() {
        int newID = setPartID + 1;
        while (lookupPart(newID) != null) {
            newID++;
        }
        setPartID = newID;
        return newID;
    }
    public static int setProductID = 0;
    public static int increaseProductID() {
        int newID = setProductID + 1;
        while (lookupProduct(newID) != null) {
            newID++;
        }
        setProductID = newID;
        return newID;
    }
}


