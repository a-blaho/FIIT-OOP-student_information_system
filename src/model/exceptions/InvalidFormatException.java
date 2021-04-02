package model.exceptions;
import model.Dialog;

/**
 * An exception class
 * Thrown when an input is in invalid format
 */
public class InvalidFormatException extends Exception {
    /**
     * Constructor
     * @param message
     */
    public InvalidFormatException(String message) {
        super(message);
        Dialog dialog = new Dialog();
        dialog.error("Zlý formát", message);
    }

}
