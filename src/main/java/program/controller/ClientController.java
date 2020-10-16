package program.controller;

import javafx.scene.paint.Color;
import program.model.ModelDataHandler;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    Client echoClient;
    private MapController mapController;
    StartController startController;
    ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
    Player player;
    Player currentPlayer;
    boolean lobbyLeader = false;

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
    public void getLobbies() throws IOException, ClassNotFoundException {
        echoClient.sendObject("LOBBYS");
    }
    public void startGame() throws IOException {
        echoClient.sendObject("startGame");
    }
    public void sendObject(Object object) throws IOException {
        echoClient.sendObject(object);
    }
    public void addPlayerToLobby(Player player) throws IOException {
        echoClient.sendObject(player);
    }
    public boolean checkIfLobbyLeader() throws IOException {
        echoClient.sendObject("LOBBYLEADER");
        return false;
    }
}
