package vendingmachine.dao;

// Exception thrown when user tries to deposit a negative value
public class NegativeDepositException extends Exception {
    public NegativeDepositException(String msg) {
        super(msg);
    }
}
