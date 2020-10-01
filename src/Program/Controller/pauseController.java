package Program.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class pauseController extends AnchorPane {

    @FXML
    private HBox pauseHBox;

    @FXML
    private ImageView chanceImageView;

    @FXML
    private Pane buttonPane;

    @FXML
    private Button returnButton;

    @FXML
    private Button newGameButton;

    @FXML
    private Button endGameButton;

    @FXML
    private Button quitGameButton;

    MapController mapController;
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

    public void initialize() {
        //TODO: What now? Pause ska finnas från start av programmet och ska sedan finnas kvar i ett lager bakom kartan.

    }

    public void returnButtonPressed(){

        /*
        //Uppdatera kartan
        updateMap();
        //Lägg kartan längst fram
        map.toFront();
        //Visa interfacen igen
        interfacePane.setVisible(true);
         */
    }

    public void newGameButtonPressed(){

        /*
        //Avsluta omgången
        game.end();
        //Gå till setUpGame.fxml
        ... hur man nu skriver det
         */
    }

    public void endGameButtonPressed(){

         /*
        //Avsluta omgången
        game.end();
        //Gå till endMenu.fxml
        ... hur man nu skriver det
         */
    }

    public void quitGameButtonPressed(){

        /*
        //Gå till endMenu.fxml
        ... hur man nu skriver det
         */
    }



}