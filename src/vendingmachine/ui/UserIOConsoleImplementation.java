package vendingmachine.ui;

import java.util.Scanner;

public class UserIOConsoleImplementation implements UserIO{
    private final Scanner input = new Scanner(System.in);

    // Method that prints a line to the console
    @Override
    public void println(String msg) {
        System.out.println(msg);
    }

    // Method that prints a line to the console and then waits for user input, returning user input
    @Override
    public String readNextInput(String msg) {
        System.out.println(msg);
        return input.nextLine();
    }

    // Method that waits for a user to enter an integer within a min/max bound, returning user input
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

    // Method that pauses execution until the user provides an input
    @Override
    public void anyInputToContinue() {
        this.readNextInput("Enter any input to continue...");
    }

}
