package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbySelectController extends AnchorPane {

    @FXML public FlowPane lobbyFlow;
    @FXML public Button backToMenuButton;
    @FXML public Button joinLobbyButton;


    public List<LobbyItem> lobbyItems = new ArrayList<>();

    private final StartController startController;

    public LobbySelectController(StartController startController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lobbySelect.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.startController = startController;
    }

    @FXML
    public void joinLobby() throws IOException {
        startController.goToSetup();
    }

    @FXML
    public void toMenu() {
        startController.backToMainMenu();
    }

    public void resetLobbyItems() {
        for (LobbyItem lobbyItem : lobbyItems) {
            lobbyItem.marked = false;
        }
    }

    public void updateLobbies() {
        Platform.runLater(() -> {
            lobbyFlow.getChildren().addAll(lobbyItems);
            });

    }
}
