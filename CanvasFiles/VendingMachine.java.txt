/**
 * A simple program to demonstrate knowledge of the concepts of Object-Oriented Programming
 * @author Peter-John Matzig (000926606) January 2024.
 *
 */
public class VendingMachine {
    private String itemName;
    private double unitCost;
    private double currentCredit;
    private double totalRevenue;
    private int unitsRemaining;
    public static int machineCount=0;

    /**
     * This is the object constructor. Each non-String parameter should be instantiated to a positive non-zero value. But,
     * the constructor WILL accept 0 and negative values for the non-String parameters.
     * @param itemName is a String
     * @param unitCost is a double
     * @param unitsRemaining is an integer
     * machineCount keeps track of every object instantiated. It is automatically incremented
     */
        public VendingMachine(String itemName, double unitCost, int unitsRemaining) {
        this.itemName = itemName;
        this.unitCost = unitCost;
        this.unitsRemaining = unitsRemaining;
        machineCount++;
    }

    /**
     * Returns the proper name of the product in the machine
     * @return is a String
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Returns the unit cost of the item in the vending machine
     * @return is a double
     */
    public double getUnitCost() {
        return unitCost;
    }

    /**
     * Returns the current amount of credit that the user has entered
     * @return is a double
     */
    public double getCurrentCredit() {
        return currentCredit;
    }

    /**
     * Returns the current total revenue that the machine has accumulated
     * since the last time it was turned on/emptied
     * @return is a double
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Returns the number of items left in the vending machine
     * @return is an integer
     */
    public int getUnitsRemaining() {
        return unitsRemaining;
    }

    /**
     * Sets the name of the item in the machine
     * @param itemName A String representing the name of the item in the machine
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets the unit cost of the item in the machine
     * @param unitCost is a double representing the unit cost of the item
     */
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     * This method is called when the currentCredit variable needs to change - it adds when a user
     * enters credit and subtracts when a user successfully purchases an item
     * @param currentCredit The amount that the instance variable is modified by, in this program the
     *                      unitCost of the item is the subtract value and the creditAdd variable
     *                      is the add value
     * @param addSubtract is a flag that determines whether to add the currentCredit variable to the total or
     *                    subtract it from the total
     */
    public void setCurrentCredit(double currentCredit, String addSubtract) {
        if(addSubtract.equals("add")) {
            this.currentCredit += currentCredit;
        }
        if(addSubtract.equals("subtract")){
            this.currentCredit-=currentCredit;
        }
    }

    /**
     * This method is invoked when the totalRevenue instance variable is modified. It can be incremented (add)
     * , decremented (subtract) or reset to a certain number (reset). In this program, reset is used to
     * change the value to zero.
     * @param totalRevenue is the amount by which the variable will change.
     * @param status is the flag that determines if the totalRevenue variable is added, subtracted or reset
     *               to that specific value.
     */
    public void setTotalRevenue(double totalRevenue, String status) {
        if(status.equals("add")) {
            this.totalRevenue += totalRevenue;
        }
        if (status.equals("subtract")){
            this.totalRevenue-=totalRevenue;
        }
        if (status.equals("reset")){
            this.totalRevenue=totalRevenue;
        }
    }

    /**
     * This is the method used to alter the unitsRemaining instance variable. This method can increment the total (add)
     * or decrement the total (subtract) or reset the total to a specific value (reset).
     * @param unitsRemaining this is the amount by which the total changes.
     * @param addSubtract this is the flag that determines if the value is added or subtracted from
     *                    the total. Reset resets the total to a specific value.
     */
    public void setUnitsRemaining(int unitsRemaining, String addSubtract) {
        if(addSubtract.equals("add")) {
            this.unitsRemaining += unitsRemaining;
        }
        if (addSubtract.equals("subtract")){
            this.unitsRemaining -= unitsRemaining;
        }
        if(addSubtract.equals("reset")){
            this.unitsRemaining=0;
            this.unitsRemaining+=unitsRemaining;
        }
    }

    /**
     * This method returns a formatted report that lists the value of each instance variable.
     * @return a String that contains the value of each instance variable, cleverly formatted.
     */
    @Override
    public String toString() {
        return "\n"+itemName+". $"+unitCost+" each. There are "+unitsRemaining+" units remaining. Current credit: $"
                +currentCredit+". \nThis unit has accumulated $"+totalRevenue+" in total sales.";


    }
}
