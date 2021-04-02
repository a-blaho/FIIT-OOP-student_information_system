package model.exceptions;

import model.Dialog;

/**
 * Exception class
 * Thrown when an object is not selected (in TableView or ChoiceBox)
 */
public class ObjectNotSelected extends Exception {
    /**
     * Constructor
     * @param message
     */
    public ObjectNotSelected(String message) {
        super(message);
        Dialog dialog = new Dialog();
        dialog.error("Nastala chyba", message);
    }

    /**
     * Checks if the object returns null
     * @param o
     * @param message
     * @throws ObjectNotSelected if the object is not selected
     */
    public static void checkForNull(Object o, String message) throws ObjectNotSelected {
        if(o == null)
            throw new ObjectNotSelected(message);
        else
            return;
    }
}
