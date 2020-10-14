package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import program.model.Lobby;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LobbyItem extends AnchorPane {

    private MapController mapController;

    @FXML private Label timeLabel;
    @FXML private Label capacityLabel;
    @FXML private Label nameLabel;
    Lobby lobby;


    public LobbyItem( Lobby lobby){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LobbyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.lobby = lobby;

        DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
        this.timeLabel.setText(date.format(lobby.getLobbyTime()));
        this.nameLabel.setText("Lobby name:" + lobby.getLobbyName());
        this.capacityLabel.setText("Users: " + lobby.getLobbyCapacity() + "/16");

    }




}
