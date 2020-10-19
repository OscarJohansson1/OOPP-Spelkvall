package server.code.model;


import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    public String getLobbyTime() {
        return lobbyTime;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getLobbyCapacity() {
        return lobbyCapacity;
    }

    public String getPlayers() {
        return String.valueOf(players.size());
    }

    public List<Integer> getGridPane() {
        return gridPane;
    }


}
