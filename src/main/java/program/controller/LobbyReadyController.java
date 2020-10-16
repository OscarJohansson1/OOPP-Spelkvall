package program.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbyReadyController extends AnchorPane {

    @FXML public FlowPane userFlow;
    @FXML private Button backButton;
    @FXML private Button joinLobbyButton;
    @FXML public Button startButton;

    private final StartController startController;
    private final List<PlayerCard> userCards = new ArrayList<>();
    private final Stage stage;

    public LobbyReadyController(Stage stage, StartController startController) throws IOException, ClassNotFoundException {

        this.startController = startController;
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LobbyReady.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Platform.setImplicitExit(false);
        initialize();

    }

    public void initialize(){
        userFlow.setVgap(10);
        userFlow.setHgap(52);
        userFlow.setPadding(new Insets(10,0,10,52));
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //Skapar setUpGameController
                Scene scene = new Scene(startController, 1920, 1080);

                stage.setTitle("program.Chans");
                stage.setScene(scene);
                stage.show();
            }

        });
    }
    public void ready() throws IOException {
        startController.clientController.player.setReady(!startController.clientController.player.isReady());
        startController.clientController.sendObject(startController.clientController.player.isReady());
    }
    public void startGame() throws IOException {
        startController.clientController.startGame();
    }

    public void updateUserCards(List<Player> players){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                userCards.clear();
                for (Player player : players) {
                    userCards.add(new PlayerCard(player));
                }
                userFlow.getChildren().setAll(userCards);
                System.out.println("Usercards are " + userCards.size());
            }
        });


    }

}