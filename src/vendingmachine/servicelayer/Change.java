package vendingmachine.servicelayer;

import java.math.BigDecimal;

// Class for calculating the change required for a specific balance
public class Change {
    private static final int GREATER_THAN = 1;
    private static final int LESS_THAN = -1;
    private static final BigDecimal PENNY = new BigDecimal("0.01");
    private static final BigDecimal NICKEL = new BigDecimal("0.05");
    private static final BigDecimal DIME = new BigDecimal("0.10");
    private static final BigDecimal QUARTER = new BigDecimal("0.25");

    public static int[] makeChange(BigDecimal amount) {
        int pennies = 0;
        int nickels = 0;
        int dimes = 0;
        int quarters = 0;

        // Calculate the number of quarters needed
        quarters = calculateNumberOfCoins(Coins.QUARTER, amount);
        amount = amount.subtract(QUARTER.multiply(new BigDecimal(quarters)));
        // Calculate the number of dimes needed
        dimes = calculateNumberOfCoins(Coins.DIME, amount);
        amount = amount.subtract(DIME.multiply(new BigDecimal(dimes)));
        // Calculate the number of nickels needed
        nickels = calculateNumberOfCoins(Coins.NICKEL, amount);
        amount = amount.subtract(NICKEL.multiply(new BigDecimal(nickels)));
        // Calculate the number of pennies needed
        pennies = calculateNumberOfCoins(Coins.PENNY, amount);
        amount = amount.subtract(PENNY.multiply(new BigDecimal(pennies)));

        return new int[] {pennies, nickels, dimes, quarters};
    }

    private static int calculateNumberOfCoins(Coins coinType, BigDecimal amount) {
        BigDecimal coinValue;
        int numberOfCoins = 0;
        switch (coinType) {
            case PENNY:
                coinValue = PENNY;
                break;
            case NICKEL:
                coinValue = NICKEL;
                break;
            case DIME:
                coinValue = DIME;
                break;
            case QUARTER:
                coinValue = QUARTER;
                break;
            default:
                coinValue = new BigDecimal("1");
        }
        while(amount.compareTo(BigDecimal.ZERO) == GREATER_THAN) {
            numberOfCoins++;
            amount = amount.subtract(coinValue);
        }
        return numberOfCoins;
    }
}
