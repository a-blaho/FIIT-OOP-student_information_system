package model.user;

import model.*;
import model.observer.Observable;
import model.observer.Observer;

import java.util.ArrayList;

/**
 * Class representing a teacher user
 */
public class Teacher extends User implements Observable {
    private String title;
    private Subject subject;
    ArrayList<Observer> observerList = new ArrayList<>();
    /**
     * Constructor
     * @param name
     * @param surname
     * @param username
     * @param password
     * @param subject
     */
    public Teacher(String name, String surname, String username, String password, Subject subject) {
        super.User(name, surname, username, password);
        this.subject = subject;
        this.title = "Mgr.";
    }
    /**
     * Observer pattern method to notify a student, when their grades get changed
     * @param observer
     * @param subject
     */
    public void notifyObserver(Observer observer, Subject subject) {
        for(Observer o : this.observerList) {
            if(o.equals(observer)) {
                o.update(subject);
                return;
            }
        }
    }

    /**
     * Assigns student to the teachers subject
     * @param student
     */
    public void assignStudent(Student student) {
        for(Subject s : student.getSubjects()) {
            if(s.equals(this.subject)) {
                return;
            }
        }
        student.getSubjects().add(this.subject);
        this.addObserver(this.observerList ,student);
        return;
    }

    /**
     * Adds grade to a student
     * @param student
     * @param subject
     * @param grade
     * @param category
     */
    public void addGrade(Student student, Subject subject, int grade, Category category, boolean finalGrade) {
        for(Category s : this.subject.getCategories()) {
            if(s.getCategory().equals(category.getCategory())) {
                if(finalGrade)
                    student.getGrades().add(new FinalGrade(category, grade, subject));
                else
                    student.getGrades().add(new RegularGrade(category, grade, subject));
                break;
            }
        }
        this.notifyObserver(student, subject);
        Serialization.save(users);
    }

    /**
     * Gets the teacher's title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the teacher's subject
     * @return Subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Adds a new unique category to subject
     * @param category name of the category
     * @return  true if adding was successful
     */
    public boolean addCategory(String category) {
        for(Category s : this.subject.getCategories()) {    //checks if the category is already in the ArrayList
            if(s.getCategory().equals(category)) {
                return false;
            }
        }
        this.subject.getCategories().add(new Category(category));
        Serialization.save(users);
        return true;
    }

    /**
     * Removes student's grade
     * @param student
     * @param grade
     */
    public void removeGrade(Student student, Grade grade) {
        student.getGrades().remove(grade);
        notifyObserver(student, this.subject);
        Serialization.save(users);
    }

    /**
     * Removes a category from subject and all grades within that category
     * @param category
     */
    public void removeCategory(Category category) {
        for(User u : users) {                                               //remove all grades in the category
            if(u instanceof Student) {
                Student s = (Student) u;
                for(Grade g : s.getGrades()) {
                    if(category.equals(g.getCategory())) {
                        removeGrade(s, g);
                        break;
                    }
                }
            }
        }
        //then remove the category itself
        this.getSubject().getCategories().remove(category);
        Serialization.save(users);
        return;
    }

    /**
     * Counts all students in the teacher's subject
     * @return amount of students as a String
     */
    public String studentsCount() {
        int count = 0;
        for(User u : users) {
            if(u instanceof Student) {
                Student student = (Student) u;
                for(Subject subject : student.getSubjects()) {
                    if(subject.getName().equals(this.getSubject().getName())) {
                        count++;
                        break;
                    }
                }
            }
        }
        return String.valueOf(count);
    }

    /**
     * Gets path to the correct .fxml file
     * Example of polymorphism
     * @return
     */
    public String getGradesPath(){
        return "mystudents";
    }
}

