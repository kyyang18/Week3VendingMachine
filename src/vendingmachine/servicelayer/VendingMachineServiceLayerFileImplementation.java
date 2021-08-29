package vendingmachine.servicelayer;

import vendingmachine.dao.*;
import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class VendingMachineServiceLayerFileImplementation implements VendingMachineServiceLayer {
    private VendingMachineDao dao;
    private AuditLog auditLog;
    private LocalDateTime ld;

    private final int GREATER_THAN = 1;
    private final int LESS_THAN = -1;
    private final String DELIMITER = " : ";

    public VendingMachineServiceLayerFileImplementation() throws FileLoadingWritingException, IOException {
        this.dao = new VendingMachineDaoFileImplementation();
        this.auditLog = new AuditLog();
    }

    @Override
    public void addItem(VendingItem item) {
        this.dao.addItem(item);
    }

    @Override
    public void makePurchase(String itemName, int quantity) throws ItemNotFoundException,
            ItemOutOfStockException, InsufficientFundsException, FileLoadingWritingException {
        VendingItem itemToBuy = dao.getInventory().get(itemName);
        // Check if item exists in stock
        if (itemToBuy == null) {
            throw new ItemNotFoundException("Item with that name was not found in the vending machine's inventory");
        }
        // Check if enough stock exists to fulfill the order
        else if (itemToBuy.getQuantity() == 0) {
            throw new ItemOutOfStockException("Item is completely out of stock");
        }
        else if (itemToBuy.getQuantity() - quantity < 0) {
            throw new ItemNotFoundException("Not enough quantity to fulfill transaction - transaction not processed");
        } else {
            BigDecimal cost = itemToBuy.getCost().multiply(new BigDecimal(quantity));
            // Check if user has sufficient funds to complete transactions
            if (cost.compareTo(dao.getVendingMachine().getUserBalance()) == GREATER_THAN) {
                throw new InsufficientFundsException("Insufficient funds to perform this transaction - transaction not " +
                        "processed ");
            } else {
                // Transaction may be performed
                dao.makePurchase(itemName, quantity);
                this.logPurchase(itemName, quantity, itemToBuy.getCost().toString());
            }
        }
    }

    @Override
    public Map<String, VendingItem> getInventory() {
        return dao.getInventory();
    }

    @Override
    public void addBalance(String addition) throws NegativeDepositException, FileLoadingWritingException {
        BigDecimal deposit = new BigDecimal(addition);
        if (deposit.compareTo(BigDecimal.ZERO) == LESS_THAN) {
            throw new NegativeDepositException("Deposit cannot be a negative value");
        }
        logDeposit(addition);
        dao.addBalance(addition);
    }

    @Override
    public VendingMachine getVendingMachine() {
        return dao.getVendingMachine();
    }

    @Override
    public void logPurchase(String name, int quantity, String unitCost) {
        ld = LocalDateTime.now();
        String logEntry = ld.toString() + DELIMITER;
        logEntry += "PURCHASE" + DELIMITER;
        logEntry += name + DELIMITER;
        logEntry += quantity + " units" + DELIMITER;
        logEntry += "$" + unitCost;
        auditLog.writeLogEntry(logEntry);
    }

    @Override
    public void logDeposit(String amount) {
        ld = LocalDateTime.now();
        String logEntry = ld.toString() + DELIMITER;
        logEntry += "DEPOSIT: $" + amount;
        auditLog.writeLogEntry(logEntry);
    }


    @Override
    public int[] makeChange() throws FileLoadingWritingException{
        int[] change = Change.makeChange(dao.getVendingMachine().getUserBalance());
        this.withdrawAllUserFunds();
        return change;
    }

    @Override
    public void logWithdrawal(String amount) {
        ld = LocalDateTime.now();
        String logEntry = ld.toString() + DELIMITER;
        logEntry += "WITHDRAWAL: $" + amount;
        auditLog.writeLogEntry(logEntry);
    }

    // Set userBalance to zero (i.e. withdrawing all of the funds)
    private void withdrawAllUserFunds() throws FileLoadingWritingException {
        this.logWithdrawal(dao.getVendingMachine().getUserBalance().toString());
        dao.setBalance("0.00");
    }






}
