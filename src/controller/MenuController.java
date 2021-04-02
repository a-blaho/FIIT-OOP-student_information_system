package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.Dialog;
import model.user.Student;
import model.user.Teacher;

import java.io.IOException;

/**
 * Controls the Menu scene
 */
public class MenuController extends Controller {

    @FXML
    private AnchorPane subPane;

    @FXML
    private Text loggedAsText;

    @FXML
    private Button logoutButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button gradesButton;

    @FXML
    private Button subjectButton;

    @FXML
    private Text newMessage;

    @FXML
    private Button homeButton;

    private Dialog dialog = new Dialog();

    @Override
    public void initialize() {
        super.initialize();
        logoutButton.setOnAction((e) -> {
            try {
                logOut();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        loggedAsText.setText("Prihlásený ako " + identity.getUsername());
        profileButton.setOnAction((e) -> {
          try {
              switchScene(subPane, "profile");
          } catch (IOException ex) {
              ex.printStackTrace();
          }
        });
        if(identity.getUser() instanceof Student) {
            gradesButton.setText("Známky");
            subjectButton.visibleProperty().setValue(false);
            Student student = (Student) identity.getUser();
            if(student.getUpdatedSubjectSize() != 0) {
                newMessage.setText("Nastali zmeny v predmete: " + student.firstUpdatedMessage());
            }
        }
        else if(identity.getUser() instanceof Teacher) {
            gradesButton.setText("Moji študenti");
            subjectButton.visibleProperty().setValue(true);
            subjectButton.setOnAction((e) -> {
                try {
                    switchScene(subPane, "subject");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        gradesButton.setOnAction((e) -> {
            try {
                switchScene(subPane, identity.getUser().getGradesPath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        homeButton.setOnAction((e) -> {
            try {
                switchScene(pane, "menu");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Switches back to the login scene and destroys the identity
     * @throws IOException if path to the next scene wasn't found
     */
    private void logOut() throws IOException {
        if(dialog.confirmation("Odhlásenie", "Naozaj sa chcete odhlásiť?")) {
            identity.destroy();
            this.switchScene(pane, "login");
        }
    }
}
