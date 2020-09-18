import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class StartController extends AnchorPane {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button startButton;

    MapController mapController = new MapController();
    Parent root = mapController;
    Stage stage;



    public StartController(Stage stage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartMenu.fxml"));
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

    private void initialize() {
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Scene scene = new Scene(root, 1600, 900);

                stage.setTitle("Chans");
                stage.setScene(scene);
                stage.show();
            }
        });
    }
}
