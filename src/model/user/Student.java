package model.user;

import javafx.beans.property.SimpleStringProperty;
import model.Grade;
import model.Serialization;
import model.Subject;
import model.observer.Observer;

import java.util.ArrayList;
import java.util.Random;
import static helper.Round.round;

/**
 * Class representing student user
 */
public class Student extends User implements Observer {
    private ArrayList<Subject> subjects = new ArrayList<>();
    private ArrayList<Grade> grades = new ArrayList<>();
    private int year;
    private ArrayList<String> updatedSubjects = new ArrayList<>();  //list of subjects, that were updated (a grade was added or removed)

    /**
     * Constructor
     * @param name
     * @param surname
     * @param username
     * @param password
     */
    public Student(String name, String surname, String username, String password) {
        super.User(name, surname, username, password);
        Random num = new Random();
        this.year = num.nextInt(4) + 1;
    }

    /**
     * Gets students grades
     * @return ArrayList
     */
    public ArrayList<Grade> getGrades() {
       return this.grades;
    }

    /**
     * Prints all assigned subjects as a String
     * @return String
     */
    public String printSubjects() {
        String output = "";
        for(Subject s : this.subjects) {
            output = output + s.getName() + ", " ;
        }
        if(!output.equals(""))
            output = output.substring(0, output.length() - 2);  //get rid of the last comma
        return output;
    }

    /**
     * Get assigned subjects
     * @return ArrayList
     */
    public ArrayList<Subject> getSubjects() {
        return this.subjects;
    }

    /**
     * Get student's year
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * Calculates the average grade
     * @param subject Subject in which the average grade is supposed to be calculated
     * @return double
     */
    public double average(String subject) {
        double sum = 0, count = 0;
        for(Grade g : grades) {
            if(g.getSubject().getName().equals(subject)) {
                sum += g.getGrade();
                if(g.getGrade() != 0)           //if the grade was final, don't count it
                    count++;
            }
        }
        if(count != 0)
            return round(sum / count);
        else
            return 0;
    }

    /**
     * Gets the average grade as a SimpleStringProperty
     * @param subject
     * @return SimpleStringProperty
     */
    public SimpleStringProperty averageProperty(String subject) {
        return new SimpleStringProperty(Double.toString(average(subject)));
    }

    /**
     * Gets student's full name as a SimpleStringProperty
     * @return SimpleStringProperty
     */
    public SimpleStringProperty getFullName() {
        return new SimpleStringProperty( this.getName() + " " + this.getSurname());
    }

    /**
     * Gets path to .fxml file
     * Polymorphism example
     * @return String
     */
    public String getGradesPath(){
        return "grades";
    }

    /**
     * Returns the first object in updatedSubjects ArrayList
     * @return
     */
    public String firstUpdatedMessage() {
        return updatedSubjects.get(0);
    }

    /**
     * Removes subject from the list of updated subjects
     * @param o updated subject
     */
    public void removeUpdatedMessage(Object o) {
        if(updatedSubjects.size() == 0) {
            return;
        }
        for(String s : updatedSubjects) {
            if(s.equals(o)) {
                updatedSubjects.remove(o);
                break;
            }
        }
        Serialization.save(users);
    }

    /**
     * Adds subject to the list of updated subjects
     * @param subject
     */
    public void update(Subject subject) {
        for(String s : updatedSubjects) {      //check if the subject is already in the list
            if(s.equals(subject.getName())) {
                return;
            }
        }
        this.updatedSubjects.add(subject.getName());
        Serialization.save(users);
    }

    /**
     * Returns the size of updateSubject ArrayList
     * @return
     */
    public int getUpdatedSubjectSize() {
        return updatedSubjects.size();
    }
}
