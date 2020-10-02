package program.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the StartMenu.fxml
 */
public class StartController extends AnchorPane {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button startButton;

    private SetUpGameController setUpGameController;// = new SetUpGameController();
    //MapController mapController = new MapController();
    private Parent root;// = setUpGameController;
    private Stage stage;

    /**
     *
     * @param stage the main stage
     */
    public StartController(Stage stage) {

        this.stage = stage;
        setUpGameController = new SetUpGameController(stage);
        root = setUpGameController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();

    }

    private void initialize() {
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("Chans");
                stage.setScene(scene);
                stage.show();

            }
        });
    }
}
