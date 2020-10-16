package server.code;

import program.controller.Client;
import program.controller.LobbyItem;
import program.model.Lobby;
import server.EchoMultiServer;

import java.util.ArrayList;
import java.util.List;

public class LobbyListController {
    private final List<Lobby> lobbies = new ArrayList<>();
    public List<Lobby> getLobbies(){
        return lobbies;
    }
    public Lobby updateLobby(Lobby lobby){
        for (Lobby value : lobbies) {
            if (value.getLobbyName().equals(lobby.getLobbyName())) {
                value.updateLobby(lobby);
                return value;
            }
        }
        lobbies.add(lobby);
        return lobby;
    }

}
