package vendingmachine.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.dao.InsufficientFundsException;
import vendingmachine.dao.ItemNotFoundException;
import vendingmachine.dao.ItemOutOfStockException;
import vendingmachine.dao.NegativeDepositException;
import vendingmachine.dto.VendingItem;
import vendingmachine.servicelayer.VendingMachineServiceLayer;
import vendingmachine.servicelayer.VendingMachineServiceLayerFileImplementation;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VendingMachineServiceLayerTest {
    private VendingMachineServiceLayer serviceLayer;

    @BeforeEach
    // Setup test objects
    public void setup() {
        try {
            serviceLayer = new VendingMachineServiceLayerFileImplementation("servicelayer_test.txt");
            VendingItem testItem1 = new VendingItem("Oh Henry", "1.50", 12);
            VendingItem testItem2 = new VendingItem("Doritos", "1.75", 22);
            serviceLayer.addItem(testItem1);
            serviceLayer.addItem(testItem2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testAddItem() {
        try {
            int inventorySize = serviceLayer.getVendingMachine().getInventory().size();
            VendingItem testItem3 = new VendingItem("Lay's", "2.25", 10);
            serviceLayer.addItem(testItem3);
            assertEquals(inventorySize, serviceLayer.getVendingMachine().getInventory().size());

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testDepositSuccessful() {
        try {
            BigDecimal balance = serviceLayer.getVendingMachine().getUserBalance();
            serviceLayer.addBalance("10.00");
            assertEquals(serviceLayer.getVendingMachine().getUserBalance(), balance.add(new BigDecimal("10.00")));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testDepositNegativeValue() {
        try {
            BigDecimal balance = serviceLayer.getVendingMachine().getUserBalance();
            serviceLayer.addBalance("-10.00");
            fail("NegativeDepositException should have been thrown");
        } catch (NegativeDepositException e) {
            //pass
        } catch (Exception e) {
            fail("NegativeDepositException should have been thrown");
        }
    }

    @Test
    void testPurchaseSuccessful() {
        try {
            BigDecimal balance = serviceLayer.getVendingMachine().getUserBalance();
            serviceLayer.makePurchase("Doritos", 1);
            assertEquals(serviceLayer.getVendingMachine().getUserBalance(), balance.subtract(new BigDecimal("1.75")));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testPurchaseFailedNoStock() {
        try {
            //Deposit funds to make transaction
            serviceLayer.addBalance("100.00");
            serviceLayer.makePurchase("Doritos", 25);
            fail("ItemOutOfStockException should have been thrown");
        } catch (ItemOutOfStockException e) {
            // pass
        } catch (Exception e) {
            fail("ItemOutOfStockException should have been thrown, instead got " + e.getMessage());
        }
    }

    @Test
    void testPurchaseFailedInsufficientFunds() {
        try {
            BigDecimal balance = serviceLayer.getVendingMachine().getUserBalance();
            // Create a new item that is too expensive
            VendingItem expensiveItem = new VendingItem("Luxury Chips",
                    (new BigDecimal("5").multiply(balance)).toString()
                    , 10);
            serviceLayer.addItem(expensiveItem);
            serviceLayer.makePurchase("Luxury Chips", 1);
            fail("InsufficientFundsException should have been thrown");
        } catch (InsufficientFundsException e) {
            // pass
        } catch (Exception e) {
            fail("InsufficientFundsException should have been thrown");
        }
    }

    @Test
    void testPurchaseFailedItemDoesNotExist() {
        try {
            BigDecimal balance = serviceLayer.getVendingMachine().getUserBalance();
            serviceLayer.makePurchase("Fake Item", 1);
            fail("ItemNotFoundException should have been thrown");
        } catch (ItemNotFoundException e) {
            // pass
        } catch (Exception e) {
            fail("ItemNotFoundException should have been thrown");
        }
    }
}
