package model.observer;

import model.Subject;

/**
 * Observer pattern interface
 */
public interface Observer {
    /**
     * Updates itself
     * @param subject
     */
    void update(Subject subject);
}
