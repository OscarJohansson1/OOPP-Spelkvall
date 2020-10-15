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
    public final List<ClientHandler> clients = new ArrayList<>();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                ClientHandler clientHandler = new ClientHandler(serverSocket.accept());
                clientHandler.start();
                clients.add(clientHandler);
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
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private ObjectOutputStream outObject;
        private ObjectInputStream inObject;
        private final ServerModel serverModel = ServerModel.getModelDataHandler();

        public ClientHandler(Socket socket) { this.clientSocket = socket; }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                writeToAll(serverModel.getLobbys());
                while ((inputLine = inObject.readObject()) != null) {
                    if(inputLine instanceof Lobby){
                        serverModel.updateLobby((Lobby)inputLine);
                        writeToAll(serverModel.getLobbys());
                    }
                    else if(inputLine instanceof String){
                        if(((String) inputLine).equals("LOBBYS")){
                            writeToAll(serverModel.getLobbys());
                        }
                    }
                    System.out.println("Recieved: " + inputLine);
                    outObject.writeObject(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException | ClassNotFoundException e) {

            }
        }

        private void writeToAll(Object input) throws IOException {
            for(ClientHandler client: clients) {
                client.outObject.writeObject(input);
            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }

}