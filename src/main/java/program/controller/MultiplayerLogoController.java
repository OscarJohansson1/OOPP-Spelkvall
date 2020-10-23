package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import program.model.IObservable;
import program.model.IObserver;
import program.model.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplayerLogoController extends AnchorPane implements IObservable {

    @FXML
    private ImageView logoImage;
    @FXML
    private TextField playerNameTextField;
    @FXML
    private GridPane gridPane;

    @FXML
    private Button backToLobbySelectButton;
    @FXML
    private Button chooseButton;

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

    private final List<IObserver> observers = new ArrayList<>();
    private List<ImageView> divisionList;
    private final StartController startController;
    private ImageView selectedButton;
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

    /**
     * Initializes the buttons and asks the server for GridPane.
     */
    private void initialize() throws IOException {

        notifyObservers("gridPane");
        divisionList = new ArrayList<>(Arrays.asList(recA, recAE, recD, recE, recF, recH, recI, recIT, recK,
                recKfKb, recM, recSjo, recTB, recTD, recV, recZ));
        for (int i = 0; i < divisionList.size(); i++) {
            int var = i;
            divisionList.get(i).setOnMouseClicked(mouseEvent -> {
                if (integerList != null && integerList.contains(var)) {
                    divisionList.get(var).setDisable(true);
                } else {
                    logoImage.setImage(divisionList.get(var).getImage());
                    selectedButton = divisionList.get(var);
                }
            });
        }
    }

    /**
     * Makes the logos that are chosen greyed out.
     *
     * @param integers list of ints for greyed out logos
     */
    public void updateGridPane(List<Integer> integers) {
        integerList = integers;
        for (Integer integer : integerList) {
            divisionList.get(integer).setDisable(true);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            divisionList.get(integer).setEffect(colorAdjust);
        }
    }

    /**
     * Returns to lobby list view.
     */
    @FXML
    public void backToLobbySelect() throws IOException {
        startController.removeSetUp();
    }

    /**
     * If the playerNameTextField isn't empty it will create a player based on the name and logo picked and go to lobbyReady.
     */
    @FXML
    public void choose() throws IOException {
        if (!(playerNameTextField.getText().trim().isEmpty())) {
            int number = gridPane.getChildren().indexOf(selectedButton);
            String color = selectedButton.getStyle().substring(22, 29);
            String logoUrl = selectedButton.getId().substring(3).toLowerCase() + "_logo";
            String playerName = playerNameTextField.getCharacters().toString();
            startController.goToLobbyReady(new Player(0, 1, color, logoUrl, playerName), number);
        }
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object object) throws IOException {
        for (IObserver observer : observers) {
            observer.sendObject(object);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
}
