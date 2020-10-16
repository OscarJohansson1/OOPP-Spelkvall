package server.code;

import program.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerController {
    private Player currentPlayer;
    private final List<Player> players = new ArrayList<>();
    public void getRandomPlayer(){
        players.get(new Random().nextInt(players.size())).setMyTurn(true);
    }
    public List<Player> getPlayers(){
        return players;
    }
}
