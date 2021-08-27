package vendingmachine.dto;

import java.math.BigDecimal;

public class VendingItem {
    private String name;
    private BigDecimal cost;
    private int quantity;

    public VendingItem(String name, BigDecimal cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }
}
