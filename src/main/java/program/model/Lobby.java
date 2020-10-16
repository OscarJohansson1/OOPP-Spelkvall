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

    final String lobbyName;
    final String lobbyTime;
    int lobbyCapacity;
    final int lobbyId;
    public List<User> users = new ArrayList<>();
    public User lobbyLeader;

    public Lobby(String name){
        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        Date realDate = new Date();
        lobbyName = name;
        lobbyTime = date.format(new Timestamp(realDate.getTime()));
        lobbyCapacity = 16;
        lobbyId = 1;
    }
    public void addPlayer(User user){
        if(users.size() == 0){
            lobbyLeader = user;
        }
        users.add(user);
    }
    public void updateLobby(Lobby lobby){
        lobbyCapacity = lobby.lobbyCapacity;
        users = lobby.users;
    }
    public String getLobbyTime() { return lobbyTime; }

    public String getLobbyName() {
        return lobbyName;
    }

    public int getLobbyCapacity() {
        return lobbyCapacity;
    }
    public List<User> getLobbyUsers() {
        return users;
    }
    public int getLobbyId(){return lobbyId;}


}
