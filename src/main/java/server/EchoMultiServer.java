package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import program.model.*;

public class EchoMultiServer {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true)
                new EchoClientHandler(serverSocket.accept()).start();

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

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private ObjectOutputStream outObject;
        private ObjectInputStream inObject;

        public EchoClientHandler(Socket socket) { this.clientSocket = socket; }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outObject = new ObjectOutputStream(clientSocket.getOutputStream());
                inObject = new ObjectInputStream(clientSocket.getInputStream());
                Object inputLine;
                while ((inputLine = inObject.readObject()) != null) {

                    System.out.println("Recieved: " + inputLine);
                    outObject.writeObject(inputLine);


                }

                in.close();
                out.close();
                clientSocket.close();

            } catch (IOException | ClassNotFoundException e) {

            }
        }
    }

    public static void main(String[] args) {
        EchoMultiServer server = new EchoMultiServer();
        server.start(6666);
    }

}