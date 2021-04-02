package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class that represents grades
 */
public abstract class Grade implements java.io.Serializable{
    private Subject subject;
    private Integer grade;
    private Category category;

    /**
     * Constructor
     * @param category Category in which the grade was given
     * @param grade Value of the given grade (1-5)
     * @param subject Subject in which the grade was given
     */
    public Grade(Category category, Integer grade, Subject subject){
        this.category = category;
        this.grade = grade;
        this.subject = subject;
    }

    /**
     * Gets the grade value as a SimpleStringProperty
     * @return SimpleStringProperty
     */
   public StringProperty getGradeProperty() {

        return new SimpleStringProperty(Integer.toString(grade));
    }

    /**
     * Gets the category of the grade as a SimpleStringProperty
     * @return SimpleStringProperty
     */
    public StringProperty getCategoryProperty() {
        return new SimpleStringProperty(category.getCategory());
    }

    /**
     * Gets the category in which the grade was given
     * @return Category
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Gets subject in which the grade was given
     * @return Subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the value of grade
     * @return Integer
     */
    public Integer getGrade() {
        return grade;
    }

}
