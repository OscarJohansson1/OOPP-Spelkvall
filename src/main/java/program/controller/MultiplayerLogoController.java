package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import program.client.Client;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplayerLogoController extends AnchorPane {

    @FXML
    private ImageView logoImage;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView recA;
    @FXML
    private ImageView recAE;
    @FXML
    private ImageView recD;
    @FXML
    private ImageView recE;
    @FXML
    private ImageView recF;
    @FXML
    private ImageView recH;
    @FXML
    private ImageView recI;
    @FXML
    private ImageView recIT;
    @FXML
    private ImageView recK;
    @FXML
    private ImageView recKfKb;
    @FXML
    private ImageView recM;
    @FXML
    private ImageView recSjo;
    @FXML
    private ImageView recTB;
    @FXML
    private ImageView recTD;
    @FXML
    private ImageView recV;
    @FXML
    private ImageView recZ;

    private ArrayList<ImageView> divisionList;
    private final Client client = Client.getClient();
    private final StartController startController;
    private ImageView selectedbutton;
    private List<Integer> integerList = new ArrayList<>();

    public MultiplayerLogoController(StartController startController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setUpOnline.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.startController = startController;
        initialize();
    }

    private void initialize() throws IOException {

        client.sendObject("gridPane");
        divisionList = new ArrayList<>(Arrays.asList(recA, recAE, recD, recE, recF, recH, recI, recIT, recK,
                recKfKb, recM, recSjo, recTB, recTD, recV, recZ));
        for (int i = 0; i < divisionList.size(); i++) {
            int var = i;
            divisionList.get(i).setOnMouseClicked(mouseEvent -> {
                if (integerList != null && integerList.contains(var)) {
                    divisionList.get(var).setDisable(true);
                } else {
                    logoImage.setImage(divisionList.get(var).getImage());
                    selectedbutton = divisionList.get(var);
                }
            });
        }
    }

    public void updateGridPane(List<Integer> integers) {
        integerList = integers;
        for (Integer integer : integerList) {
            divisionList.get(integer).setDisable(true);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            divisionList.get(integer).setEffect(colorAdjust);
        }
    }
    @FXML
    public void backToLobbyList() {
        startController.removeSetUp();
    }
    @FXML
    public void choose() throws IOException {

        if (!(playerNameTextField.getText().trim().isEmpty())) {

            int number = gridPane.getChildren().indexOf(selectedbutton);
            String color = selectedbutton.getStyle().substring(22, 29);
            String logoUrl = selectedbutton.getId().substring(3).toLowerCase() + "_logo";
            String playerName = playerNameTextField.getCharacters().toString();
            startController.goToLobbyReady(new Player(0, 1, color, logoUrl, playerName), number);

        }

    }
    @FXML
    public void toLobbySelect() {
        startController.toLobbySelect();
    }


}
