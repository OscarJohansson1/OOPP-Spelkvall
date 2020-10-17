package program.controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import program.model.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private LinkedBlockingQueue<Object> messages;
    private ConnectionToServer server;
    private ClientController clientController;
    private MapController mapController;
    private final LobbyItemCreator lobbyItemCreator = new LobbyItemCreator();
    private List<Integer> templist;

    public void startConnection(String ip, int port, ClientController clientController) throws IOException {
        server = new ConnectionToServer(new Socket(ip, port));
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
        Thread messageHandling = new Thread() {
            public void run() {
                while (true) {
                    try {
                        Object message = messages.take();
                        if (message instanceof String) {
                            if (message.equals("startClear")) {
                                clientController.startController.lobbyReadyController.startButton.setDisable(false);
                            } else if (message.equals("StartGame")) {
                                Stage stage = clientController.startController.stage;
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        Parent value = null;
                                        try {
                                            mapController = new MapController(clientController, stage);
                                            ModelDataHandler.getModelDataHandler().setClientController(clientController);
                                            clientController.player.setMyTurn(ModelDataHandler.getModelDataHandler().getCurrentPlayer().getId() == clientController.player.getId());
                                            value = mapController;
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        Scene scene = new Scene(value, 1920, 1080);

                                        stage.setTitle("program.Chans");
                                        stage.setScene(scene);
                                        stage.show();
                                    }
                                });

                            } else if (message.equals("startNotClear")) {
                                clientController.startController.lobbyReadyController.startButton.setDisable(true);
                            } else if (message.equals("unmark1")) {

                            } else if (message.equals("unmark2")) {

                            } else if (message.equals("resetColor")) {
                                mapController.resetColor();
                            } else if (message.equals("nextPhase")) {
                                ModelDataHandler.getModelDataHandler().round.nextPhase();
                            } else if (message.equals("removeAttackView")) {
                                mapController.removeOnlineAttackView();
                            } else if (message.equals("winner")) {

                            } else {
                                if (lobbyItemCreator.getName() == null) {
                                    lobbyItemCreator.setName((String) message);
                                } else if (lobbyItemCreator.getTime() == null) {
                                    lobbyItemCreator.setTime((String) message);
                                } else if (lobbyItemCreator.getCapacity() == null) {
                                    lobbyItemCreator.setCapacity((String) message);
                                } else if (lobbyItemCreator.getPlayers() == null) {
                                    lobbyItemCreator.setPlayers((String) message);
                                    clientController.startController.lobbySelectController.lobbyItems.add(lobbyItemCreator.createLobbyItem(clientController.startController));
                                    lobbyItemCreator.resetVariables();
                                    clientController.startController.lobbySelectController.updateLobbys();
                                }
                            }
                        } else if (message instanceof Integer) {
                            if (ModelDataHandler.getModelDataHandler().getCurrentPlayer() == null) {
                                ModelDataHandler.getModelDataHandler().setCurrentPlayer(ModelDataHandler.getModelDataHandler().getPlayers().get((Integer) message));
                                clientController.player.setId((Integer) message);
                            } else if ((Integer) message > ModelDataHandler.getModelDataHandler().getPlayers().size() - 1) {

                            } else {
                                clientController.player.setId((Integer) message);
                            }

                        } else if (message instanceof List) {
                            for (Object object : (List<?>) message) {
                                if (object instanceof LobbyItem) {
                                    clientController.startController.lobbySelectController.updateLobbys();
                                    break;
                                } else if (object instanceof Player) {
                                    List<Player> playerList = (List<Player>) message;
                                    ModelDataHandler.getModelDataHandler().setPlayers(playerList);
                                    clientController.startController.lobbyReadyController.updateUserCards(playerList);
                                } else if (object instanceof Integer) {
                                    if (mapController == null) {
                                        clientController.startController.multiplayerLogoController.updateGridPane((List<Integer>) message);
                                    }
                                }

                            }
                        } else if (message instanceof Board) {
                            clientController.modelDataHandler.setBoard((Board) message);
                        } else if (message instanceof Boolean) {
                            if ((Boolean) message)
                                clientController.startController.lobbyReadyController.startButton.setVisible(true);
                        } else if (message instanceof Space) {
                            Space space = (Space) message;
                            ModelDataHandler.getModelDataHandler().receiveOnlineSelectedSpace(space);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    mapController.view.updateTextUnits(space.getId(), space.getUnits(), mapController.allButtons, mapController);
                                    mapController.view.setColor(mapController.getCube(space.getId()), Color.web(ModelDataHandler.getModelDataHandler().getColorOnSpace(space.getId())).darker().darker(), mapController.allButtons);
                                }
                            });
                        } else if (message instanceof Player) {
                            System.out.println("Received player has id: " + ((Player) message).getId());
                            ModelDataHandler.getModelDataHandler().setCurrentPlayer((Player) message);
                            ModelDataHandler.getModelDataHandler().setDeployableUnits(((Player) message).getUnits());
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Player player = ModelDataHandler.getModelDataHandler().getCurrentPlayer();
                                    mapController.view.updateCurrentPlayer(player.getColor(), mapController, player.getName());
                                    mapController.view.updateDeployableUnits(mapController.deployableUnitsText, ((Player) message).getUnits());
                                }
                            });
                            if (clientController.player.getId() == ((Player) message).getId()) {
                                clientController.player.setMyTurn(true);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        mapController.view.myturn(mapController);
                                    }
                                });

                            } else {
                                clientController.player.setMyTurn(false);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        mapController.view.otherPlayerPlaying(mapController);
                                    }
                                });
                            }

                        } else if (message instanceof Attack) {
                            mapController.changeToOnlineAttackView();
                            mapController.attackController.attack((Attack) message);
                        }
                    } catch (InterruptedException | IOException ignored) {
                    }
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

            Thread read = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Object obj = inObject.readObject();
                            System.out.println("Recieved object: " + obj);
                            messages.put(obj);
                        } catch (IOException | InterruptedException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            read.setDaemon(true);
            read.start();
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
