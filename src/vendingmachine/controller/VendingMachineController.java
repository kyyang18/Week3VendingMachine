package vendingmachine.controller;

import vendingmachine.servicelayer.VendingMachineServiceLayer;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImplementation;
import vendingmachine.ui.VendingMachineView;

public class VendingMachineController {
    private UserIO io = new UserIOConsoleImplementation();
    private VendingMachineView view;
    private VendingMachineServiceLayer serviceLayer;

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer servicelayer) {
        this.view = view;
        this.serviceLayer = servicelayer;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while(keepGoing) {
                menuSelection = view.printHomeMenuAndGetSelection(serviceLayer.getVendingMachine());
                switch(menuSelection) {
                    default:
                        this.printInvalidSelection();
                }
            }
        } catch (Exception e) {

        }
    }

    private void printInvalidSelection() {
        io.println("Invalid selection");
    }
}
