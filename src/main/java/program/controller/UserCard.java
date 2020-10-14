package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import program.model.User;

import java.io.IOException;

public class UserCard extends AnchorPane {

    @FXML private Label cardNameLabel;
    @FXML private ImageView cardImageView;

    User user;
    LobbyReadyController lobbyReadyController;

    public UserCard(LobbyReadyController lobbyReadyController, User user){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userCard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.lobbyReadyController = lobbyReadyController;
        this.user = user;

        this.cardImageView.setImage(new Image(user.getImageUrl()));
        this.cardNameLabel.setText(user.getUserName());
    }


}
