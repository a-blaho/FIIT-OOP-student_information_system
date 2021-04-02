package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Serialization;
import model.user.Student;
import model.Subject;
import model.user.Teacher;
import static model.user.User.users;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Študentský informačný systém");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        users = Serialization.load();
        /*
        Subject math = new Subject("Matematika");
        Subject slovak = new Subject("Slovenčina");

        Student s = new Student("Adam", "Blahovič", "adam", "adam");
        Student s1 = new Student("David", "Testový", "david", "david");
        Student s2 = new Student("Jakub", "Testový", "jakub", "jakub");


        Teacher t = new Teacher("Marek", "Matematikár", "marek", "marek", math);
        Teacher t1 = new Teacher("Peter", "Slovenčinár", "peter", "peter", slovak);

        t.assignStudent(s);
        t1.assignStudent(s);
        t.assignStudent(s1);
        t1.assignStudent(s1);
        t.assignStudent(s2);
        t1.assignStudent(s2);

        t.addGrade(s,math,1, math.getCategories().get(0), false);
        t.addGrade(s,math,4, math.getCategories().get(1), false);

        Serialization.save(users);
        */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
