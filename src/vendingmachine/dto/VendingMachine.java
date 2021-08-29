package vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachine {
    private Map<String, VendingItem> inventory;
    private BigDecimal userBalance;

    public VendingMachine(Map<String, VendingItem> stock, String balance) {
        this.inventory = stock;
        this.userBalance = new BigDecimal(balance);
    }

    public BigDecimal getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(BigDecimal userBalance) {
        this.userBalance = userBalance;
    }

    public Map<String, VendingItem> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, VendingItem> inventory) {
        this.inventory = inventory;
    }
}
