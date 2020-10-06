package program.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * the controller for StartMenu.fxml
 */
class SetUpGameController extends AnchorPane {

    @FXML private Slider slider;
    @FXML private AnchorPane pane;
    @FXML private GridPane gridPane;

    @FXML private Button backButton;
    @FXML private Button startGameButton;
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

    @FXML private Button rec1;
    @FXML private Button rec2;
    @FXML private Button rec3;
    @FXML private Button rec4;
    @FXML private Button rec5;
    @FXML private Button rec6;
    @FXML private Button rec7;
    @FXML private Button rec8;
    @FXML private Button rec9;
    @FXML private Button rec10;
    @FXML private Button rec11;
    @FXML private Button rec12;
    @FXML private Button rec13;
    @FXML private Button rec14;
    @FXML private Button rec15;
    @FXML private Button rec16;

    private int nextToChoose = 1;
    private ArrayList<ImageView> selectedDivisions = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<Integer> nextPlayerNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16));
    private ArrayList<Button> playerButtonList;
    private ArrayList<ImageView> divisionList;

    private int amountOfPlayers = 0;

    private Stage stage;
    private Object ColorAdjust;

    private ColorAdjust chosen = new ColorAdjust();


    /**
     * this is the constructor for SetUpGameController
     * @param stage the main stage
     */
    SetUpGameController(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setUpGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.stage = stage;
        initialize();
    }

    private void initialize() {
        playerButtonList = new ArrayList<>(Arrays.asList(rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9,
                rec10, rec11, rec12, rec13, rec14, rec15, rec16));
        divisionList = new ArrayList<>(Arrays.asList(recA, recAE, recD, recE, recF, recH, recI, recIT, recK,
                recKfKb, recM, recSjo, recTB, recTD, recV, recZ));
        updatePlayerGrid((int) slider.getValue());

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                updatePlayerGrid( newValue.intValue());
            }
        });
        startGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(colorList.size() >= slider.getValue()) {
                    Parent root = new MapController(colorList, stage);
                    //give players to

                    Scene scene = new Scene(root, 1920, 1080);

                    stage.setTitle("Chans");
                    stage.setScene(scene);
                    stage.show();
                }

            }
        });
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Parent root = new StartController(stage);
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("Chans");
                stage.setScene(scene);
                stage.show();
            }
        });
        for(ImageView button : divisionList){
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    mouseClicked(button);
                }
            });
        }
        for(Button button : playerButtonList){
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    makeSelected(button);

                }
            });
        }
    }

    private void mouseClicked(ImageView image){
        if(!selectedDivisions.contains(image) && nextToChoose <= amountOfPlayers) {
            colorList.add(Color.web(image.getStyle().substring(22,29)));

            Button playerButton = playerButtonList.get(nextToChoose - 1);
            playerButton.setText("Player " + nextToChoose + " represents " + image.getId().substring(3));
            playerButton.setStyle(image.getStyle());

            chosen.setBrightness(-0.5);
            chosen.setSaturation(-1.0);
            image.setEffect(chosen);

            selectedDivisions.add(image);
            nextToChoose++;
        }
    }

    private void makeSelected(Button button){
        //Going to implement at a later stage
    }
    private void updatePlayerGrid(int players){

        for(int i = 0; i < players; i++){
            playerButtonList.get(i).setVisible(true);
        }
        for(int i = players; i < playerButtonList.size(); i++){
            playerButtonList.get(i).setVisible(false);
        }
        amountOfPlayers = players;
    }
}
