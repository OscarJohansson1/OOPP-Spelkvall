package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import program.client.Client;
import program.model.IObservable;
import program.model.IObserver;
import program.model.Player;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The controller for the StartMenu.fxml
 */
public class StartController extends AnchorPane implements IObservable {

    @FXML
    private AnchorPane rootpane;
    @FXML
    private ImageView chanceImageView;

    @FXML
    private Button startButton;
    @FXML
    private Button startButton2;
    @FXML
    private Button quitButton;

    private final Parent root;
    public Stage stage;

    private final List<IObserver> observers = new ArrayList<>();
    public LobbySelectController lobbySelectController;
    public LobbyReadyController lobbyReadyController;
    public MultiplayerLogoController multiplayerLogoController;

    /**
     * @param stage the main stage
     */
    public StartController(Stage stage) {

        this.stage = stage;
        root = new SetUpGameController(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Platform.setImplicitExit(false);

        lobbySelectController = new LobbySelectController(StartController.this);
        lobbyReadyController = new LobbyReadyController(stage, this);
        initialize();

    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object object) throws IOException {
        for (IObserver observer : observers) {
            observer.sendObject(object);
        }
    }

    private void initialize() {
        startButton.setOnMouseClicked(mouseEvent -> {

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Scene scene = new Scene(root, screenSize.width, screenSize.height);

            stage.setFullScreen(true);
            stage.setTitle("program.Chans");
            stage.setScene(scene);
            stage.show();
        });

        quitButton.setOnMouseClicked(mouseEvent -> {

            Platform.exit();
            System.exit(0);

        });
        startButton2.setOnMouseClicked(mouseEvent -> {
            try {
                goToLobbySelect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void goToLobbySelect() throws IOException {
        Client.getClient().startConnection("95.80.61.51", 6666, this);
        notifyObservers("LOBBYS");
        if (observers.size() != 0) {
            rootpane.getChildren().add(lobbySelectController);
        }
    }

    public void goToLobbyReady(Player player, int gridPosImageview) throws IOException {
        notifyObservers(new Player(player));
        notifyObservers("LOBBYLEADER");
        notifyObservers(gridPosImageview);
        lobbyReadyController.startButton.setVisible(false);
        lobbyReadyController.startButton.setDisable(true);
        rootpane.getChildren().add(lobbyReadyController);
        rootpane.getChildren().remove(multiplayerLogoController);
    }

    public void goToSetup() throws IOException {
        for (int i = 0; i < lobbySelectController.lobbyItems.size(); i++) {
            if (lobbySelectController.lobbyItems.get(i).marked) {
                notifyObservers(i);
                break;
            }
        }
        multiplayerLogoController = new MultiplayerLogoController(this);
        rootpane.getChildren().add(multiplayerLogoController);
        rootpane.getChildren().remove(lobbySelectController);
    }

    public void startGame() throws IOException {
        notifyObservers("startGame");
    }

    public void removeSetUp() {
        rootpane.getChildren().remove(multiplayerLogoController);
    }

    public void backToMainMenu() {
        rootpane.getChildren().remove(lobbySelectController);
    }

    public void toLobbySelect() {
        rootpane.getChildren().remove(multiplayerLogoController);
        rootpane.getChildren().add(lobbySelectController);
    }


}
