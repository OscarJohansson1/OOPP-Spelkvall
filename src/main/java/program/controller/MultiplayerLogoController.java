package program.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MultiplayerLogoController extends AnchorPane {

    @FXML private ImageView logoImage;
    @FXML private TextField playerNameTextField;

    @FXML private ImageView recA;
    @FXML private ImageView recAE;
    @FXML private ImageView recD;
    @FXML private ImageView recE;
    @FXML private ImageView recF;
    @FXML private ImageView recH;
    @FXML private ImageView recI;
    @FXML private ImageView recIT;
    @FXML private ImageView recK;
    @FXML private ImageView recKfKb;
    @FXML private ImageView recM;
    @FXML private ImageView recSjo;
    @FXML private ImageView recTB;
    @FXML private ImageView recTD;
    @FXML private ImageView recV;
    @FXML private ImageView recZ;

    private ArrayList<ImageView> divisionList;
    private ClientController clientController;
    private StartController startController;
    private String playerName;
    private ImageView selectedbutton;


    public MultiplayerLogoController(ClientController clientController, StartController startController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setUpOnline.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.clientController = clientController;
        this.startController = startController;
        initialize();
    }
    private void initialize() throws IOException {

        divisionList = new ArrayList<>(Arrays.asList(recA, recAE, recD, recE, recF, recH, recI, recIT, recK,
                recKfKb, recM, recSjo, recTB, recTD, recV, recZ));
        for(ImageView button : divisionList){
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    logoImage.setImage(button.getImage());
                    selectedbutton = button;
                    /*try {
                        clientController.addPlayerToLobby(player);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }*/
                }
            });
        }


    }
    public void backToLobbyList() {
        startController.removeSetUp();
    }
    public void choose() throws IOException, ClassNotFoundException {

        if (!(playerNameTextField.getText().trim().isEmpty())){

            startController.goToLobbyReady(new Player(0,1,selectedbutton.getStyle().substring(22,29),selectedbutton.getId().substring(3).toLowerCase() + "_logo",playerNameTextField.getCharacters().toString()));

        }

    }
    public void toLobbySelect() throws IOException {  startController.toLobbySelect(); }




}
