package models.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class Inventory {


    private static ObservableList<Part> allParts = FXCollections.observableArrayList();


    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == partId) {
                return part;}}
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
        } else {
            return allParts;
        }

    }
    public static void updatePart(int index, Part selectedPart) {
        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == index) {
                allParts.set(i, selectedPart);
            }
        }

    }
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }


    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (int i=0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == productId) {
                return product;}}
        return null;

    }
    public static ObservableList<Product> lookupProduct(String productName) {
        if (!productName.isEmpty()) {
            ObservableList<Product> allProducts = getAllProducts();
            ObservableList<Product> foundProducts = FXCollections.observableArrayList();
            for (Product product : allProducts) {
                if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                    foundProducts.add(product);
                }
            }
            return foundProducts;
        } else {
            return allProducts;
        }
    }
    public static void updateProduct(int index, Product newProduct) {
        for (int i=0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == index) {
                allProducts.set(i, newProduct);
            }
        }

    }
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


    public static int setPartID = 0;
    public static int increasePartID() {
        Random rand = new Random();
        int newID = setPartID + rand.nextInt(10000);
        while (lookupPart(newID) != null) {
            newID++;
        }
        if (allParts.contains(newID)){
            newID++;
        }
        setPartID = newID;
        return newID;
    }
    public static int setProductID = 0;
    public static int increaseProductID() {
        Random rand = new Random();

        int newID = setProductID + rand.nextInt(10000) ;
        while (lookupProduct(newID) != null) {
            newID++;
        }
        setProductID = newID;
        return newID;
    }
}


