package server.code;

import program.model.Board;
import program.model.Player;
import program.model.Lobby;

import java.util.List;

public class ServerController {
    private LobbyListController lobbyController = new LobbyListController();
    private ServerBoardController serverBoardController = new ServerBoardController();
    private PlayerController playerController = new PlayerController();

    public ServerController(){
        createNewLobby();
    }

    public void createNewLobby() {
        lobbyController.getLobbies().add(new Lobby("yes"));
    }

    public void addLobby(Lobby lobby){
        lobbyController.getLobbies().add(lobby);
    }
    public Lobby updateLobby(Lobby lobby){
        return lobbyController.updateLobby(lobby);
    }

    public Player getNextPlayer(Player player){
        for(Player player1: playerController.getPlayers()){
            if(player1.getMyTurn()){
                return player1;
            }
        }
        return null;
    }
    public Board getGameBoard(){
        return serverBoardController.getBoard();
    }
    public void initializeServerBoardController(Lobby lobby){
        serverBoardController.initialize(lobby.users);
    }
    public void initializeGame(Lobby lobby){
        if(lobbyController.getLobbies().contains(lobby)){

        }
    }

    public List<Lobby> getLobbys() {
        return lobbyController.getLobbies();
    }



}
