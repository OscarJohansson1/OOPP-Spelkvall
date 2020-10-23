package server.code.model;

import program.model.BoardManager;
import program.model.ChalmersBoard;
import program.model.GameManager;
import program.model.Player;

import java.util.Random;

/**
 * Lobby for while playing
 */
public class GameLobby extends Lobby {
    private Player currentPlayer;

    public GameLobby(String name) {
        super(name);
    }

    /**
     * @return returns next player in list
     */
    public Player nextPlayer() {
        if (currentPlayer == null) {
            return currentPlayer = getRandomPlayer();
        }
        return currentPlayer = GameManager.nextPlayer(players, currentPlayer);
    }

    /**
     * @return returns random player from player list
     */
    private Player getRandomPlayer() {
        return players.get(new Random().nextInt(players.size()));
    }

    /**
     * Creates a board by using the space names and the player list.
     *
     * @return returns the board
     */
    public BoardManager initializeBoard() {
        GameManager modelDataHandler = GameManager.getGameManager();
        return new BoardManager(new ChalmersBoard(modelDataHandler.randomizeSpaces(modelDataHandler.spaceNames.size(), players)));
    }
}
