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

    List<LobbyItem> lobbys = new ArrayList<>();

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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LobbyItem lobbyItem = null;
                try {
                    lobbyItem = new LobbyItem(startController.clientController.getLobbys());
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                lobbys.add(lobbyItem);
                lobbyFlow.getChildren().addAll(lobbyItem);
            }
        });


    }

    public void joinLobby() {
        startController.goToSetup();
    }




}
