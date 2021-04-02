package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import model.Grade;
import model.user.Student;
import model.Subject;

/**
 * Controls the Grades scene
 */
public class GradesController extends Controller {

    @FXML
    private TableView<Grade> gradeTable;

    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Button confirmButton;

    @FXML
    private TableColumn<Grade, String> categoryColumn;

    @FXML
    private TableColumn<Grade, String> gradeColumn;

    @FXML
    private Text averageText;

    @Override
    public void initialize() {
        super.initialize();
        Student student = (Student) identity.getUser();
        for(Subject s : student.getSubjects()) {
            choiceBox.getItems().add(s.getName());
        }
        confirmButton.setOnAction((e) -> {
            printGrades(choiceBox.getValue(), student);
            student.removeUpdatedMessage(choiceBox.getValue());
        });
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());
        gradeTable.setPlaceholder(new Label("Žiadne známky"));
        gradeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    /**
     * Prints the grades into TableView
     * @param o Subject name
     * @param student
     */
    private void printGrades (Object o, Student student) {
        String subjectName = (String) o;
        ObservableList<Grade> grades = FXCollections.observableArrayList();
        for(Grade grade : student.getGrades()) {
            if(grade.getSubject().getName().equals(subjectName)) {
                grades.add(grade);
            }
        }
        gradeTable.setItems(grades);
        averageText.setText(Double.toString(student.average(subjectName)));
    }
}


