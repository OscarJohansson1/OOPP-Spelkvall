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
    @FXML private Button recA;
    @FXML private Button recAE;
    @FXML private Button recD;
    @FXML private Button recE;
    @FXML private Button recF;
    @FXML private Button recH;
    @FXML private Button recI;
    @FXML private Button recIT;
    @FXML private Button recK;
    @FXML private Button recKfKb;
    @FXML private Button recM;
    @FXML private Button recSjo;
    @FXML private Button recTB;
    @FXML private Button recTD;
    @FXML private Button recV;
    @FXML private Button recZ;

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
    private ArrayList<Button> selectedButtons = new ArrayList<>();
    private ArrayList<Color> colorList = new ArrayList<>();
    private ArrayList<Integer> nextPlayerNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16));
    private ArrayList<Button> playerButtonList;
    private ArrayList<Button> divisionList;

    private int amountOfPlayers = 0;

    private Stage stage;

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
        for(Button button : divisionList){
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
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

    private void mouseClicked(Button button){
        if(!selectedButtons.contains(button) && nextToChoose <= amountOfPlayers) {
            colorList.add(Color.web(button.getStyle().substring(22,29)));

            Button playerButton = playerButtonList.get(nextToChoose - 1);
            playerButton.setText("Player " + nextToChoose + " represents " + button.getText());
            playerButton.setStyle(button.getStyle());

            button.setStyle("-fx-background-color: #A0A0A0");
            button.setText(Integer.toString(nextToChoose));

            selectedButtons.add(button);
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
