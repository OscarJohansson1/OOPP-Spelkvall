package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import program.model.*;
import server.code.ServerController;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final ServerController serverController = new ServerController();

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
        private Lobby lobby;
        ObjectOutputStream outObject;
        ObjectInputStream inObject;

        public ClientHandler(Socket socket) { this.clientSocket = socket; }

        public void run() {
            try {
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                while ((inputLine = inObject.readObject()) != null) {
                    System.out.println("Recieved: " + inputLine);
                    if(inputLine instanceof Lobby){
                        lobby = (Lobby) inputLine;
                        writeToAll(serverController.updateLobby((Lobby)inputLine), outObject,lobby);
                    }
                    else if(inputLine instanceof String){

                        if(inputLine.equals("LOBBYS")){
                            System.out.println("Returning: " + serverController.getLobbys());
                            outObject.writeObject(serverController.getLobbys());
                        }
                        else if(inputLine.equals("startGame")){
                            writeToAll(lobby.getLobbyUsers(),outObject,lobby);
                            serverController.initializeServerBoardController(lobby);
                            writeToAll(serverController.getGameBoard(), outObject, lobby);
                            writeToRandomClientInLobby(lobby);
                        }
                    }
                    else if(inputLine instanceof Space){
                        writeToAll(inputLine, outObject, lobby);
                    }

                }
            } catch (IOException | ClassNotFoundException ignored) {

            }
        }

        private void writeToAll(Object input, ObjectOutputStream outObject, Lobby lobby) throws IOException {
            for(ClientHandler client: clients) {
                if(client.lobby.getLobbyName().equals(lobby.getLobbyName())){
                    System.out.println("Sending " + input +" to client" + client + " in lobby " + lobby.getLobbyName());
                    outObject.writeObject(input);
                    outObject.flush();
                }
            }
        }
        private void writeToRandomClientInLobby( Lobby lobby) throws IOException {
            List<ClientHandler> clientHandlers = new ArrayList<>();
            for(ClientHandler clientHandler: clients){
                if(clientHandler.lobby.getLobbyName().equals(lobby.getLobbyName())){
                    clientHandlers.add(clientHandler);
                }
            }
            clientHandlers.get(new Random().nextInt(clientHandlers.size())).outObject.writeObject(true);
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }

}