package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LobbyItem extends AnchorPane {

    @FXML
    private Label timeLabel;
    @FXML
    private Label capacityLabel;
    @FXML
    private Label nameLabel;
    boolean marked = false;
    private StartController startController;


    public LobbyItem(String name, String time, String capacity, String players, StartController startController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LobbyItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.startController = startController;
        this.timeLabel.setText(time);
        this.nameLabel.setText("Lobby name:" + name);
        this.capacityLabel.setText("Users:" + players + "/" + capacity);

    }

    public void onClick() throws IOException {
        startController.lobbySelectController.resetLobbyItems();
        marked = true;
        this.setStyle("-fx-background-color: cornflowerblue");

    }


}
