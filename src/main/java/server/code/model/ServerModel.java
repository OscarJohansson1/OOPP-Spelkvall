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
        for(Lobby lobby: lobbys){
            System.out.println("Each lobby has " + lobby.users.size() + " users");
        }
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
                System.out.println("Received: existing lobby from client");
                System.out.println("Lobby has " + lobby.users.size() + " users");
                value.updateLobby(lobby);
                return value;
            }
        }
        lobbys.add(lobby);
        return lobby;
    }

}
