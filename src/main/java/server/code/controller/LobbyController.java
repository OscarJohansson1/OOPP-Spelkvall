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

    public LobbyController(){
        createNewMenuLobby("yes");
    }
    public List<GameLobby> getGameLobbies() {
        return gameLobbies;
    }
    public List<MenuLobby> getMenuLobbies() {
        return menuLobbies;
    }
    private void addLobby(Lobby lobby){
        if(lobby instanceof GameLobby){
            gameLobbies.add((GameLobby) lobby);
        }
        else if(lobby instanceof MenuLobby){
            menuLobbies.add((MenuLobby) lobby);
        }
    }
    public void createNewMenuLobby(String name) {
        addLobby(new MenuLobby(name));
    }
    public void createNewGameLobby(String name){
        addLobby(new GameLobby(name));
    }
    public void addMenuLobbyPlayersToGameLobby(MenuLobby menuLobby, GameLobby gameLobby){
        for(Player player: menuLobby.getLobbyPlayers()){
            gameLobby.getLobbyPlayers().add(player);
        }

    }


}
