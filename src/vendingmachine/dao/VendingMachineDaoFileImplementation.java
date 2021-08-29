package vendingmachine.dao;

import vendingmachine.dto.VendingItem;
import vendingmachine.dto.VendingMachine;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImplementation implements VendingMachineDao {
    private static final String DELIMITER = "::";
    private static final String FILE_NAME = "vendingmachine_inventory.txt";

    private VendingMachine vendingMachine;

    public VendingMachineDaoFileImplementation() throws FileLoadingWritingException{
        this.loadVendingMachine();
    }

    @Override
    public void addItem(VendingItem item) {
        this.vendingMachine.getInventory().put(item.getName(), item);
    }

    @Override
    public void makePurchase(String itemName, int quantity) {
        VendingItem itemToBuy = vendingMachine.getInventory().get(itemName);
        // Reduce quantity from the vending machine
        itemToBuy.setQuantity(itemToBuy.getQuantity() - quantity);
        // Deduct cost from user balance
        BigDecimal cost = itemToBuy.getCost().multiply(new BigDecimal(quantity));
        vendingMachine.setUserBalance(vendingMachine.getUserBalance().subtract(cost));
    }

    @Override
    public Map<String, VendingItem> getInventory() {
        return this.vendingMachine.getInventory();
    }

    @Override
    public void addBalance(String addition) {
        this.vendingMachine.setUserBalance(this.vendingMachine.getUserBalance().add(new BigDecimal(addition)));
    }

    public VendingMachine getVendingMachine() {
        return this.vendingMachine;
    }

    public void loadVendingMachine() throws FileLoadingWritingException{
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

            // First line contains the balance
            String balanceString = scanner.nextLine();

            // Get the map containing the vending machine's inventory
            String currentLine;
            Map<String, VendingItem> stock = new HashMap<>();
            while(scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                VendingItem newVendingItem = unmarshallVendingItem(currentLine);
                stock.put(newVendingItem.getName(), newVendingItem);
            }

            this.vendingMachine = new VendingMachine(stock, balanceString);
        } catch (FileNotFoundException e) {
            throw new FileLoadingWritingException("Error loading vending machine data from file");
        }
    }

    public VendingItem unmarshallVendingItem(String input) {
        // Split input string
        // Format:
        // [NAME, COST, QUANTITY]
        String[] inputArray = input.split(DELIMITER);
        String name = inputArray[0];
        String cost = inputArray[1];
        int quantity = Integer.parseInt(inputArray[2]);
        return new VendingItem(name, cost, quantity);
    }

    public void saveVendingMachine() throws FileLoadingWritingException {
        PrintWriter printer;

        try {
            printer = new PrintWriter(new FileWriter(FILE_NAME));
            // Write the balance in the first line of the file
            printer.println(this.vendingMachine.getUserBalance().toString());
            printer.flush();

            // Write the inventory to the file
            for (VendingItem item : this.getAllVendingItems()) {
                printer.println(this.marshallVendingItem(item));
                printer.flush();
            }
        } catch (IOException e) {
            throw new FileLoadingWritingException("Error saving vending machine data to file");
        }
    }

    public String marshallVendingItem(VendingItem item) {
        String itemAsText = item.getName() + DELIMITER;
        itemAsText += item.getCost().toString() + DELIMITER;
        itemAsText += String.valueOf(item.getQuantity());
        return itemAsText;
    }

    public List<VendingItem> getAllVendingItems() {
        return new ArrayList<>(this.vendingMachine.getInventory().values());
    }

}
