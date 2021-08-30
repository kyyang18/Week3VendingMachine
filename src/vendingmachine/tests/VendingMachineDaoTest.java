package vendingmachine.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoFileImplementation;
import vendingmachine.dto.VendingItem;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class VendingMachineDaoTest {

    private VendingMachineDao dao;

    @BeforeEach
    // Setup test objects
    void setup() {
        try {
            dao = new VendingMachineDaoFileImplementation("vendingmachinedao_test.txt");
            VendingItem testItem1 = new VendingItem("Oh Henry", "1.50", 12);
            VendingItem testItem2 = new VendingItem("Doritos", "1.75", 22);
            dao.addItem(testItem1);
            dao.addItem(testItem2);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testAddItem() {
        try {
            int inventorySize = dao.getVendingMachine().getInventory().size();
            VendingItem testItem3 = new VendingItem("Lay's", "2.25", 10);
            dao.addItem(testItem3);
            assertEquals(inventorySize, dao.getVendingMachine().getInventory().size());

        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testDeposit() {
        try {
            BigDecimal balance = dao.getVendingMachine().getUserBalance();
            dao.addBalance("10.00");
            assertEquals(dao.getVendingMachine().getUserBalance(), balance.add(new BigDecimal("10.00")));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testPurchase() {
        try {
            BigDecimal balance = dao.getVendingMachine().getUserBalance();
            dao.makePurchase("Doritos", 1);
            assertEquals(dao.getVendingMachine().getUserBalance(), balance.subtract(new BigDecimal("1.75")));
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }



}
