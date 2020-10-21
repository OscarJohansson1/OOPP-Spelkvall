package server.code.model;

import program.model.Player;
import java.util.ArrayList;
import java.util.List;

public abstract class Lobby {
    final String lobbyName;
    public int lobbyId;
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

    public boolean checkIfAllPlayersReady() {
        if(players.size() > 1){
            for (Player player : players) {
                if (!player.isReady()) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        return true;
    }
}
