package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Category;
import model.Dialog;
import model.Grade;
import model.exceptions.InvalidFormatException;
import model.exceptions.ObjectNotSelected;
import model.user.Student;
import model.user.Teacher;
import model.user.User;

import static model.exceptions.ObjectNotSelected.checkForNull;
import static model.user.User.users;

/**
 * Controls the My students scene
 */
public class MyStudentsController extends Controller {

    @FXML
    TableView<Student> studentsTable;

    @FXML
    TableColumn<Student, String> studentsColumn;

    @FXML
    TableColumn<Student, String> averagesColumn;

    @FXML
    ChoiceBox categoriesChoiceBox;

    @FXML
    Button addGradeButton;

    @FXML
    TextField gradeText;

    @FXML
    TableView<Grade> gradesTable;

    @FXML
    TableColumn<Grade, String> categoryColumn;

    @FXML
    TableColumn<Grade, String> gradeColumn;

    @FXML
    Button showGradesButton;

    @FXML
    Button deleteGradeButton;

    @FXML
    CheckBox finalGradeCheck;

    Dialog dialog = new Dialog();
    Student selectedStudent = null;     //remembers the last selected student from the TableView

    @Override
    public void initialize() {
        super.initialize();
        Teacher teacher = (Teacher) identity.getUser();

        studentsTable.setPlaceholder(new Label("Žiadne zadania"));
        studentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        studentsColumn.setCellValueFactory(cellData -> cellData.getValue().getFullName());
        averagesColumn.setCellValueFactory(cellData -> cellData.getValue().averageProperty(teacher.getSubject().getName()));
        refreshTable(teacher);

        gradesTable.setPlaceholder(new Label("Žiadny zvolený študent"));
        studentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().getGradeProperty());
        showGradesButton.setOnAction((e) -> {
            showGrades(teacher);
        });

        refreshChoiceBox(teacher);

        addGradeButton.setOnAction((e) -> {
            addGrade(teacher);
        });

        deleteGradeButton.setOnAction((e) -> {
            deleteGrade(teacher);
        });
    }

    /**
     * Reloads all the data in the student TableView
     * @param teacher
     */
    private void refreshTable(Teacher teacher) {
        studentsTable.getItems().clear();
        ObservableList<Student> studentsList = FXCollections.observableArrayList();
        for(User s : users) {
            if(s instanceof Student) {
                Student student = (Student) s;
                if(student.getSubjects().contains(teacher.getSubject())) {
                    studentsList.add(student);
                }
            }
        }
        studentsTable.setItems(studentsList);
    }

    /**
     * Removes the selected grade
     * @param teacher
     */
    private void deleteGrade(Teacher teacher) {
        TableView.TableViewSelectionModel selectionModel = gradesTable.getSelectionModel();
        Grade selectedGrade = (Grade) selectionModel.getSelectedItem();
        if(selectedGrade == null || selectedStudent == null) {
            dialog.error("Chyba pri mazaní známky", "Nie je zvolený študent alebo známka");
            return;
        }
        if(dialog.confirmation("Chystáte sa vymazať známku", "Naozaj chcete vymazať známku?")) {
            teacher.removeGrade(selectedStudent, selectedGrade);
            showGrades(teacher);
        }
        else
            return;
        return;
    }

    /**
     * Shows student's grades in the TableView
     * @param teacher
     */
    private void showGrades(Teacher teacher) {
        Student previous = selectedStudent;
        TableView.TableViewSelectionModel selectionModel = studentsTable.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectedStudent = (Student) selectionModel.getSelectedItems().get(0);       //choose a student
        if(selectedStudent == null)
            selectedStudent = previous;
        ObservableList<Grade> gradesList = FXCollections.observableArrayList();         //create an ArrayList for their grades
        for(User s : users)                                                             //find the correct student
            if(s instanceof Student) {
                Student student = (Student) s;
                if(student.getUsername().equals(selectedStudent.getUsername())) {
                    for (Grade g : student.getGrades()) {
                        if(g.getSubject().equals(teacher.getSubject())) {
                            gradesList.add(g);
                        }
                    }
                }
            }
        if(gradesList.size() == 0) {
            gradesTable.setPlaceholder(new Label("Študent ešte nemá žiadne známky"));
        }

        refreshTable(teacher);
        gradesTable.setItems(gradesList);
    }

    /**
     * Reloads all the data in ChoiceBox
     * @param teacher
     */
    public void refreshChoiceBox(Teacher teacher) {
        categoriesChoiceBox.getItems().clear();
        for(Category c :  teacher.getSubject().getCategories()) {
            categoriesChoiceBox.getItems().add(c.getCategory());
        }
    }

    /**
     * Checks if the grade is in correct format
     * @param grade
     * @throws InvalidFormatException if grade isn't from the interval (1,5)
     */
    static void validateGrade(String grade) throws InvalidFormatException{
        if(grade.equals("1") || grade.equals("2") || grade.equals("3") || grade.equals("4") || grade.equals("5"))
            return;
        else
            throw new InvalidFormatException("Známka je v nesprávnom formáte") ;
    }

    /**
     * Adds grade to a student
     * @param teacher
     */
    public void addGrade(Teacher teacher) {
        TableView.TableViewSelectionModel selectionModel = studentsTable.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        selectedStudent = (Student) selectionModel.getSelectedItems().get(0);
        try {
            validateGrade(gradeText.getText());
        } catch (InvalidFormatException e) {
            return;
        }
        try {
            checkForNull(categoriesChoiceBox.getValue(), "Nezvolená kategória");
            checkForNull(selectedStudent, "Nezvolený študent");
        } catch (ObjectNotSelected e) {
            return;
        }
        Category category = new Category("null");
        for(Category c : teacher.getSubject().getCategories()) {
            if(c.getCategory().equals(categoriesChoiceBox.getValue().toString())) {
                category = c;
            }
        }
        boolean finalGrade = finalGradeCheck.isSelected();
        teacher.addGrade(selectedStudent, teacher.getSubject(), Integer.parseInt(gradeText.getText()), category, finalGrade);
        refreshTable(teacher);
        dialog.information(null, "Známka úspešne zapísaná");
        showGrades(teacher);
    }
}
