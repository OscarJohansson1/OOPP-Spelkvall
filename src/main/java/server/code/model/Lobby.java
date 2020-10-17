package server.code.model;

import program.controller.PlayerCard;
import program.model.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Lobby {
    final String lobbyName;
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
        for (Player player : players) {
            if (!player.isReady()) {
                return false;
            }
        }
        return true;
    }
}
