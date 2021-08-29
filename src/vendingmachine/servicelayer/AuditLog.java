package vendingmachine.servicelayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Class for logging events to a text file
public class AuditLog {
    private final String AUDIT_FILE_NAME = "auditlog.txt";

    private PrintWriter printer;

    // Constructor initializes printer in 'append' mode
    public AuditLog() throws IOException {
        this.printer = new PrintWriter(new FileWriter(AUDIT_FILE_NAME, true));
    }

    // Append a new line to the audit file containing logEntry
    public void writeLogEntry(String logEntry) {
        printer.println(logEntry);
        printer.flush();
    }
}
