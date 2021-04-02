package model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Class which handles all alert dialogs
 */
public class Dialog {
    /**
     * Error dialog
     * @param header
     * @param content
     */
    public void error(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    /**
     * Confirmation dialog with an OK and Cancel buttons
     * @param header
     * @param content
     * @return True if OK was pressed, false otherwise
     */
    public boolean confirmation(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdenie");
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    /**
     * Dialog with a text field
     * @param header
     * @param content
     * @return Text in the text field
     */
    public String textInput(String header, String content) {
        TextInputDialog alert = new TextInputDialog();
        alert.setTitle("Vyplnte pole");
        alert.setHeaderText(header);
        alert.setContentText(content);
        Optional<String>input = alert.showAndWait();
        if(input.isPresent()) {
            return input.get();
        }
        else {
            return null;
        }
    }

    /**
     * Information dialog
     * @param header
     * @param content
     */
    public void information(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Oznam");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
