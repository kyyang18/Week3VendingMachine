package vendingmachine.dao;

public class NegativeDepositException extends Exception {
    public NegativeDepositException(String msg) {
        super(msg);
    }
}
