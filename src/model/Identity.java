package model;

import model.user.User;
import static model.user.User.users;

/**
 * Class representing the logged in user's identity
 * Singleton design patter
 */
public class Identity {
    private String username;
    private static Identity instance = null;
    private User user;

    /**
     * Private constructor
     * @param username identifies the logged in user
     */
    private Identity(String username) {
        this.username = username;
        for (User u : users) {
            if(username.equals(u.getUsername()))
                this.user = u;
        }
    }

    /**
     * Public singleton constructor
     * @param username currently logged in user
     * @return instance of the logged in user
     */
    public static Identity getInstance(String username)
    {
        if (instance == null)
            instance = new Identity(username);
        return instance;
    }

    /**
     * Public singleton constructor
     * @return instance of the logged in user
     */
    public static Identity getInstance() {
        return instance;
    }

    /**
     * Gets currently logged in user
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets current instance to null
     */
    public void destroy() {
        instance = null;
    }

    /**
     * Gets username
     * @return Stirng
     */
    public String getUsername() {
        return this.username;
    }
}
