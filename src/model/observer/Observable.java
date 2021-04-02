package model.observer;

import model.Subject;
import model.user.Student;

import java.util.ArrayList;

/**
 * Observer pattern interface
 */
public interface Observable {


    /**
     * Adds a student to an ArrayList
     * @param s
     */
    default void addObserver(ArrayList<Observer> observerList, Student s) {
        observerList.add(s);
    }

    /**
     * Removes observer from the ArrayList
     * @param s
     */
    default void removeObserver(ArrayList<Observer> observerList, Student s) {
        observerList.remove(s);
    }
    /**
     * Notifies a specific observer from ArrayList
     * @param observer
     * @param subject
     */
    void notifyObserver(Observer observer, Subject subject);
}
