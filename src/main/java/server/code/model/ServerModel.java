package server.code.model;

import program.model.Player;
import program.model.Lobby;

import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private List<Lobby> lobbys = new ArrayList<>();
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
    public Lobby updateLobby(Lobby lobby){
        for (Lobby value : lobbys) {
            if (value.getLobbyId() == lobby.getLobbyId()) {
                value.updateLobby(lobby);
                return value;
            }
        }
        lobbys.add(lobby);
        return lobby;
    }

}
