package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import program.model.*;
import server.code.controller.LobbyController;
import server.code.model.GameLobby;
import server.code.model.Lobby;
import server.code.model.MenuLobby;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final LobbyController lobbyController = new LobbyController();

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
                    }

                } else if (inputLine.equals("LOBBYLEADER")) {
                    if (menuLobby.getLobbyPlayers().size() == 1) {
                        outObject.writeObject(true);
                    }
                } else if (inputLine.equals("startGame")) {
                    lobbyController.createNewGameLobby(menuLobby.getLobbyName());
                    gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                    lobbyController.getMenuLobbies().remove(menuLobby);
                    lobbyController.createNewMenuLobby("Ajjemän");
                    addClientsToGameLobby(gameLobby);
                    writeToAllInLobby(gameLobby.initializeBoard(), gameLobby);
                    writeToAllInLobby("startGame", gameLobby);
                    writeToAllInLobby(gameLobby.nextPlayer(), gameLobby);
                } else if (inputLine.equals("resetColor")) {
                    writeToAllInLobby("resetColor", gameLobby);
                } else if (inputLine.equals("resetSelectedSpaces")) {
                    writeToAllInLobby("resetSelectedSpaces", gameLobby);
                } else if (inputLine.equals("nextPlayer")) {
                    writeToAllInLobby(gameLobby.nextPlayer(), gameLobby);
                } else if (inputLine.equals("gridPane")) {
                    outObject.writeObject(new ArrayList<>(menuLobby.getGridPane()));
                } else if (inputLine.equals("removeAttackView")) {
                    writeToAllInLobby("removeAttackView", gameLobby);
                } else if (inputLine.equals("nextPhase")) {
                    writeToAllInLobby("nextPhase", gameLobby);
                }
            } else if (inputLine instanceof Space) {
                writeToAllInLobby(inputLine, gameLobby);
            } else if (inputLine instanceof Boolean) {
                player.setReady(!player.isReady());
                writeToLobbyLeaderInLobby(menuLobby, menuLobby.checkIfAllPlayersReady());
            } else if (inputLine instanceof Player) {
                Player receivedPlayer = (Player) inputLine;
                if (player == null) {
                    player = receivedPlayer;
                }
                if (menuLobby != null) {
                    menuLobby.addPlayer(receivedPlayer);
                    receivedPlayer.setId(menuLobby.getLobbyPlayers().size() - 1);
                    writeToAllInLobby(new ArrayList<>(menuLobby.getLobbyPlayers()), menuLobby);
                    outObject.writeObject(menuLobby.getLobbyPlayers().indexOf(receivedPlayer));
                } else {
                    writeToAllInLobby(inputLine, gameLobby);
                }

            } else if (inputLine instanceof Integer) {
                if (menuLobby == null && gameLobby == null) {
                    menuLobby = lobbyController.getMenuLobbies().get((Integer) inputLine);
                } else if (menuLobby != null) {
                    menuLobby.getGridPane().add((Integer) inputLine);
                } else {
                    writeToAllInLobby(inputLine, gameLobby);
                }
            } else if (inputLine instanceof List) {
                writeToAllInLobby(inputLine, gameLobby);
            } else if (inputLine instanceof Attack) {
                writeToAllInLobby(inputLine, gameLobby);
            }
        }

        private void addClientsToGameLobby(GameLobby gameLobby) {
            lobbyController.addMenuLobbyPlayersToGameLobby(menuLobby, gameLobby);
            for (ClientHandler clientHandler : clients) {
                if (clientHandler.menuLobby.getLobbyName().equals(gameLobby.getLobbyName())) {
                    clientHandler.gameLobby = lobbyController.getGameLobbies().get(lobbyController.getGameLobbies().size() - 1);
                    clientHandler.menuLobby = null;
                }
            }
        }

        private void writeToAllInLobby(Object input, Lobby lobby) throws IOException {
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
                    if (clientHandler.gameLobby.getLobbyName().equals(lobby.getLobbyName())) {
                        clientHandlers.add(clientHandler);
                    }
                } else if (lobby instanceof MenuLobby) {
                    if (clientHandler.menuLobby.getLobbyName().equals(lobby.getLobbyName())) {
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