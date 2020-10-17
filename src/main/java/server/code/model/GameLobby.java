package server.code.model;

import program.model.ModelDataHandler;
import program.model.Player;

import java.util.Random;

public class GameLobby extends Lobby {
    private Player currentPlayer;

    public GameLobby(String name) {
        super(name);
    }

    public Player nextPlayer() {
        if (currentPlayer == null) {
            return currentPlayer = getRandomPlayer();
        }
        return currentPlayer = ModelDataHandler.nextPlayer(players, currentPlayer);
    }

    private Player getRandomPlayer() {
        return players.get(new Random().nextInt(players.size()));
    }
}
