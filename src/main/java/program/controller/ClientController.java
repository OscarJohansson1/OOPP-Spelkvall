package program.controller;

import javafx.scene.paint.Color;
import program.model.ModelDataHandler;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    Client echoClient;
    StartController startController;
    ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
    public Player player;

    public ClientController(Client client, StartController startController) throws IOException {
        this.echoClient = client;
        this.startController = startController;
        try {
            echoClient.startConnection("95.80.61.51", 6666, this);
        } catch (IOException e) {
            System.out.println("Could not connect to server");
        }
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

    public void checkIfLobbyLeader() throws IOException {
        echoClient.sendObject("LOBBYLEADER");
    }

    public void resetColor() throws IOException {
        echoClient.sendObject("resetColor");
    }

    public void nextPlayer(Player player) throws IOException {
        echoClient.sendObject(player);
    }

    public void getGridPane() throws IOException {
        echoClient.sendObject("gridPane");
    }

    public void updateGridPane(int number) throws IOException {
        echoClient.sendObject(number);
    }
}
