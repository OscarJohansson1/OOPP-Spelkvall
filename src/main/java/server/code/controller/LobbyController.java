package server.code.controller;

import program.model.Player;
import server.code.model.GameLobby;
import server.code.model.Lobby;
import server.code.model.MenuLobby;

import java.util.ArrayList;
import java.util.List;

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
            gameLobbies.get(gameLobbies.size() - 1).lobbyId = gameLobbies.size();
        } else if (lobby instanceof MenuLobby) {
            menuLobbies.add((MenuLobby) lobby);
            menuLobbies.get(menuLobbies.size() - 1).lobbyId = menuLobbies.size();
        }
    }

    public void createNewMenuLobby(String name) {
        addLobby(new MenuLobby(name));
    }

    public void createNewGameLobby(String name) {
        addLobby(new GameLobby(name));
    }

    public void addMenuLobbyPlayersToGameLobby(MenuLobby menuLobby, GameLobby gameLobby) {
        for (Player player : menuLobby.getLobbyPlayers()) {
            gameLobby.getLobbyPlayers().add(player);
        }
    }



}
