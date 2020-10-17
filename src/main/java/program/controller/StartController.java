package program.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import program.model.Player;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The controller for the StartMenu.fxml
 */
public class StartController extends AnchorPane {

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

    private SetUpGameController setUpGameController;// = new SetUpGameController();
    private Parent root;// = setUpGameController;
    Stage stage;


    LobbySelectController lobbySelectController;
    LobbyReadyController lobbyReadyController;
    MultiplayerLogoController multiplayerLogoController;
    ClientController clientController;

    /**
     * @param stage the main stage
     */
    public StartController(Stage stage) throws IOException, ClassNotFoundException {

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

    private void initialize() {
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Scene scene = new Scene(root, screenSize.width, screenSize.height);

                stage.setFullScreen(true);
                stage.setTitle("program.Chans");
                stage.setScene(scene);
                stage.show();
            }
        });

        quitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Platform.exit();
                System.exit(0);

            }
        });
        startButton2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    goToLobbySelect();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goToLobbySelect() throws IOException, ClassNotFoundException {
        Client client = new Client();
        clientController = new ClientController(client, this);
        clientController.getLobbies();
        rootpane.getChildren().add(lobbySelectController);

    }

    public void goToLobbyReady(Player player, int gridPosImageview) throws IOException, ClassNotFoundException {
        clientController.player = player;
        System.out.println(player);
        clientController.addPlayerToLobby(new Player(player));
        clientController.checkIfLobbyLeader();
        clientController.updateGridPane(gridPosImageview);
        lobbyReadyController.startButton.setVisible(false);
        lobbyReadyController.startButton.setDisable(true);
        rootpane.getChildren().add(lobbyReadyController);
    }

    public void goToSetup() throws IOException {
        for (int i = 0; i < lobbySelectController.lobbyItems.size(); i++) {
            if (lobbySelectController.lobbyItems.get(i).marked) {
                clientController.sendObject(i);
                break;
            }
        }
        multiplayerLogoController = new MultiplayerLogoController(clientController, this);
        rootpane.getChildren().add(multiplayerLogoController);
    }

    public void removeSetUp() {
        rootpane.getChildren().remove(multiplayerLogoController);
    }

    public void backToMainMenu() {

        rootpane.getChildren().remove(lobbySelectController);

    }

    public void toLobbySelect() throws IOException {

        rootpane.getChildren().remove(multiplayerLogoController);
        rootpane.getChildren().add(lobbySelectController);
    }

}
