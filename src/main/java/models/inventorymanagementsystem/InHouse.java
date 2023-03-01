package models.inventorymanagementsystem;

/**
 * @author Jarod Schupp
 */

/**
 * Class holds all methods and variables for InHouse parts under the Part class.
 */

public class InHouse extends Part{
    private int machineId;

    /**
     * Constructor for InHouse parts.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * @param machineId sets the machineId
     */

    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * @return returns the machineId
     */

    public int getMachineId(){
        return machineId;
    }

}

