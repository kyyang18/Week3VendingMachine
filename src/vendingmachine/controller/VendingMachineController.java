package vendingmachine.controller;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImplementation;
import vendingmachine.ui.VendingMachineView;

public class VendingMachineController {
    private UserIO io = new UserIOConsoleImplementation();
    private VendingMachineView view;
    private VendingMachineDao dao;

    public VendingMachineController(VendingMachineView view, VendingMachineDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while(keepGoing) {
                menuSelection = view.printHomeMenuAndGetSelection(dao.getVendingMachine());
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
