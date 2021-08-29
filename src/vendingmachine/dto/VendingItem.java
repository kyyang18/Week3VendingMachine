package vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VendingItem {
    private String name;
    private BigDecimal cost;
    private int quantity;

    public VendingItem(String name, String cost, int quantity) {
        this.name = name;
        this.cost = new BigDecimal(cost).setScale(2, RoundingMode.HALF_UP);
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
