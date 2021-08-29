import vendingmachine.controller.VendingMachineController;
import vendingmachine.servicelayer.VendingMachineServiceLayer;
import vendingmachine.servicelayer.VendingMachineServiceLayerFileImplementation;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImplementation;
import vendingmachine.ui.VendingMachineConsoleView;

public class App {
    public static void main(String[] args) {
        try {
            UserIO io = new UserIOConsoleImplementation();
            VendingMachineConsoleView view = new VendingMachineConsoleView(io);
            VendingMachineServiceLayer serviceLayer = new VendingMachineServiceLayerFileImplementation();
            VendingMachineController controller = new VendingMachineController(view, serviceLayer);
            controller.run();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
