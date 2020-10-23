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

/**
 * The controller for lobbySelect.fxml
 */
public class LobbySelectController extends AnchorPane {

    @FXML
    public FlowPane lobbyFlow;
    @FXML
    public Button backToMenuButton;
    @FXML
    public Button joinLobbyButton;

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

    /**
     * Goes to the MultiPlayerLogo view.
     */
    @FXML
    public void joinLobby() throws IOException {
        startController.goToSetup();
    }

    /**
     * Returns to the start menu.
     */
    @FXML
    public void toMenu() throws IOException {
        startController.backToMainMenu();
    }

    /**
     * Resets the marked lobbyItem.
     */
    public void resetLobbyItems() {
        for (LobbyItem lobbyItem : lobbyItems) {
            lobbyItem.marked = false;
        }
    }

    /**
     * Fills the lobbyFlow FlowPane with a list of LobbyItems.
     */
    public void updateLobbies() {
        Platform.runLater(() -> {
            lobbyFlow.getChildren().removeAll();
            lobbyFlow.getChildren().addAll(lobbyItems);
        });

    }
}
