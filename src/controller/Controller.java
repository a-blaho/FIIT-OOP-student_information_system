package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import model.Identity;
import java.io.IOException;

/**
 * Base controller
 */
abstract class Controller {

    @FXML
    AnchorPane pane;

    protected Identity identity;

    /**
     * Initializes the scene
     */
    public void initialize() {
        identity = Identity.getInstance();
    }

    /**
     * Switches to a new scene
     * @param pane
     * @param path
     * @throws IOException file wasn't found
     */
    public void switchScene(AnchorPane pane, String path) throws IOException {
        pane.getChildren().clear();
        pane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + path + ".fxml")));
    }
}
