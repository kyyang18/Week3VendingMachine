package vendingmachine.dao;

import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.util.Map;

public interface VendingMachineDao {
    // Add an item to the vending machine's inventory
    public void addItem(VendingItem item);

    // Make a purchase
    public void makePurchase(String itemName, int quantity) throws FileLoadingWritingException;

    // Returns the inventory of the vending machine
    public Map<String, VendingItem> getInventory();

    // Increases the userBalance by addition
    public void addBalance(String addition) throws FileLoadingWritingException;

    // Returns the VendingMachine object
    public VendingMachine getVendingMachine();

    // Sets userBalance to a specific value
    public void setBalance(String balance) throws FileLoadingWritingException;

}
