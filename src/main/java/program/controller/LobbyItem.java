package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import program.controller.MapController;
import program.model.Lobby;


import java.io.IOException;

public class LobbyItem extends AnchorPane {

    private MapController mapController;

    @FXML private Label timeLabel;
    @FXML private Label capacityLabel;
    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    Lobby lobby;

    public LobbyItem(MapController mapController, Lobby lobby){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LobbyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mapController = mapController;

    }




}
