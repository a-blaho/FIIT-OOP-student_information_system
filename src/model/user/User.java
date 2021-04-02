package model.user;

import java.util.ArrayList;

/**
 * Abstract class that represents the user
 */
public abstract class User implements java.io.Serializable {
    private String name;
    private String surname;
    private String username;
    private String password;
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * Constructor
     */
    public void User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        users.add(this);
    }

    /**
     * Gets the username
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the surname
     * @return String
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Gets path to the correct .fxml file
     * This function should never be called using GUI
     * @return String
     */
    public String getGradesPath(){
        return "profile";
    }
}
