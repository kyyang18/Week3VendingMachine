package vendingmachine.servicelayer;

import vendingmachine.dao.InsufficientFundsException;
import vendingmachine.dao.ItemNotFoundException;
import vendingmachine.dao.ItemOutOfStockException;
import vendingmachine.dao.NegativeDepositException;
import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.util.Map;

public interface VendingMachineServiceLayer {
    public void addItem(VendingItem item);

    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException;

    public Map<String, VendingItem> getInventory();

    public void addBalance(String addition) throws NegativeDepositException;

    public VendingMachine getVendingMachine();

}
