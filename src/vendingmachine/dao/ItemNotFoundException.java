package vendingmachine.dao;

// Exception thrown when user selects an item that is not in the vending machine
public class ItemNotFoundException extends Exception{
    public ItemNotFoundException(String message) {
        super(message);
    }
}
