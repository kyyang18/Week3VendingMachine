package vendingmachine.controller;

import vendingmachine.dao.FileLoadingWritingException;
import vendingmachine.servicelayer.VendingMachineServiceLayer;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImplementation;
import vendingmachine.ui.VendingMachineConsoleView;

public class VendingMachineController {
    private UserIO io = new UserIOConsoleImplementation();
    private VendingMachineConsoleView view;
    private VendingMachineServiceLayer serviceLayer;

    public VendingMachineController(VendingMachineConsoleView view, VendingMachineServiceLayer servicelayer) {
        this.view = view;
        this.serviceLayer = servicelayer;
    }

    // Method to run the vending machine
    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while(keepGoing) {
                menuSelection = view.printHomeMenuAndGetSelection(serviceLayer.getVendingMachine());
                switch(menuSelection) {
                    case 1:
                        this.makePurchase();
                        break;
                    case 2:
                        this.addBalance();
                        break;
                    case 3:
                        this.handleQuit();
                        keepGoing = false;
                        break;
                    default:
                        this.displayError("Invalid selection");
                }
            }
        } catch (Exception e) {
            this.displayError(e.getMessage());
        }
    }

    // Handles the purchasing process
    private void makePurchase() {
        try {
            String itemToBuyName = io.readNextInput("Please enter the name of the item you wish to purchase:");
            int quantity = Integer.parseInt(io.readNextInput("Please enter the quantity you wish to purchase: "));
            serviceLayer.makePurchase(itemToBuyName, quantity);
        } catch (Exception e) {
            this.displayError(e.getMessage());
        } finally {
            io.anyInputToContinue();
        }
    }

    // Handles the depositing funds process
    private void addBalance() {
        try {
            String addedBalance = io.readNextInput("Please enter how much you would like to deposit: ");
            serviceLayer.addBalance(addedBalance);
        } catch (Exception e) {
            displayError(e.getMessage());
        } finally {
            io.anyInputToContinue();
        }
    }

    // Handles the user quitting the vending machine. Gives change before terminating
    private void handleQuit() throws FileLoadingWritingException {
        io.println("Making Change...");
        int[] change = serviceLayer.makeChange();
        int pennies = change[0];
        int nickels = change[1];
        int dimes = change[2];
        int quarters = change[3];
        io.println("Change received: ");
        io.println(quarters + " quarters");
        io.println(dimes + " dimes");
        io.println(nickels + " nickels");
        io.println(pennies + " pennies");
        io.println("Thank you for shopping at the vending machine!");
        io.anyInputToContinue();
    }

    // Displays any errors to the view
    private void displayError(String errorMessage) {
        io.println("Error: " + errorMessage);
    }
}
