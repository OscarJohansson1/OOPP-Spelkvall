package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.io.IOException;

public class EndController extends AnchorPane {

    @FXML
    private Button pauseButton;

    @FXML
    private ImageView winnerImageView;

    @FXML
    private Label winnerLabel;

    @FXML
    private Button newGameButton;

    @FXML
    private Button toMenuButton;

    @FXML
    private Button quitGameButton;

    @FXML
    private Button buttonPane;

    @FXML
    private HBox winnerHBox;

    //MapController mapController = new MapController();
    //Parent root = mapController;
    Stage stage;

    public EndController(Stage stage) {

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

    private void initialize() {
        //TODO: Här vet jag inte hur man ska koppla. Vi vill att endMenu visas då spelet är slut.

        /*Scene scene = new Scene(root, 1920, 1080);

        stage.setTitle("End");
        stage.setScene(scene);
        stage.show();*/

        /*
        //Skriva ut vem som vann i winnerLabel, måste veta vem som vann --> deras namnattribut
        /*winnerLabel.setText(" The winner is: " + winnerName);

        //Kan skrivas ut efter "division" men då måste vi skriva in det som attribut. Ist. kan man använda färgen.
        //Isåfall måste den vinnande spelarens färg skickas in till endController
        switch (winnerColor) {
            case "turquoise":
                winnerImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream("picures/itloggan.JPG")));
            case "orange":
                //winnerImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream("picures/datateknologsektionen.JPG")));
            case "brown":
                //winnerImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream("picures/maskinloggan.JPG")));
            case "dark green":
                //winnerImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream("picures/kfkbloggan.JPG")));

        }*/





    }

    public void newGameButtonPressed(){

        /*
        //Avsluta omgången
        game.end();
        //Gå till setUpGame.fxml
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