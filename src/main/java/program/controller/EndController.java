package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import program.model.ModelDataHandler;


import java.io.IOException;

/**
 * the controller for endMenu.fxml
 */
public class EndController extends AnchorPane {

    @FXML
    private ImageView winnerImageView;
    @FXML
    private Label winnerLabel;
    @FXML
    private Pane buttonPane;
    @FXML
    private HBox winnerHBox;

    @FXML
    private Button pauseButton;
    @FXML
    private Button newGameButton;
    @FXML
    private Button toMenuButton;
    @FXML
    private Button quitGameButton;

    private Stage stage;
    private ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();

    EndController(Stage stage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("endMenu.fxml"));
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

    /**
     * This checks win condition and shows the endView if a player has won
     * If the end game button is pressed there is no winner
     */
    private void initialize() {
        if (modelDataHandler.isWinner()) {
            winnerLabel.setText("Congratulations to the winner");
            winnerImageView.setImage(new Image(modelDataHandler.getWinnerLogo()));
        } else {
            winnerLabel.setText("No winner this time");
        }
    }

    /**
     * When the new game button is clicked the player is taken back to the
     * start view and gets to play the game from the beginning again
     */
    public void newGameButtonPressed() throws IOException, ClassNotFoundException {
        Parent root = new StartController(stage);
        Scene scene = new Scene(root, 1920, 1080);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Quits the application when the button is pressed
     */
    public void quitGameButtonPressed() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Taken back to the menu when the button is pressed
     */
    public void toMenuButtonPressed() throws IOException, ClassNotFoundException {
        Parent root = new StartController(stage);
        Scene scene = new Scene(root, 1920, 1080);

        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }
}

