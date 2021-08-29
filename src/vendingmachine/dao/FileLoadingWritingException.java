package vendingmachine.dao;

// Exception thrown when an error occurs while reading/writing to a file.
public class FileLoadingWritingException extends Exception{
    public FileLoadingWritingException(String message){
        super(message);
    }
}
