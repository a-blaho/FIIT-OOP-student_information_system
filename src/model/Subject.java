package model;

import java.util.ArrayList;

/**
 * Object representing a school subject
 */
public class Subject implements java.io.Serializable{
    private String name;
    private ArrayList<Category> categories = new ArrayList<>();

    /**
     * Constructor
     * @param name name of the subject
     */
    public Subject(String name) {
        this.name = name;
        categories.add(new Category("Prvá písomka"));
        categories.add(new Category("Druhá písomka"));
    }

    /**
     * Gets the name of the subject
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets all categories
     * @return ArrayList
     */
    public ArrayList<Category> getCategories() {
        return categories;
    }

}
