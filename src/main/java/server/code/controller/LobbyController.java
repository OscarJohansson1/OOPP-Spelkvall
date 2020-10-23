package server.code.controller;

import program.model.Player;
import server.code.model.GameLobby;
import server.code.model.Lobby;
import server.code.model.MenuLobby;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles lists of lobbies.
 */
public class LobbyController {
    private final List<GameLobby> gameLobbies = new ArrayList<>();
    private final List<MenuLobby> menuLobbies = new ArrayList<>();

    public LobbyController() {
        createNewMenuLobby("yes");
    }

    public List<GameLobby> getGameLobbies() {
        return gameLobbies;
    }

    public List<MenuLobby> getMenuLobbies() {
        return menuLobbies;
    }


    private void addLobby(Lobby lobby) {
        if (lobby instanceof GameLobby) {
            gameLobbies.add((GameLobby) lobby);
            gameLobbies.get(gameLobbies.size() - 1).setLobbyId(gameLobbies.size());
        } else if (lobby instanceof MenuLobby) {
            menuLobbies.add((MenuLobby) lobby);
            menuLobbies.get(menuLobbies.size() - 1).setLobbyId(menuLobbies.size());
        }
    }

    /**
     * Creates a new MenuLobby and adds it to the menuLobbies list.
     *
     * @param name name of Lobby
     */
    public void createNewMenuLobby(String name) {
        addLobby(new MenuLobby(name));
    }

    /**
     * Creates a new GameLobby and adds it to the gameLobbies list.
     *
     * @param name name of Lobby
     */
    public void createNewGameLobby(String name) {
        addLobby(new GameLobby(name));
    }

    /**
     * Copies all the players in MenuLobby to GameLobby
     */
    public void addMenuLobbyPlayersToGameLobby(MenuLobby menuLobby, GameLobby gameLobby) {
        for (Player player : menuLobby.getLobbyPlayers()) {
            gameLobby.getLobbyPlayers().add(player);
        }
    }


}
