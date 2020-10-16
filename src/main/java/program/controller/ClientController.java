package program.controller;

import javafx.scene.paint.Color;
import program.model.Lobby;
import program.model.ModelDataHandler;
import program.model.Player;
import program.model.User;

import java.io.IOException;

public class ClientController {

    Client echoClient;
    private MapController mapController;
    StartController startController;
    ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
    Lobby lobby;
    User user;

    public ClientController(Client client, StartController startController) throws IOException {
        this.echoClient = client;
        this.startController = startController;
        try {
            echoClient.startConnection("95.80.61.51", 6666, this);
        } catch (IOException e) {
            System.out.println("Could not connect to server");
        }
    }

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public void showPhase(String string) {
        mapController.view.updatePhase(string,mapController);

    }
    public void showSelectedSpace(int id) {
        mapController.view.setColor(mapController.getCube(id), Color.web(mapController.modelDataHandler.getColorOnSpace(id)).darker().darker(), mapController.allButtons);
    }
    public void removeSelectedSpace(int id) {
        mapController.view.setColor(mapController.getCube(id), Color.web(mapController.modelDataHandler.getColorOnSpace(id)), mapController.allButtons);
    }
    public void showCurrentPlayer(Player player) {
        mapController.modelDataHandler.setCurrentPlayer(player);
        mapController.view.updateCurrentPlayer(player.getColor(),mapController,player.getName());
    }
    public void updateLobby(Lobby lobby) throws IOException {
        echoClient.updateLobby(lobby);
    }
    public void getLobbys() throws IOException, ClassNotFoundException {
        echoClient.getLobbys();
    }
    public void setLobby(Lobby lobby){
        this.lobby = lobby;
        startController.lobbyReadyController.updateUserCards();
    }
    public void startGame() throws IOException {
        echoClient.startGame();
    }
    public void sendObject(Object object) throws IOException {
        echoClient.sendObject(object);
    }
}
