package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import program.model.Player;

import java.io.IOException;
import java.io.Serializable;

public class PlayerCard extends AnchorPane implements Serializable {

    @FXML private Label cardNameLabel;
    @FXML private ImageView cardImageView;

    Player player;

    public PlayerCard(Player player){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.player = player;

        this.cardImageView.setImage(new Image(player.getLogoUrl()));
        this.cardNameLabel.setText(player.getName());
    }


}
