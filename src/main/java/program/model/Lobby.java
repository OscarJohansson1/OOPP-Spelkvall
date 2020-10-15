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
    int lobbyCapacity;
    int lobbyCurrentUsers;
    final int lobbyId;
    public List<User> users = new ArrayList<>();

    public Lobby(String name){
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        Date realDate = new Date();
        lobbyName = name;
        lobbyTime = date.format(new Timestamp(realDate.getTime()));
        lobbyCapacity = 16;
        lobbyCurrentUsers = 0;
        lobbyId = 1;
    }
    public void addPlayer(User user){
        users.add(user);
        lobbyCurrentUsers = users.size();
    }
    public void updateLobby(Lobby lobby){
        System.out.println("Updating lobby in lobby class");
        lobbyCapacity = lobby.lobbyCapacity;
        lobbyCurrentUsers = lobby.lobbyCurrentUsers;
        users = lobby.users;
        System.out.println("Lobby users is " + users.size());
    }
    public String getLobbyTime() { return lobbyTime; }

    public String getLobbyName() {
        return lobbyName;
    }

    public int getLobbyCapacity() {
        return lobbyCapacity;
    }
    public int getLobbyUsers() {
        return lobbyCurrentUsers;
    }
    public int getLobbyId(){return lobbyId;}


}
