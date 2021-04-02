package model;
import model.user.User;

import java.io.*;
import java.util.ArrayList;

/**
 * Class that takes care of serialization in the whole project
 */
public class Serialization implements Serializable{

    /**
     * Saves all the changes made into a file
     * @param users All users
     */
    public static void save(ArrayList<User> users) {
        Dialog dialog = new Dialog();
        try {
            FileOutputStream fileStream = new FileOutputStream("src/data/users.txt");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(users);
            objectStream.flush();
            objectStream.close();
            fileStream.close();
        } catch(FileNotFoundException e) {
            dialog.error("Vyskytol sa problém", "Problém s ukladaním dát");
        } catch(IOException e) {
            dialog.error("Vyskytol sa problém", "Problém s ukladaním dát");
        }
    }

    /**
     * Loads data from a file
     * @return Updated ArrayList of all users
     */
    public static ArrayList<User> load() {
        Dialog dialog = new Dialog();
        try {
            FileInputStream fileInput = new FileInputStream("src/data/users.txt");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            ArrayList<User> users = (ArrayList<User>) objectInput.readObject();
            return users;
        } catch (FileNotFoundException e) {
            dialog.error("Vyskytol sa problém", "Problém s načítaním dát");
        } catch (IOException e) {
            dialog.error("Vyskytol sa problém", "Problém s načítaním dát");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            dialog.error("Vyskytol sa problém", "Problém s načítaním dát");
            e.printStackTrace();
        }
        return null;
    }
}
