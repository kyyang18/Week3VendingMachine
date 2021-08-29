package vendingmachine.servicelayer;

import vendingmachine.dao.*;
import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.util.Map;

public interface VendingMachineServiceLayer {
    // Add an item to the vending machine
    public void addItem(VendingItem item);

    // Make a purchase, only performing transaction if the item exists, is in stock, and the user has enough
    // money to perform the transaction
    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException, FileLoadingWritingException;

    // Returns the inventory of the vending machine
    public Map<String, VendingItem> getInventory();

    // Increases the userBalance by addition only if addition is a positive value
    public void addBalance(String addition) throws NegativeDepositException, FileLoadingWritingException;

    // Returns the VendingMachine object
    public VendingMachine getVendingMachine();

    // Log a purchase to the audit file
    public void logPurchase(String name, int quantity, String unitCost);

    // Log a deposit to the audit file
    public void logDeposit(String amount);

    // Log a withdrawal (e.g. getting change) to the audit file
    public void logWithdrawal(String amount);

    // Make change for userBalance
    // Returns an array representing the number of pennies, nickels, dimes, and quarters in the format:
    // [pennies, nickels, dimes, quarters]
    public int[] makeChange() throws FileLoadingWritingException;
}
