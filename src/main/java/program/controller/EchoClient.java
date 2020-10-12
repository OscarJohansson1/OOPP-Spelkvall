package program.controller;

import program.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class EchoClient {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private MapController mapController;

    private EchoClient(){}
    private static class EchoClientHolder{
        private static final EchoClient echoClient = new EchoClient();
    }
    public void recieveController(MapController mapController) {
        this.mapController = mapController;
    }
    public static EchoClient getEchoClient() { return EchoClientHolder.echoClient; }
    public void startConnection(String ip, int port) throws IOException {
        server = new ConnectionToServer(new Socket(ip,port));
        messages = new LinkedBlockingQueue<>();


        Thread messageHandling = new Thread() {
            public void run(){
                while(true){
                    try{
                        Object message = messages.take();
                        // Do some handling here...
                        switch ((String)message) {
                            case "deploy":
                                mapController.attack();
                                break;
                            case "attack":
                                break;
                            case "move":
                                break;
                        }
                        System.out.println("Message Received: " + message);
                    }
                    catch(InterruptedException e){ }
                }
            }
        };
        messageHandling.setDaemon(true);
        messageHandling.start();
    }
    private class ConnectionToServer {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket socket;

        ConnectionToServer(Socket socket) throws IOException {
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            Thread read = new Thread(){
                public void run(){
                    while(true){
                        try{
                            Object obj = in.readObject();
                            messages.put(obj);
                        }
                        catch(IOException | ClassNotFoundException | InterruptedException e){ e.printStackTrace(); }
                    }
                }
            };

            read.setDaemon(true);
            read.start();
        }

        private void write(Object obj) {
            try{
                out.writeObject(obj);
            }
            catch(IOException e){ e.printStackTrace(); }
        }


    }
    public List<Player> joinLobby(Player player) throws IOException, ClassNotFoundException {
        server.out.writeObject(player);
        List<Player> players = (List<Player>) server.in.readObject();
        System.out.println(players);
        return players;
    }
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

    public void stopConnection() throws IOException {
        server.in.close();
        server.out.close();
        server.socket.close();
    }
}
