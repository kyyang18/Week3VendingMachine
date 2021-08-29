package vendingmachine.servicelayer;

import vendingmachine.dao.*;
import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.util.Map;

public interface VendingMachineServiceLayer {
    public void addItem(VendingItem item);

    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException, FileLoadingWritingException;

    public Map<String, VendingItem> getInventory();

    public void addBalance(String addition) throws NegativeDepositException, FileLoadingWritingException;

    public VendingMachine getVendingMachine();

    public void logPurchase(String name, int quantity, String unitCost);

    public void logDeposit(String amount);

    public void logWithdrawal(String amount);

    public int[] makeChange() throws FileLoadingWritingException;
}
