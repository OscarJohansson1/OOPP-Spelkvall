package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import program.model.*;
import server.code.model.ServerModel;

public class EchoMultiServer {

    private ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final ServerModel serverModel = new ServerModel();

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

    private class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) { this.clientSocket = socket; }

        public void run() {
            try {
                ObjectOutputStream outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                while ((inputLine = inObject.readObject()) != null) {
                    System.out.println("Recieved: " + inputLine);
                    if(inputLine instanceof Lobby){
                        Lobby lobby = serverModel.updateLobby((Lobby)inputLine);
                        System.out.println("Returning "+lobby+" with " + lobby.users.size() + " users");
                        writeToAll(lobby, outObject);
                    }
                    else if(inputLine instanceof String){
                        if(inputLine.equals("LOBBYS")){
                            System.out.println("Returning: " + serverModel.getLobbys());
                            outObject.writeObject(serverModel.getLobbys());
                        }
                    }

                }
            } catch (IOException | ClassNotFoundException ignored) {

            }
        }

        private void writeToAll(Object input, ObjectOutputStream outObject) throws IOException {
            for(ClientHandler client: clients) {
                System.out.println("Sending " + input +" to client" + client);
                outObject.writeObject(input);
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }

}