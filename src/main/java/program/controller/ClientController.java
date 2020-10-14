package program.controller;

import program.model.Lobby;
import program.model.Player;
import program.model.User;

import java.io.IOException;

public class ClientController {

    Client echoClient;
    private MapController mapController;
    Lobby lobby;
    public ClientController(Client client) throws IOException {
        this.echoClient = client;
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
        mapController.view.setColor(mapController.getCube(id), mapController.modelDataHandler.getColorOnSpace(id).darker().darker(), mapController.allButtons);
    }
    public void removeSelectedSpace(int id) {
        mapController.view.setColor(mapController.getCube(id), mapController.modelDataHandler.getColorOnSpace(id), mapController.allButtons);
    }
    public void showCurrentPlayer(Player player) {
        mapController.modelDataHandler.setCurrentPlayer(player);
        mapController.view.updateCurrentPlayer(player.getColor(),mapController,player.getName());
    }
    public void updateLobby(Lobby lobby) throws IOException, ClassNotFoundException {
        echoClient.updateLobby(lobby);
    }
    public void getLobbys() throws IOException, ClassNotFoundException {
        echoClient.getLobbys();
    }
}
