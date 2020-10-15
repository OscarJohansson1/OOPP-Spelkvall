package server.code.model;

import program.model.Player;
import program.model.Lobby;

import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private final List<Lobby> lobbys = new ArrayList<>();
    public ServerModel(){
        createNewLobby();
    }
    public Lobby getLobbyHandler(int id) {
        return lobbys.get(id);
    }
    public List<Lobby> getLobbys() {
        return lobbys;
    }
    public void createNewLobby() {
        lobbys.add(new Lobby("yes"));
    }
    public void addLobby(Lobby lobby){
        lobbys.add(lobby);
    }
    public void updateLobby(Lobby lobby){
        for(Lobby lobby1: lobbys) {
            if (lobby1.getLobbyId() == lobby.getLobbyId()) {
                System.out.println("Received: existing lobby from client");
                lobby1.updateLobby(lobby);
                return;
            }
        }
        lobbys.add(lobby);
    }

}
