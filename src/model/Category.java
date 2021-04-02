package model;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

/**
 * Class representing a subject category
 * This class allows us to display subject categories in TableView
 */
public class Category implements Serializable {
    String category;

    /**
     * Constructor
     * @param category
     */
    public Category(String category) {
        this.category = category;
    }

    /**
     * Gets the category name
     * @return String
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Gets the category name as SimpleStringProperty
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getCategoryProperty() {
        return new SimpleStringProperty(this.category);
    }
}

