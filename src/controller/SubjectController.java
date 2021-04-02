package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import model.*;
import model.exceptions.ObjectNotSelected;
import model.user.Teacher;

import static model.exceptions.ObjectNotSelected.checkForNull;
import static model.user.User.users;

/**
 * Controls the Subject scene
 */
public class SubjectController extends Controller{

    @FXML
    Text title;

    @FXML
    TableView<Category> categoryTable;

    @FXML
    TableColumn<Category, String> categoryColumn;

    @FXML
    Button addCategoryButton;

    @FXML
    Button removeCategoryButton;

    @FXML
    Text studentsCount;

    Dialog dialog = new Dialog();

    @Override
    public void initialize() {
        super.initialize();
        Teacher teacher = (Teacher) identity.getUser();
        categoryTable.setPlaceholder(new Label("Žiadne zadania"));
        categoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        addCategoryButton.setOnAction((e) -> {
            addCategory(teacher);
        });
        removeCategoryButton.setOnAction((e) -> {
            removeCategory(teacher);
        });
        refreshTable(teacher);

        studentsCount.setText(teacher.studentsCount());
        title.setText(teacher.getSubject().getName());
    }

    /**
     * Removes a category
     * @param teacher
     */
    public void removeCategory(Teacher teacher) {
        TableView.TableViewSelectionModel selectionModel = categoryTable.getSelectionModel();
        Category selectedCategory = (Category) selectionModel.getSelectedItem();
        try {
            checkForNull(selectedCategory, "Nezvolená kategória");
        } catch (ObjectNotSelected e) {
            return;
        }
        if(dialog.confirmation("Chystáte sa vymazať kategóriu", "Naozaj chcete vymazať kategóriu?"))
            teacher.removeCategory(selectedCategory);
        refreshTable(teacher);
    }

    /**
     * Reloads all the data in the TableView
     * @param teacher
     */
    public void refreshTable(Teacher teacher) {
        categoryTable.getItems().clear();
        ObservableList<Category> categoryList = FXCollections.observableArrayList();
        for(Category c : teacher.getSubject().getCategories()) {
            categoryList.add(c);
        }
        categoryTable.setItems(categoryList);
    }

    /**
     * Adds new category to a subject
     * @param teacher
     */
    public void addCategory(Teacher teacher) {
        String newCategory = dialog.textInput("Pridať kategóriu", "Názov kategórie");
        if(!teacher.addCategory(newCategory))
            dialog.error("Chyba pri pridávaní kategórie", "Kategória s takýmto menom už existuje");
        Serialization.save(users);
        refreshTable(teacher);
        return;
    }


}
