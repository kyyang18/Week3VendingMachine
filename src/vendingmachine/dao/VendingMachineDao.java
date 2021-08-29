package vendingmachine.dao;

import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.util.Map;

public interface VendingMachineDao {
    // Add an item to the vending machine's inventory
    public void addItem(VendingItem item);

    // Make a purchase
    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException;

    public Map<String, VendingItem> getInventory();

    public void addBalance(String addition);

    public VendingMachine getVendingMachine();

}
