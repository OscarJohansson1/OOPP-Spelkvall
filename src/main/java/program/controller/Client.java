package program.controller;

import program.model.Lobby;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private ClientController clientController;
    private boolean wait = true;

    public Client(){

    }
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
                        wait = false;
                        if(message instanceof String) {
                            System.out.println("Received string");
                            Client.this.clientController.lobby = new Lobby("yes");
                            System.out.println("Lobby is created");
                        }
                        else if(message instanceof Integer) {
                            clientController.showSelectedSpace((int)message);

                        }
                        else if(message instanceof Lobby){
                            clientController.lobby = (Lobby) message;
                            System.out.println("Recieved lobby");
                        }
                        System.out.println("Message Received: " + message);
                    }
                    catch(InterruptedException ignored){ }
                }
            }
        };
        messageHandling.setDaemon(true);
        messageHandling.start();
    }
    private class ConnectionToServer {
        BufferedReader in;
        PrintWriter out;
        private final ObjectOutputStream outObject;
        private final ObjectInputStream inObject;
        Socket socket;

        ConnectionToServer(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
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

    /*public List<Player> joinLobby(Player player) throws IOException, ClassNotFoundException {
        server.out.writeObject(player);
        List<Player> players = (List<Player>) server.in.readObject();
        System.out.println(players);
        return players;
    }*/
    public void selectSpace(int id) throws IOException {
        server.write(id);
    }
    public void deploy() throws IOException {
        server.write("deploy");
        server.out.flush();

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
    public Lobby getLobbys(ClientController clientController) throws IOException, ClassNotFoundException {
        sendObject(new Lobby("yes"));
        System.out.println("Sending a new lobby object to server");
        return clientController.lobby;
    }

    public String getString() throws IOException, ClassNotFoundException {
        server.out.println("LOBBYS");
        return (String) server.in.readLine();
    }
    public void sendObject(Object object) throws IOException {
        server.outObject.writeObject(object);
    }
    public void stopConnection() throws IOException {
        System.out.println("Terminating connection to server");
        server.socket.close();
    }
}
