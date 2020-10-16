package server.code.model;

import program.controller.PlayerCard;
import program.model.Player;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class MenuLobby extends Lobby implements Serializable {

    final String lobbyTime;
    String lobbyCapacity;
    public Player lobbyLeader;
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



}
