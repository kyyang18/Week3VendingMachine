package vendingmachine.dao;

// Exception thrown when user tries to purchase a quantity of an item that exceeds the amount available
public class ItemOutOfStockException extends Exception{
    public ItemOutOfStockException(String message) {
        super(message);
    }
}
