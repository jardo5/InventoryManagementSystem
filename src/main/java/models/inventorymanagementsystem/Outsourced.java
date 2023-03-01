package models.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

/**
 * Class holds all methods and variables for Outsourced parts under the Part class.
 */
public class Outsourced extends Part {
    private String companyName;

    /**
     * Constructor for Outsourced parts.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @param companyName sets the companyName
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return returns the companyName
     */

    public String getCompanyName(){
        return companyName;
    }
}
