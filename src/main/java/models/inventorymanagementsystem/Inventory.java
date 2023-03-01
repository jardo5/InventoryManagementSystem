package models.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

/**
 * Inventory class. This class is responsible for handling the Parts and Products Lists.
 * It contains methods to add, modify, and delete parts and products.
 * It also contains methods to lookup parts and products by ID or name.
 * It also contains methods to generate a unique ID for a new part or product.
 */

public class Inventory {
    /**
     * ObservableList of all Parts
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Adds a new part to the allParts list.
     * @param newPart The part to be added.
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Iterates through the allParts list and returns the part with the matching ID or name.
     * @param partId The ID of the part to be returned.
     * @return The part with the matching ID.
     */

    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == partId) {
                return part;}}
        return null;
    }

    /**
     * Iterates through the allParts list and returns a list of parts with the matching name.
     * @param partName The name of the part to be returned.
     * @return A list of parts with the matching name.
     */
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
    /**
     * Selects an existing part, updates it, and adds it back into the allParts list.
     * @param index The index of the part to be updated.
     * @param selectedPart The part to be updated.
     */
    public static void updatePart(int index, Part selectedPart) {
        for (int i=0; i < allParts.size(); i++) {
            Part part = allParts.get(i);
            if (part.getId() == index) {
                allParts.set(i, selectedPart);
            }
        }

    }
    /**
     * Deletes a part from the allParts list.
     * @param selectedPart The part to be deleted.
     * @return Deletes part from the allParts list.
     */
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);
    }
    /**
     * Returns the allParts list.
     * @return The allParts list.
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    /**
     * ObservableList of all Products
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    /**
     * Adds a new product to the allProducts list.
     * @param newProduct The product to be added.
     */

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    /**
     * Iterates through the allProducts list and returns the product with the matching ID or name.
     * @param productId The ID of the product to be returned.
     * @return The product with the matching ID.
     */
    public static Product lookupProduct(int productId) {
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (int i=0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == productId) {
                return product;}}
        return null;

    }
    /**
     * Iterates through the allProducts list and returns a list of products with the matching name.
     * @param productName The name of the product to be returned.
     * @return A list of products with the matching name.
     */
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
    /**
     * Selects an existing product, updates it, and adds it back into the allProducts list.
     * @param index The index of the product to be updated.
     * @param newProduct The product to be updated.
     */
    public static void updateProduct(int index, Product newProduct) {
        for (int i=0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            if (product.getId() == index) {
                allProducts.set(i, newProduct);
            }
        }

    }
    /**
     * Deletes a product from the allProducts list.
     * @param selectedProduct The product to be deleted.
     * @return Deletes product from the allProducts list.
     */
    public static boolean deleteProduct(Product selectedProduct) {
        return allProducts.remove(selectedProduct);
    }
    /**
     * Returns the allProducts list.
     * @return The allProducts list.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int setPartID = 0;
    /**
     * Generates a random ID for a new part.
     * @return A random ID for a new part.
     */
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
    /**
     * Generates a random ID for a new product.
     * @return A random ID for a new product.
     */
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


