package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * The controller for lobbyItem.fxml
 */
public class LobbyItem extends AnchorPane {

    @FXML
    private Label timeLabel;
    @FXML
    private Label capacityLabel;
    @FXML
    Label nameLabel;
    boolean marked = false;
    private final StartController startController;


    public LobbyItem(String name, String time, String capacity, String players, StartController startController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lobbyItem.fxml"));
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

    /**
     * Method that resets lobby items in lobbySelect and marks itself and changes it's color
     */
    @FXML
    public void onClick() {
        startController.lobbySelectController.resetLobbyItems();
        marked = true;
        this.setStyle("-fx-background-color: cornflowerblue");

    }


}
