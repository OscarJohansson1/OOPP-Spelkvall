package server.code.model;


import program.model.Player;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Lobby for while sitting in waiting lobby before game starts.
 */
public class MenuLobby extends Lobby implements Serializable {

    final String lobbyTime;
    String lobbyCapacity;
    private final List<Integer> gridPane = new ArrayList<>();

    public MenuLobby(String name) {
        super(name);
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        Date realDate = new Date();
        lobbyTime = date.format(new Timestamp(realDate.getTime()));
        lobbyCapacity = "16";
    }

    /**
     * @return returns the list of ints correlated to the logos that are already chosen
     */
    public List<Integer> getGridPane() {
        return gridPane;
    }

    /**
     * @return true or false depending on if all players are ready
     */
    public boolean checkIfAllPlayersReady() {
        if (players.size() > 1) {
            for (Player player : players) {
                if (!player.isReady()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public String getLobbyTime() {
        return lobbyTime;
    }

    public String getLobbyCapacity() {
        return lobbyCapacity;
    }

    public String getPlayers() {
        return String.valueOf(players.size());
    }


}
