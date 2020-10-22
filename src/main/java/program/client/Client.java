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
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Client implements IObserver {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private StartController startController;
    private MapController mapController;
    private Player player;
    private final LobbyItemCreator lobbyItemCreator = new LobbyItemCreator();
    private GameManager modelDataHandler;
    private boolean startedGame = false;
    public boolean hasConnection = false;

    private Client() {
    }

    private static class ClientHolder {
        private static final Client client = new Client();
    }

    public static Client getClient() {
        return ClientHolder.client;
    }

    public void startConnection(String ip, int port, StartController startController) throws IOException {
        try {
            Socket socket = new Socket();
            int timeout = 500;
            socket.connect(new InetSocketAddress(ip, port), timeout);
            server = new ConnectionToServer(socket);
        } catch (SocketTimeoutException e) {
            return;
        }
        hasConnection = true;
        this.startController = startController;
        startController.addObserver(this);
        messages = new LinkedBlockingQueue<>();
        System.out.println("Connected to 95.80.61.51, Port: 6666");
        modelDataHandler = GameManager.getGameManager();
        modelDataHandler.addObserver(this);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                stopConnection();
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
                    break;
                }
            }
        });
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
            Thread read = new Thread(() -> {
                while (true) {
                    try {
                        Object obj = inObject.readObject();
                        System.out.println("Recieved object: " + obj);
                        messages.put(obj);
                    } catch (IOException | InterruptedException | ClassNotFoundException e) {
                        break;
                    }
                }
            });
            read.setDaemon(true);
            read.start();
        }
    }

    private void handleObject(Object message) {
        try {
            if (message instanceof String) {
                if (message.equals("startClear")) {
                    startController.lobbyReadyController.startButton.setDisable(false);
                } else if (message.equals("startGame")) {
                    Stage stage = startController.stage;
                    mapController = new MapController(stage);
                    mapController.addObserver(this);
                    player.setMyTurn(modelDataHandler.getCurrentPlayer().getId() == player.getId());
                    modelDataHandler = GameManager.getGameManager();
                    modelDataHandler.addObserver(this);
                    Platform.runLater(() -> {
                        Scene scene = new Scene(mapController, 1920, 1080);
                        stage.setTitle("program.Chans");
                        stage.setScene(scene);
                        stage.show();
                    });
                    startedGame = true;

                } else if (message.equals("startNotClear")) {
                    startController.lobbyReadyController.startButton.setDisable(true);
                } else if (message.equals("resetColor")) {
                    mapController.resetColorOnline();
                } else if (message.equals("nextPhase")) {
                    modelDataHandler.round.nextPhase();
                    mapController.view.updatePhase(modelDataHandler.getCurrentPhase(), mapController);
                    if (!modelDataHandler.getCurrentPhase().equals("DEPLOY")) {
                        mapController.addMarkedCube(mapController.secondMarked);
                    } else {
                        mapController.removeMarkedCube(mapController.secondMarked);
                    }
                } else if (message.equals("removeAttackView")) {
                    mapController.removeOnlineAttackView();

                } else if (message.equals("resetSelectedSpaces")) {
                    modelDataHandler.resetSpaces();
                }
            } else if (message instanceof Integer) {
                if (modelDataHandler.getCurrentPlayer() == null) {
                    modelDataHandler.setCurrentPlayer(modelDataHandler.getPlayers().get((Integer) message));
                } else if (startedGame) {
                    modelDataHandler.setRoundCount((Integer) message);
                    return;
                }
                player.setId((Integer) message);
            } else if (message instanceof List) {
                for (Object object : (List<?>) message) {
                    if (object instanceof Player) {
                        List<Player> playerList = (List<Player>) message;
                        modelDataHandler.setPlayers(playerList);
                        startController.lobbyReadyController.updateUserCards(playerList);
                        if (player == null) {
                            player = playerList.get(playerList.size() - 1);
                        }
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
            } else if (message instanceof BoardManager) {
                modelDataHandler.setBoard((BoardManager) message);
            } else if (message instanceof Boolean) {
                if ((Boolean) message)
                    startController.lobbyReadyController.startButton.setVisible(true);
            } else if (message instanceof Space) {
                Space space = (Space) message;
                modelDataHandler.receiveOnlineSelectedSpace(space);
                mapController.view.updateTextUnits(space.getId(), space.getUnits(), mapController.allButtons, mapController);
                mapController.view.setColor(mapController.getCube(space.getId()), Color.web(modelDataHandler.getColorOnSpace(space.getId())).darker().darker(), mapController.allButtons);
                mapController.displayCubes(space.getId());


            } else if (message instanceof Player) {
                Player receivedPlayer = (Player) message;
                modelDataHandler.setCurrentPlayer(receivedPlayer);
                if (modelDataHandler.getRoundCount() > modelDataHandler.getPlayers().size()) {
                    modelDataHandler.firstDeployment = false;
                    receivedPlayer.setUnits(0);
                    modelDataHandler.setDeployableUnits(modelDataHandler.calculateDeployableUnits(modelDataHandler.getCurrentPlayer()));
                    mapController.moveSlider.setMax(modelDataHandler.getCurrentPlayer().getUnits());
                }
                mapController.updateCurrentPlayer();
                if (player.getId() == ((Player) message).getId()) {
                    player.setMyTurn(true);
                    modelDataHandler.getCurrentPlayer().setMyTurn(true);
                    mapController.view.myturn(mapController);
                } else {
                    player.setMyTurn(false);
                    mapController.view.otherPlayerPlaying(mapController);
                    modelDataHandler.getCurrentPlayer().setMyTurn(false);
                }

            } else if (message instanceof AttackPhase) {
                mapController.changeToAttackView();
                if (mapController.attackController.attackView.observers.size() == 0) {
                    mapController.attackController.attackView.addObserver(this);
                }
                mapController.attackController.attack((AttackPhase) message);
                if (player.getId() != modelDataHandler.getCurrentPlayer().getId()) {
                    mapController.removeAbortAndAttack();
                }
            }
        } catch (IOException ignored) {
        }
    }

    @Override
    public void sendObject(Object object) throws IOException {
        server.outObject.writeObject(object);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private void removeObserver(){
        if(startController != null){
            mapController.removeObserver(this);
        }
        if(mapController != null){
            mapController.removeObserver(this);
        }
        startController.removeObserver(this);
        mapController.removeObserver(this);
    }

    public void stopConnection() throws IOException {
        System.out.println("Terminating connection to server");
        hasConnection = false;
        server.inObject.close();
        server.outObject.close();
        server.socket.close();
    }
}
