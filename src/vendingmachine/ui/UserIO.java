package vendingmachine.ui;

public interface UserIO {
    // Method that prints a line
    void println(String msg);

    // Method that prints a line and then waits for user input
    String readNextInput(String msg);

    // Method that waits for a user to enter an integer within a min/max bound
    int readIntSelection(String msg, int min, int max);

    // Method that pauses execution until the user provides an input
    public void anyInputToContinue();
}
