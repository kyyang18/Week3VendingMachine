package vendingmachine.ui;

import java.util.Scanner;

public class UserIOConsoleImplementation implements UserIO{
    private final Scanner input = new Scanner(System.in);

    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    @Override
    public String readNextInput(String msg) {
        System.out.println(msg);
        return input.nextLine();
    }

    @Override
    public int readIntSelection(String msg, int min, int max) {
        int result;
        while(true) {
            try {
                String userSelection = this.readNextInput(msg);
                result = Integer.parseInt(userSelection);
                if (result >= min || result <= max) {
                    return result;
                } else {
                    System.out.println("Invalid selection. Please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer");
            }
        }
    }

}
