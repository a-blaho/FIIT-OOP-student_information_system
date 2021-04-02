package model;

/**
 * Class representing the final grade, which does not affect the average grade
 */
public class FinalGrade extends Grade{

    /**
     * Constructor
     * @param category
     * @param grade
     * @param subject
     */
    public FinalGrade(Category category, Integer grade, Subject subject) {
        super(category, grade, subject);
    }

    /**
     * Used in calculating average, so it returns 0
     * @return Integer
     */
    public Integer getGrade() {
        return 0;
    }
}
