package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Menu;
import program.model.*;
import server.code.controller.LobbyController;
import server.code.controller.GameController;
import server.code.model.GameLobby;
import server.code.model.Lobby;
import server.code.model.MenuLobby;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final LobbyController lobbyController = new LobbyController();
    private final GameController serverBoardController = new GameController();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                clientHandler.start();
                clients.add(clientHandler);
                System.out.println("Adding " + clientHandler + " to client list");
                clients.removeIf(ClientHandler -> !ClientHandler.isAlive());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }

    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class ClientHandler extends Thread {
        private final Socket clientSocket;
        private GameLobby gameLobby;
        private MenuLobby menuLobby;
        ObjectOutputStream outObject;
        ObjectInputStream inObject;
        private Player player;

        public ClientHandler(Socket socket) { this.clientSocket = socket; }

        public void run() {
            try {
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                while ((inputLine = inObject.readObject()) != null) {
                    System.out.println("Recieved: " + inputLine);
                    if(inputLine instanceof String){

                        if(inputLine.equals("LOBBYS")){
                            System.out.println("Returning: " + lobbyController.getMenuLobbies());
                            for(MenuLobby menuLobby: lobbyController.getMenuLobbies()){
                                outObject.writeObject(menuLobby.getLobbyName());
                                outObject.writeObject(menuLobby.getLobbyTime());
                                outObject.writeObject(menuLobby.getLobbyCapacity());
                            }

                        }
                        else if(inputLine.equals("LOBBYLEADER")){
                            if(menuLobby.getLobbyPlayers().size() == 1){
                                outObject.writeObject(true);
                            }
                        }
                        else if(inputLine.equals("startGame")){
                            lobbyController.createNewGameLobby(menuLobby.getLobbyName());
                            gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                            addClientsToGameLobby(gameLobby);
                            serverBoardController.initializeGame(gameLobby.getLobbyPlayers());
                            writeToAllInLobby(serverBoardController.getBoard(), gameLobby);
                            writeToAllInLobby(0, gameLobby);
                            writeToAllInLobby("StartGame", gameLobby);
                        }
                        else if(inputLine.equals("unmark1")){

                        }
                        else if(inputLine.equals("unmark2")){

                        }
                    }
                    else if(inputLine instanceof Space){
                        writeToAllInLobby(inputLine, gameLobby);
                    }
                    else if(inputLine instanceof Boolean){
                        player.setReady((Boolean) inputLine);
                        if(menuLobby.checkIfAllPlayersReady()){
                            writeToSpecificClientInLobby(menuLobby, 0);
                        }
                    }
                    else if(inputLine instanceof Player){
                        if(player == null)player = (Player) inputLine;
                        menuLobby.addPlayer(player);
                        writeToAllInLobby(menuLobby.getLobbyPlayers(), menuLobby);
                    }
                    else if(inputLine instanceof  Integer){
                        menuLobby = lobbyController.getMenuLobbies().get((Integer) inputLine);
                    }

                }
            } catch (IOException | ClassNotFoundException ignored) {

            }
        }

        public void addClientsToGameLobby(GameLobby gameLobby){
            for(ClientHandler clientHandler: clients){
                if(clientHandler.menuLobby.getLobbyName().equals(gameLobby.getLobbyName())){
                    clientHandler.gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                }
            }
            lobbyController.addMenuLobbyPlayersToGameLobby(menuLobby,gameLobby);
        }

        private void writeToAllInLobby(Object input, Lobby lobby) throws IOException {
            for(ClientHandler client: findClientsInLobby(lobby)) {
                outObject.writeObject(input);
            }
        }

        private void writeToRandomClientInLobby( Lobby lobby) throws IOException {
            List<ClientHandler> clientHandlers = findClientsInLobby(lobby);
            clientHandlers.get(new Random().nextInt(clientHandlers.size())).outObject.writeObject(true);
        }

        private void writeToSpecificClientInLobby(Lobby lobby, int id) throws IOException {
            findClientsInLobby(lobby).get(id).outObject.writeObject("startClear");
        }

        private List<ClientHandler> findClientsInLobby(Lobby lobby){
            List<ClientHandler> clientHandlers = new ArrayList<>();
            for(ClientHandler clientHandler: clients){
                if(lobby instanceof GameLobby){
                    if(clientHandler.gameLobby.getLobbyName().equals(lobby.getLobbyName())){
                        clientHandlers.add(clientHandler);
                    }
                }
                else if(lobby instanceof MenuLobby){
                    if(clientHandler.menuLobby.getLobbyName().equals(lobby.getLobbyName())){
                        clientHandlers.add(clientHandler);
                    }
                }
            }
            return clientHandlers;
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }

}