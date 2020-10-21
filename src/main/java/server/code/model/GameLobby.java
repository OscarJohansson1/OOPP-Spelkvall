package server.code.model;

import program.model.BoardManager;
import program.model.ChalmersBoard;
import program.model.GameManager;
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
        return currentPlayer = GameManager.nextPlayer(players, currentPlayer);
    }

    private Player getRandomPlayer() {
        return players.get(new Random().nextInt(players.size()));
    }
    public BoardManager initializeBoard() {
        GameManager modelDataHandler = GameManager.getGameManager();
        return new BoardManager(new ChalmersBoard(modelDataHandler.randomizeSpaces(modelDataHandler.spaceNames.size(), players)));
    }
}
