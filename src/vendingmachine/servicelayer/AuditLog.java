package vendingmachine.servicelayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditLog {
    private final String AUDIT_FILE_NAME = "auditlog.txt";

    private PrintWriter printer;

    public AuditLog() throws IOException {
        this.printer = new PrintWriter(new FileWriter(AUDIT_FILE_NAME, true));
    }

    public void writeLogEntry(String logEntry) {
        printer.println(logEntry);
        printer.flush();
    }
}
