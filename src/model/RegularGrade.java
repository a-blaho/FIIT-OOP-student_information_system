package model;

/**
 * Class that represents a regular grade
 */
public class RegularGrade extends Grade {

    /**
     * Constructor
     * @param category
     * @param grade
     * @param subject
     */
    public RegularGrade (Category category, Integer grade, Subject subject) {
        super(category, grade, subject);
    }

    /**
     * Gets the value of grade
     * @return Integer
     */
    public Integer getGrade() {
        return super.getGrade();
    }
}
