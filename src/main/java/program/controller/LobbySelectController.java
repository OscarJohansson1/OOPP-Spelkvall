package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbySelectController extends AnchorPane {

    @FXML public FlowPane lobbyFlow;

    List<LobbyItem> lobbyItems = new ArrayList<>();

    private final StartController startController;

    public LobbySelectController(StartController startController) throws IOException, ClassNotFoundException {
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

    public void joinLobby() throws IOException {
        startController.goToSetup();
    }

    public void toMenu() throws IOException {

        startController.backToMainMenu();

    }
    public void resetLobbyItems(){
        for(LobbyItem lobbyItem: lobbyItems)
        {
            lobbyItem.marked = false;
        }
    }
    public void updateLobbys(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbyFlow.getChildren().addAll(lobbyItems);
            }});
    }
}
