package program.model;

import program.controller.UserCard;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Lobby implements Serializable{

    String lobbyName;
    String lobbyTime;
    String lobbyCapacity;
    String lobbyCurrentUsers;
    public final List<User> users = new ArrayList<>();

    public Lobby(String name){
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        Date realDate = new Date();
        lobbyName = name;
        lobbyTime = date.format(new Timestamp(realDate.getTime()));
        lobbyCapacity = "16";
        lobbyCurrentUsers = "0";
    }
    public void addPlayer(User user){

        lobbyCurrentUsers = (Integer.parseInt(lobbyCurrentUsers) + 1) +"";
        users.add(user);
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
