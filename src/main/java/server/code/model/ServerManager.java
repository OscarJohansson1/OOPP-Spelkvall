package server.code.model;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import program.model.*;
import server.code.model.code.controller.LobbyController;
import server.code.model.code.model.GameLobby;
import server.code.model.code.model.Lobby;
import server.code.model.code.model.MenuLobby;

/**
 * Main server class
 */
public class ServerManager {

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final LobbyController lobbyController = new LobbyController();

    /**
     * Starts the server and waits for someone to connect.
     *
     * @param port port that is used to connect to the server.
     */
    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                clientHandler.start();
                clients.add(clientHandler);
                clients.removeIf(ClientHandler -> !ClientHandler.isAlive());
                System.out.println(clients);
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

    /**
     * Class for handling clients
     */
    public class ClientHandler extends Thread {
        private final Socket clientSocket;
        private GameLobby gameLobby;
        private MenuLobby menuLobby;
        ObjectOutputStream outObject;
        ObjectInputStream inObject;
        private Player player;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        /**
         * Starts the thread and wait's for input
         */
        public void run() {
            try {
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                while ((inputLine = inObject.readObject()) != null) {
                    handleObject(inputLine);
                }
            } catch (IOException | ClassNotFoundException ignored) {
            }
        }

        /**
         * Handles the received input and does something depending on what the object is
         *
         * @param inputLine received input
         */
        private void handleObject(Object inputLine) throws IOException {
            System.out.println("Recieved: " + inputLine);
            if (inputLine instanceof String) {
                if (inputLine.equals("LOBBYS")) {
                    for (MenuLobby menuLobby : lobbyController.getMenuLobbies()) {
                        List<String> strings = new ArrayList<>();
                        strings.add(menuLobby.getLobbyName());
                        strings.add(menuLobby.getLobbyTime());
                        strings.add(menuLobby.getLobbyCapacity());
                        strings.add(menuLobby.getPlayers());
                        outObject.writeObject(strings);
                        this.menuLobby = null;
                    }
                    return;
                } else if (inputLine.equals("LOBBYLEADER")) {
                    if (menuLobby.getLobbyPlayers().size() == 1) {
                        outObject.writeObject(true);
                    }
                    return;
                } else if (inputLine.equals("startGame")) {
                    lobbyController.createNewGameLobby(menuLobby.getLobbyName());
                    gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                    lobbyController.getMenuLobbies().remove(menuLobby);
                    lobbyController.createNewMenuLobby("Ajjemän");
                    addClientsToGameLobby(gameLobby);
                    writeToAllInLobbyControlled(gameLobby.initializeBoard(), gameLobby);
                    writeToAllInLobbyControlled("startGame", gameLobby);
                    writeToAllInLobbyControlled(gameLobby.nextPlayer(), gameLobby);
                    return;
                } else if (inputLine.equals("nextPlayer")) {
                    writeToAllInLobbyControlled(gameLobby.nextPlayer(), gameLobby);
                    return;
                } else if (inputLine.equals("gridPane")) {
                    outObject.writeObject(new ArrayList<>(menuLobby.getGridPane()));
                    return;
                } else if (inputLine.equals("removePlayer")) {
                    for (Player player : menuLobby.getLobbyPlayers()) {
                        assert this.player != null;
                        if (this.player.getId() == player.getId()) {
                            removePlayerFromLobby(menuLobby, this.player);
                            menuLobby.getGridPane().remove(menuLobby.getGridPane().get(this.player.getId()));
                            this.player = null;
                            menuLobby = null;
                            break;
                        }
                    }
                    return;
                }
            } else if (inputLine instanceof Boolean) {
                player.setReady(!player.isReady());
                writeToLobbyLeaderInLobby(menuLobby, menuLobby.checkIfAllPlayersReady());
                return;
            } else if (inputLine instanceof Player) {
                Player receivedPlayer = new Player((Player) inputLine);
                if (player == null) {
                    player = receivedPlayer;
                }
                if (menuLobby != null) {
                    menuLobby.addPlayer(receivedPlayer);
                    receivedPlayer.setId(menuLobby.getLobbyPlayers().size() - 1);
                    writeToAllInLobbyControlled(new ArrayList<>(menuLobby.getLobbyPlayers()), menuLobby);
                    outObject.writeObject(menuLobby.getLobbyPlayers().indexOf(receivedPlayer));
                    return;
                }
            } else if (inputLine instanceof Integer) {
                if (menuLobby == null && gameLobby == null) {
                    menuLobby = lobbyController.getMenuLobbies().get((Integer) inputLine);
                    return;
                } else if (menuLobby != null) {
                    menuLobby.getGridPane().add((Integer) inputLine);
                    writeToAllInLobbyNoControl(menuLobby.getGridPane(), menuLobby);
                    return;
                }
            }
            writeToAllInLobbyControlled(inputLine, gameLobby);
        }

        private void removePlayerFromLobby(Lobby lobby, Player player) throws IOException {
            for (ClientHandler clientHandler : clients) {
                if (clientHandler.menuLobby != null && clientHandler.menuLobby.getLobbyId() == lobby.getLobbyId()) {
                    clientHandler.menuLobby.getLobbyPlayers().remove(player);
                }
            }
            writeToAllInLobbyControlled(lobby.getLobbyPlayers(), lobby);
        }

        private void addClientsToGameLobby(GameLobby gameLobby) {
            lobbyController.addMenuLobbyPlayersToGameLobby(menuLobby, gameLobby);
            for (ClientHandler clientHandler : clients) {
                if (clientHandler.menuLobby != null && clientHandler.menuLobby.getLobbyId() == gameLobby.getLobbyId()) {
                    clientHandler.gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                    clientHandler.menuLobby = null;
                }
            }
        }

        private void writeToAllInLobbyControlled(Object input, Lobby lobby) throws IOException {
            for (ClientHandler client : findClientsInLobby(lobby)) {
                if (client.player != null) {
                    client.outObject.writeObject(input);
                }
            }
        }

        private void writeToAllInLobbyNoControl(Object input, Lobby lobby) throws IOException {
            for (ClientHandler client : findClientsInLobby(lobby)) {
                client.outObject.writeObject(input);
            }
        }

        private void writeToLobbyLeaderInLobby(Lobby lobby, boolean b) throws IOException {
            if (b) {
                findClientsInLobby(lobby).get(0).outObject.writeObject("startClear");
            } else {
                findClientsInLobby(lobby).get(0).outObject.writeObject("startNotClear");
            }

        }

        private List<ClientHandler> findClientsInLobby(Lobby lobby) {
            List<ClientHandler> clientHandlers = new ArrayList<>();
            for (ClientHandler clientHandler : clients) {
                if (lobby instanceof GameLobby) {
                    if (clientHandler.gameLobby != null && clientHandler.gameLobby.getLobbyId() == gameLobby.getLobbyId()) {
                        clientHandlers.add(clientHandler);
                    }
                } else if (lobby instanceof MenuLobby) {
                    if (clientHandler.menuLobby != null && clientHandler.menuLobby.getLobbyId() == menuLobby.getLobbyId()) {
                        clientHandlers.add(clientHandler);
                    }
                }
            }
            return clientHandlers;
        }

    }

    public static void main(String[] args) {
        ServerManager server = new ServerManager();
        server.start(6666);
    }

}