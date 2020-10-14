package program.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Lobby implements Serializable{

    String lobbyName;
    String lobbyTime;
    String lobbyCapacity;
    String lobbyCurrentUsers;

    public Lobby(String name){
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        Date realDate = new Date();
        lobbyName = name;
        lobbyTime = date.format(new Timestamp(realDate.getTime()));
        lobbyCapacity = "16";
        lobbyCurrentUsers = "0";
    }
    public void addPlayer(){

        lobbyCurrentUsers = (Integer.parseInt(lobbyCurrentUsers) + 1) +"";

    }

    public String getLobbyTime() { return lobbyTime; }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getLobbyCapacity() {
        return lobbyCapacity;
    }
    public String getLobbyUsers() {
        return lobbyCurrentUsers;
    }

}
