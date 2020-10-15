package program.controller;

import program.model.Lobby;
import program.model.User;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private ClientController clientController;

    public void startConnection(String ip, int port, ClientController clientController) throws IOException {
        server = new ConnectionToServer(new Socket(ip,port));
        messages = new LinkedBlockingQueue<>();
        this.clientController = clientController;
        System.out.println("Connecting to 95.80.61.51, Port: 6666");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                stopConnection();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        Thread messageHandling = new Thread(){
            public void run(){
            while(true){
                try{
                    Object message = messages.take();
                    if(message instanceof String) {
                        System.out.println("Received string");
                    }
                    else if(message instanceof Integer) {
                        clientController.showSelectedSpace((int)message);

                    }
                    else if(message instanceof Lobby){
                        System.out.println("Recieved lobby with " + ((Lobby) message).users.size() + " users");
                        if(((Lobby)message).getLobbyId() == clientController.startController.lobbyReadyController.chosenLobby.getLobbyId()){
                            clientController.startController.lobbyReadyController.chosenLobby.updateLobby((Lobby) message);
                            clientController.startController.lobbyReadyController.updateUserCards();
                        }
                    }
                    else if(message instanceof List){
                        clientController.startController.lobbyReadyController.updateChoosenLobby((List<Lobby>) message);
                        clientController.startController.lobbySelectController.updateLobbys((List<Lobby>) message);
                        clientController.startController.lobbyReadyController.updateUserCards();
                    }
                }
                catch(InterruptedException ignored){ }
            }
            }
        };
        messageHandling.setDaemon(true);
        messageHandling.start();
    }
    private class ConnectionToServer {
        private final ObjectOutputStream outObject;
        private final ObjectInputStream inObject;
        Socket socket;

        ConnectionToServer(Socket socket) throws IOException {
            this.socket = socket;
            outObject = new ObjectOutputStream(socket.getOutputStream());
            inObject = new ObjectInputStream(socket.getInputStream());

            Thread read = new Thread(){
                public void run(){
                    while(true){
                        try{
                            Object obj = inObject.readObject();
                            System.out.println("Recieved object: " + obj);
                            messages.put(obj);
                        }
                        catch(IOException | InterruptedException | ClassNotFoundException e){ e.printStackTrace(); }
                    }
                }
            };

            read.setDaemon(true);
            read.start();
        }

        private void write(Object obj) throws IOException {
            outObject.writeObject(obj);
        }
    }

    public void selectSpace(int id) throws IOException {
        server.write(id);
    }
    public void deploy() throws IOException {
        server.write("deploy");

    }
    public void attack() throws IOException {
        server.write("attack");
    }
    public void move() throws IOException {
        server.write("move");
    }
    public void startGame() throws IOException {
        server.write("Start");
    }
    public void getLobbys() throws IOException, ClassNotFoundException {
        sendObject("LOBBYS");
        System.out.println("Sending LOBBYS to server");
    }

    public void updateLobby(Lobby lobby) throws IOException {
        System.out.println("Sending " + lobby + " to server");
        sendObject(lobby);
    }

    public void sendObject(Object object) throws IOException {
        server.outObject.writeObject(object);
    }
    public void stopConnection() throws IOException {
        System.out.println("Terminating connection to server");
        server.socket.close();
    }
}
