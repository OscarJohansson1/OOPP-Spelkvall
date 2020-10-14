package program.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbyReadyController extends AnchorPane {

    @FXML public FlowPane playerFlow;
    @FXML private Button backButton;
    @FXML private Button joinLobbyButton;

    private StartController root;
    private List<UserCard> userCards = new ArrayList<>();
    private Stage stage;

    public LobbyReadyController(List<UserCard> userCards, Stage stage) throws IOException, ClassNotFoundException {

        this.userCards = userCards;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LobbyReady.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        Platform.setImplicitExit(false);
        playerFlow.getChildren().addAll(userCards);
        initialize();

    }

    public void initialize(){

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                //Skapar setUpGameController
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("program.Chans");
                stage.setScene(scene);
                stage.show();
            }

        });
    }
    public void ready(){

    }
    public void startGame(){

    }

}