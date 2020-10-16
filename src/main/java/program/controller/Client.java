package program.controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.model.*;
import server.code.model.MenuLobby;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private ClientController clientController;
    private final LobbyItemCreator lobbyItemCreator = new LobbyItemCreator();

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
                        if(message.equals("startClear")){
                            clientController.startController.lobbyReadyController.startButton.setDisable(false);
                        }
                        else if(message.equals("StartGame")){
                            Stage stage = clientController.startController.stage;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Parent value = null;
                                    try {
                                        value = new MapController(clientController,stage);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Scene scene = new Scene(value, 1920, 1080);

                                    stage.setTitle("program.Chans");
                                    stage.setScene(scene);
                                    stage.show();
                                }
                            });

                        }
                        else if(message.equals("unmark1")){

                        }
                        else if(message.equals("unmark2")){

                        }
                        else {
                            if(lobbyItemCreator.getName() == null){
                                lobbyItemCreator.setName((String) message);
                            }
                            else if(lobbyItemCreator.getTime() == null){
                                lobbyItemCreator.setTime((String) message);
                            }
                            else if(lobbyItemCreator.getCapacity() == null){
                                lobbyItemCreator.setCapacity((String)message);
                                clientController.startController.lobbySelectController.lobbyItems.add(lobbyItemCreator.createLobbyItem(clientController.startController));
                                lobbyItemCreator.resetVariables();
                                clientController.startController.lobbySelectController.updateLobbys();
                            }
                        }
                    }
                    else if(message instanceof Integer) {
                        ModelDataHandler.getModelDataHandler().setCurrentPlayer(ModelDataHandler.getModelDataHandler().getPlayers().get((Integer)message));

                    }
                    else if(message instanceof List){
                        for (Object object : (List<?>) message) {
                            if (object instanceof LobbyItem) {
                                clientController.startController.lobbySelectController.updateLobbys();
                                break;
                            }
                            else if(object instanceof Player){
                                List<Player> playerList = (List<Player>)message;
                                ModelDataHandler.getModelDataHandler().setPlayers(playerList);
                                clientController.startController.lobbyReadyController.updateUserCards(playerList);
                            }

                        }
                    }
                    else if(message instanceof Board){
                        clientController.modelDataHandler.setBoard((Board) message);
                    }
                    else if(message instanceof Boolean){
                        if((Boolean) message) clientController.startController.lobbyReadyController.startButton.setVisible(true);
                    }
                    else if(message instanceof Space){
                        ModelDataHandler.getModelDataHandler().setSpace((Space)message);
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

    public void sendObject(Object object) throws IOException {
        server.outObject.writeObject(object);
    }

    public void stopConnection() throws IOException {
        System.out.println("Terminating connection to server");
        server.socket.close();
    }

}
