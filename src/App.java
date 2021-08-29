import vendingmachine.controller.VendingMachineController;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoFileImplementation;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImplementation;
import vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImplementation();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoFileImplementation();
        VendingMachineController controller = new VendingMachineController(view, dao);
        controller.run();
    }
}
