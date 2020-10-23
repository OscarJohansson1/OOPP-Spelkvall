package server.code.model.code.model;

import program.model.Player;

import java.util.ArrayList;
import java.util.List;


/**
 * A lobby consists of a list of players, the lobby's name and it's id.
 */
public abstract class Lobby {
    final String lobbyName;
    private int lobbyId;
    protected List<Player> players = new ArrayList<>();

    public Lobby(String name) {
        lobbyName = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public List<Player> getLobbyPlayers() {
        return players;
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }


}
