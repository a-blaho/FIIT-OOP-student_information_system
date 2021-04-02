package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Identity;
import model.Dialog;
import model.user.User;
import static model.user.User.users;
import java.io.IOException;

/**
 * Controls the login scene
 */
public class LoginController extends Controller{

    @FXML
    private AnchorPane pane;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    private Dialog dialog = new Dialog();

    @Override
    public void initialize() {
        super.initialize();
        loginButton.defaultButtonProperty().bind(loginButton.focusedProperty());
        loginButton.setOnAction((e) -> {
            try {
                login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Verifies the username and password and logs in the user
     * @throws IOException if path to the next scene wasn't found
     */
    private void login() throws IOException {
        for(User u : users) {
            if (usernameField.getText().equals(u.getUsername()) && passwordField.getText().equals(u.getUsername())) {
                identity = Identity.getInstance(usernameField.getText());
                this.switchScene(pane, "menu");
                return;
            }
        }
        usernameField.clear();
        passwordField.clear();
        dialog.error("Problém s prihlásením", "Nesrpávne meno alebo heslo");
     }

}
