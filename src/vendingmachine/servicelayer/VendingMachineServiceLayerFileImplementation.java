package vendingmachine.servicelayer;

import vendingmachine.dao.*;
import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineServiceLayerFileImplementation implements VendingMachineServiceLayer {
    private VendingMachineDao dao;
    public VendingMachineServiceLayerFileImplementation() throws FileLoadingWritingException {
        this.dao = new VendingMachineDaoFileImplementation();
    }

    @Override
    public void addItem(VendingItem item) {
        this.dao.addItem(item);
    }

    @Override
    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException {
        VendingItem itemToBuy = dao.getInventory().get(itemName);
        // Check if item exists in stock
        if (itemToBuy == null) {
            throw new ItemNotFoundException("Item with that name was not found in the vending machine's inventory");
        }
        // Check if enough stock exists to fulfill the order
        if (itemToBuy.getQuantity() == 0) {
            throw new ItemOutOfStockException("Item is completely out of stock");
        }
        if (itemToBuy.getQuantity() - quantity < 0) {
            throw new ItemNotFoundException("Not enough quantity to fulfill transaction - transaction not processed");
        }
        BigDecimal cost = itemToBuy.getCost().multiply(new BigDecimal(quantity));
        // Check if user has sufficient funds to complete transactions
        if (cost.compareTo(dao.getVendingMachine().getUserBalance()) == -1) {
            throw new InsufficientFundsException("Insufficient funds to perform this transaction - transaction not " +
                    "processed ");
        }
        // Transaction may be performed
        dao.makePurchase(itemName, quantity);
    }

    @Override
    public Map<String, VendingItem> getInventory() {
        return dao.getInventory();
    }

    @Override
    public void addBalance(String addition) throws NegativeDepositException {
        BigDecimal deposit = new BigDecimal(addition);
        if (deposit.compareTo(BigDecimal.ZERO) == -1) {
            throw new NegativeDepositException("Deposit cannot be a negative value");
        }
        dao.addBalance(addition);
    }

    @Override
    public VendingMachine getVendingMachine() {
        return dao.getVendingMachine();
    }




}
