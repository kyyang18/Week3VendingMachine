package vendingmachine.dao;

// Exception thrown when user does not have enough funds to perform transaction
public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
