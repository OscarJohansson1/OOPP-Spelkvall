package server.code.model;

import program.model.Player;
import program.model.Lobby;

import java.util.ArrayList;
import java.util.List;

public class ServerModel {
    private final List<Lobby> lobbys = new ArrayList<>();
    private List<Player> players;
    private ServerModel(){initialize();}
    private static class ServerModelHolder{
        private static final ServerModel serverModel = new ServerModel();
    }
    public static ServerModel getModelDataHandler() { return ServerModelHolder.serverModel; }
    private void initialize() {
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

}
