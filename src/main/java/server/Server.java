package server;

import program.model.*;
import program.model.ModelDataHandler;
import program.model.Player;
import server.code.model.ServerModel;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    public final List<ClientHandler> clients = new ArrayList<>();
    private Socket socket;
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true){
            ClientHandler echoClientHandler = new ClientHandler(serverSocket.accept());
            echoClientHandler.start();
            clients.add(echoClientHandler);
            clients.removeIf(clientHandler -> !clientHandler.isAlive());
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private class ClientHandler extends Thread {
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private ObjectOutputStream outObject;
        private ObjectInputStream inObject;
        private final ServerModel serverModel = ServerModel.getModelDataHandler();
        private final ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inObject = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    if(checkInput(inObject.readObject())) break;
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

        private boolean checkInput(Object input) throws IOException {
            if (input != null) {
                System.out.println("Recieved: " + input);
                if(input instanceof String){
                    switch ((String) input) {
                        case "SELECTSPACE":

                            break;
                        case "LOBBYS":
                            System.out.println("Writing " + serverModel.getLobbys() +" to client");
                            writeToAll(serverModel.getLobbys());

                            break;
                        default:
                            System.out.println("Writing " + input);
                            writeToAll(input);
                            break;

                    }
                }
                else {
                    outObject.writeObject(input);
                }
            }
            return true;
        }
        private void writeToAll(Object input) throws IOException {
            for(ClientHandler client: clients) {
                client.outObject.writeObject(input);
            }
        }

        public void stopConnection() throws IOException {
            System.out.println("Terminating");
            in.close();
            out.close();
            clientSocket.close();
        }
    }
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(6666);

    }
}


