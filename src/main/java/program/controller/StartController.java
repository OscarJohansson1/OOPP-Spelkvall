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

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
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
                goOnline();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * If the observer list isn't empty it creates a new LobbySelectController and adds it to the rootpane and asks the server for "LOBBYS".
     */
    public void goToLobbySelect() throws IOException {
        if (!observers.isEmpty()) {
            lobbySelectController = new LobbySelectController(this);
            notifyObservers("LOBBYS");
            rootpane.getChildren().add(lobbySelectController);
        }
    }

    /**
     * If there are no observers it tries to make a connection to the server.
     */
    public void goOnline() throws IOException {
        if (observers.isEmpty()) {
            Client.getClient().startConnection("95.80.61.51", 6666, this);
        }
        goToLobbySelect();
    }

    /**
     * Removes the client from the observer list and stops the connection to the server.
     */
    public void exitOnline() throws IOException {
        Client.getClient().removeObserver();
        Client.getClient().stopConnection();
    }

    /**
     * Notifies the observers and moves on to the Lobby ready view
     *
     * @param player           receives player from MultiPlayerLogoController.
     * @param gridPosImageview the id of a logo on the gridpane in MultiPlayerLogoController.
     */
    public void goToLobbyReady(Player player, int gridPosImageview) throws IOException {
        notifyObservers(new Player(player));
        notifyObservers("LOBBYLEADER");
        notifyObservers(gridPosImageview);
        lobbyReadyController.startButton.setVisible(false);
        lobbyReadyController.startButton.setDisable(true);
        rootpane.getChildren().add(lobbyReadyController);
        rootpane.getChildren().remove(multiplayerLogoController);
    }

    /**
     * Notifies the observers of the marked lobby and adds MultiPlayerLogoController to the rootpane.
     */
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
        notifyObservers("gridPane");

    }

    /**
     * Notifies the observers to start the game.
     */
    public void startGame() throws IOException {
        notifyObservers("startGame");
    }

    /**
     * Returns to the lobby list view.
     */
    public void removeSetUp() throws IOException {
        rootpane.getChildren().remove(multiplayerLogoController);
        goToLobbySelect();
    }

    /**
     * Returns to the lobby list view.
     */
    public void removeLobbyReady() throws IOException {
        rootpane.getChildren().remove(lobbyReadyController);
        notifyObservers("removePlayer");
        goToLobbySelect();
    }

    /**
     * Returns to the start menu.
     */
    public void backToMainMenu() throws IOException {
        rootpane.getChildren().remove(lobbySelectController);
        exitOnline();
    }

}
