package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * the controller for pauseMenu.fxml
 */
public class PauseController extends AnchorPane {

    @FXML private AnchorPane rootpane;
    @FXML private HBox pauseHBox;
    @FXML private ImageView chanceImageView;
    @FXML private Pane buttonPane;

    @FXML private Button returnButton;
    @FXML private Button newGameButton;
    @FXML private Button endGameButton;
    @FXML private Button quitGameButton;

    private MapController mapController;
    private Parent root = mapController;
    private Stage stage;

    public PauseController(Stage stage, MapController mapcontroller) {

        this.mapController = mapcontroller;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pauseMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.stage = stage;
        initialize();

    }

    public void initialize() {
        //TODO: What now? Pause ska finnas från start av programmet och ska sedan finnas kvar i ett lager bakom kartan.

    }

    public void returnButtonPressed(){

       mapController.checkPauseController();

    }

    public void newGameButtonPressed(){

        Parent root = new StartController(stage);
        Scene scene = new Scene(root, 1920, 1080);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void endGameButtonPressed(){

        Parent root = new EndController(stage);
        Scene scene = new Scene(root, 1920, 1080);

        stage.setTitle("End");
        stage.setScene(scene);
        stage.show();
    }

    public void quitGameButtonPressed(){

        Platform.exit();
        System.exit(0);
    }



}

