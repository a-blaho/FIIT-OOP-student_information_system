package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.user.Student;
import model.user.Teacher;

/**
 * Controls the Profile scene
 */
public class ProfileController extends Controller {

    @FXML
    private Text nameText;

    @FXML
    private Text surnameText;

    @FXML
    private Text roleText;

    @FXML
    private Text emailText;

    @FXML
    private Text subjectsText;

    @FXML
    private Text yearText;

    @FXML
    private Label yearLabel;

    @Override
    public void initialize() {
        super.initialize();
        nameText.setText(identity.getUser().getName());
        surnameText.setText(identity.getUser().getSurname());
        if(identity.getUser() instanceof Student) {
            Student student = (Student) identity.getUser();
            roleText.setText("Profil študenta");
            subjectsText.setText(student.printSubjects());
            yearLabel.setText("Ročník:");
            yearText.setText(Integer.toString(student.getYear()));
        }
        else {
            Teacher teacher = (Teacher) identity.getUser();
            roleText.setText("Profil učiteľa");
            yearLabel.setText("Titul:");
            yearText.setText(teacher.getTitle());
            subjectsText.setText(teacher.getSubject().getName());
        }

        emailText.setText(identity.getUser().getUsername() + "@sis.sk");

    }
}
