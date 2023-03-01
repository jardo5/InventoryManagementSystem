package models.inventorymanagementsystem; /**
* Supplied class Part.java 
 */

/**
 *
 * @author Jarod Schupp
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class holds all methods and variables for the Product class.
 */

public class Product {

    /**
     * ObservableList of associatedParts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for Product class.
     * @param id product id
     * @param name product name
     * @param price product price
     * @param stock product stock
     * @param min product min
     * @param max product max
     */


    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return returns the product id
     */

    public int getId() {
        return id;
    }

    /**
     * @param id sets the product id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return returns the product name
     */

    public String getName() {
        return name;
    }

    /**
     * @param name sets the product name
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return returns the product price
     */

    public double getPrice() {
        return price;
    }

    /**
     * @param price sets the product price
     */

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return returns the product stock
     */

    public int getStock() {
        return stock;
    }

    /**
     * @param stock sets the product stock
     */

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return returns the product min
     */

    public int getMin() {
        return min;
    }

    /**
     * @param min sets the product min
     */

    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return returns the product max
     */

    public int getMax() {
        return max;
    }

    /**
     * @param max sets the product max
     */

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * @param part adds a part to the associatedParts list
     */

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    /**
     * @param selectedAssociatedPart deletes a part from the associatedParts list
     * @return returns true if the part is deleted
     */

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if (this.associatedParts.contains(selectedAssociatedPart)) {
            this.associatedParts.remove(selectedAssociatedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return returns the associatedParts list
     */

    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}