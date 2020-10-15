package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import program.model.Lobby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbySelectController extends AnchorPane {

    @FXML public FlowPane lobbyFlow;
    Lobby chosenLobby;

    List<Lobby> lobbys = new ArrayList<>();

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
        startController.goToSetup(chosenLobby);
    }
    public void updateChoosenLobby(List<Lobby> lobbies) {

        if(chosenLobby != null){
            for (Lobby lobby : lobbies) {
                if (lobby.getLobbyId() == chosenLobby.getLobbyId()) {
                    chosenLobby = lobby;
                }
            }
        }

    }
    public void updateLobbys(List<Lobby> lobbies){
        Platform.runLater(new Runnable() {
        @Override
        public void run() {
            System.out.println("Updating lobbys in LobbyselectController");
            lobbyFlow.getChildren().clear();
            lobbys = lobbies;
            for(Lobby lobby: lobbys){
                lobbyFlow.getChildren().add(new LobbyItem(lobby, startController));
            }
            System.out.println("Lobby size is " + lobbys.size());
        }});


    }




}
