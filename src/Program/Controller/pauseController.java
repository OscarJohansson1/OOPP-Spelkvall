package Program.Controller;

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

public class pauseController extends AnchorPane {

    @FXML
    private Button pauseButton;

    MapController mapController = new MapController();
    Parent root = mapController;
    Stage stage;

    public pauseController(Stage stage) {

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

    private void initialize() {
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Scene scene = new Scene(root, 800, 600);

                stage.setTitle("Pause");
                stage.setScene(scene);
                stage.show();

            }
        });
    }


}