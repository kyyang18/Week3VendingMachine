package vendingmachine.ui;

import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

public class VendingMachineConsoleView {
    private UserIO io;

    public VendingMachineConsoleView(UserIO io) {
        this.io = io;
    }

    // Prints the main menu for the vending machine, and returns the user's selection as a integer
    public int printHomeMenuAndGetSelection(VendingMachine vendingMachine) {
        io.println("=== Vending Machine ===");
        io.println("Current balance: " + vendingMachine.getUserBalance());
        this.printVendingMachineInventory(vendingMachine);
        io.println("1. Make Purchase");
        io.println("2. Add balance");
        io.println("3. Quit");
        return io.readIntSelection("Please select from above options ", 1, 2);
    }

    // Prints out the stock of the vending machine
    public void printVendingMachineInventory(VendingMachine vendingMachine) {
        io.println(String.format("|%-20s| |%-20s| |%-20s|", "Name", "Price", "Quantity Available"));
        for (VendingItem item : vendingMachine.getInventory().values()) {
            this.printVendingItem(item);
        }
    }

    // Prints out the details of a VendingMachineItem
    public void printVendingItem(VendingItem item) {
        io.println(String.format("|%-20s| |%-20s| |%-20s|", item.getName(), item.getCost(), item.getQuantity()));
    }

}
