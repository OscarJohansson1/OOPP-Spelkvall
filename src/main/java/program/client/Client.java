package program.client;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import program.controller.LobbyItem;
import program.controller.LobbyItemCreator;
import program.controller.MapController;
import program.controller.StartController;
import program.model.*;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private StartController startController;
    private MapController mapController;
    private Player player;
    private final LobbyItemCreator lobbyItemCreator = new LobbyItemCreator();
    private final ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
    public boolean startedConnection = false;

    private Client() {
    }

    private static class ClientHolder {
        private static Client client;
        public static Client getClient(){
            if(client == null){
                return client = new Client();
            }
            return client;
        }
    }

    public static Client getClient() {
        System.out.println(ClientHolder.getClient());
        return ClientHolder.getClient();
    }

    public void startConnection(String ip, int port, StartController startController) throws IOException {
        server = new ConnectionToServer(new Socket(ip, port));
        messages = new LinkedBlockingQueue<>();
        this.startController = startController;
        System.out.println("Connecting to 95.80.61.51, Port: 6666");
        startedConnection = true;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                stopConnection();
                System.exit(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        Thread messageHandling = new Thread(() -> {
            while (true) {
                try {
                    Object message;
                    message = messages.take();
                    handleObject(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        messageHandling.setDaemon(true);
        messageHandling.start();
    }

    private void handleObject(Object message) {
        try {
            if (message instanceof String) {
                if (message.equals("startClear")) {
                    startController.lobbyReadyController.startButton.setDisable(false);
                } else if (message.equals("startGame")) {
                    Stage stage = startController.stage;
                    mapController = new MapController(stage);
                    player.setMyTurn(modelDataHandler.getCurrentPlayer().getId() == player.getId());
                    Platform.runLater(() -> {
                        Scene scene = new Scene(mapController, 1920, 1080);
                        stage.setTitle("program.Chans");
                        stage.setScene(scene);
                        stage.show();
                    });

                } else if (message.equals("startNotClear")) {
                    startController.lobbyReadyController.startButton.setDisable(true);
                } else if (message.equals("resetColor")) {
                    mapController.resetColorOnline();
                } else if (message.equals("nextPhase")) {
                    modelDataHandler.round.nextPhase();
                } else if (message.equals("removeAttackView")) {
                    mapController.removeOnlineAttackView();
                }
            } else if (message instanceof Integer) {
                if (modelDataHandler.getCurrentPlayer() == null) {
                    modelDataHandler.setCurrentPlayer(modelDataHandler.getPlayers().get((Integer) message));
                }
                player.setId((Integer) message);

            } else if (message instanceof List) {
                for (Object object : (List<?>) message) {
                    if (object instanceof Player) {
                        List<Player> playerList = (List<Player>) message;
                        modelDataHandler.setPlayers(playerList);
                        startController.lobbyReadyController.updateUserCards(playerList);
                    } else if (object instanceof Integer) {
                        if (mapController == null) {
                            startController.multiplayerLogoController.updateGridPane((List<Integer>) message);
                        }
                    } else if (object instanceof String) {
                        lobbyItemCreator.setVariables((String) object);
                        LobbyItem lobbyItem = lobbyItemCreator.createLobbyItem(startController);
                        if (lobbyItem != null) {
                            startController.lobbySelectController.lobbyItems.add(lobbyItem);
                            startController.lobbySelectController.updateLobbies();
                        }
                    }
                }
            } else if (message instanceof Board) {
                modelDataHandler.setBoard((Board) message);
            } else if (message instanceof Boolean) {
                if ((Boolean) message)
                    startController.lobbyReadyController.startButton.setVisible(true);
            } else if (message instanceof Space) {
                Space space = (Space) message;
                modelDataHandler.receiveOnlineSelectedSpace(space);
                mapController.view.updateTextUnits(space.getId(), space.getUnits(), mapController.allButtons, mapController);
                mapController.view.setColor(mapController.getCube(space.getId()), Color.web(modelDataHandler.getColorOnSpace(space.getId())).darker().darker(), mapController.allButtons);

            } else if (message instanceof Player) {
                modelDataHandler.setCurrentPlayer((Player) message);
                Player currentPlayer = modelDataHandler.getCurrentPlayer();
                mapController.view.updateCurrentPlayer(currentPlayer.getColor(), mapController, currentPlayer.getName());
                mapController.view.updateDeployableUnits(mapController.deployableUnitsText, ((Player) message).getUnits());
                if (player.getId() == ((Player) message).getId()) {
                    player.setMyTurn(true);
                    mapController.view.myturn(mapController);
                } else {
                    player.setMyTurn(false);
                    mapController.view.otherPlayerPlaying(mapController);
                }

            } else if (message instanceof Attack) {
                mapController.changeToOnlineAttackView();
                mapController.attackController.attack((Attack) message);
            }
        } catch (IOException ignored) {
        }
    }

    private class ConnectionToServer {
        private final ObjectOutputStream outObject;
        private final ObjectInputStream inObject;
        Socket socket;

        ConnectionToServer(Socket socket) throws IOException {
            this.socket = socket;
            outObject = new ObjectOutputStream(socket.getOutputStream());
            inObject = new ObjectInputStream(socket.getInputStream());
            Thread read = new Thread(() -> {
                while (true) {
                    try {
                        Object obj = inObject.readObject();
                        System.out.println("Recieved object: " + obj);
                        messages.put(obj);
                    } catch (IOException | InterruptedException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            read.setDaemon(true);
            read.start();
        }
    }

    public void sendObject(Object object) throws IOException {
        server.outObject.writeObject(object);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void stopConnection() throws IOException {
        System.out.println("Terminating connection to server");
        server.socket.close();
    }
}
