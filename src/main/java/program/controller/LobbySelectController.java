package program.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import program.model.Lobby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbySelectController extends AnchorPane {

    @FXML public FlowPane lobbyFlow;

    List<Lobby> lobbys = new ArrayList<>();
    List<LobbyItem> lobbyitems = new ArrayList<>();

    private final StartController startController;

    public LobbySelectController(StartController startController) throws IOException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lobbySelect.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.startController = startController;
    }

    public void joinLobby() throws IOException {
        startController.goToSetup();
    }

    public void toMenu() throws IOException {

        startController.backToMainMenu();

    }

    public void resetChosen(){

        for(LobbyItem lobbyitem : lobbyitems){

            lobbyitem.setStyle("-fx-background-color: #FFFFFF");

        }

    }
    public void updateLobbys(List<Lobby> lobbies){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Updating lobbys in LobbyselectController");
                lobbyFlow.getChildren().clear();
                lobbys = lobbies;
                for(Lobby lobby: lobbys){
                    lobbyitems.add (new LobbyItem(lobby, startController));
                }
                lobbyFlow.getChildren().addAll(lobbyitems);
                System.out.println("Lobby size is " + lobbys.size());
            }});


    }





}
