package Program.Controller;

import Program.Model.Player;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SetUpGameController extends AnchorPane {

    @FXML
    private Slider slider;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button backButton;
    @FXML
    private Button startGameButton;

    @FXML
    private Button recA;
    @FXML
    private Button recAE;
    @FXML
    private Button recD;
    @FXML
    private Button recE;
    @FXML
    private Button recF;
    @FXML
    private Button recH;
    @FXML
    private Button recI;
    @FXML
    private Button recIT;
    @FXML
    private Button recK;
    @FXML
    private Button recKfKb;
    @FXML
    private Button recM;
    @FXML
    private Button recSjo;
    @FXML
    private Button recTB;
    @FXML
    private Button recTD;
    @FXML
    private Button recV;
    @FXML
    private Button recZ;

    @FXML
    private GridPane gridPane;

    @FXML
    private Rectangle rec1;
    @FXML
    private Rectangle rec2;
    @FXML
    private Rectangle rec3;
    @FXML
    private Rectangle rec4;
    @FXML
    private Rectangle rec5;
    @FXML
    private Rectangle rec6;
    @FXML
    private Rectangle rec7;
    @FXML
    private Rectangle rec8;
    @FXML
    private Rectangle rec9;
    @FXML
    private Rectangle rec10;
    @FXML
    private Rectangle rec11;
    @FXML
    private Rectangle rec12;
    @FXML
    private Rectangle rec13;
    @FXML
    private Rectangle rec14;
    @FXML
    private Rectangle rec15;
    @FXML
    private Rectangle rec16;

    @FXML
    private Text text1;
    @FXML
    private Text text2;
    @FXML
    private Text text3;
    @FXML
    private Text text4;
    @FXML
    private Text text5;
    @FXML
    private Text text6;
    @FXML
    private Text text7;
    @FXML
    private Text text8;
    @FXML
    private Text text9;
    @FXML
    private Text text10;
    @FXML
    private Text text11;
    @FXML
    private Text text12;
    @FXML
    private Text text13;
    @FXML
    private Text text14;
    @FXML
    private Text text15;
    @FXML
    private Text text16;

    private ArrayList<Player> playerList = new ArrayList<>();
    private ArrayList<Integer> nextPlayerNumber = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16));
    private ArrayList<Rectangle> recList;
    private ArrayList<Text> textList;

    Stage stage;

    SetUpGameController(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setUpGame.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        textList = new ArrayList<>(Arrays.asList(text1, text2, text3, text4, text5, text6, text7, text8,
                text9, text10, text11, text12, text13, text14, text15, text16));
        recList = new ArrayList<>(Arrays.asList(rec1, rec2, rec3, rec4, rec5, rec6, rec7, rec8, rec9,
                rec10, rec11, rec12, rec13, rec14, rec15, rec16));

        this.stage = stage;
        initialize();
    }

    private void createPlayerList(int players){
        if (players > playerList.size()){
            for(int i = playerList.size(); i < players; i++){
                playerList.add(new Player(0, i + 1, Color.web("#FF0000")));
            }
        } else if (players < playerList.size()){
            for(int i = playerList.size(); i > players; i--){
                playerList.remove(playerList.size() - 1);
            }
        }
    }

    private int getAndRemoveNextPlayerToChoose(){
        return nextPlayerNumber.remove(0);
    }

    private void addPlayerToChoose(int playerNumber){
        int i = 0;
        if(nextPlayerNumber.contains(playerNumber)){
            return;
        } else {
            while(nextPlayerNumber.get(i) < playerNumber){
                i++;
            }
            nextPlayerNumber.add(i, playerNumber);
        }
    }

    private void updatePlayer(String color, String studentDivision){
        int n = getAndRemoveNextPlayerToChoose();
        playerList.add(playerList.indexOf(playerList.remove(n)), new Player(0, n, Color.web(color)));
    }

    private void updatePlayerGrid(){

        for(int i = 0; i < playerList.size(); i++){
            recList.get(i).setVisible(true);
            textList.get(i).setVisible(true);
        }
        for(int i = playerList.size(); i < recList.size(); i++){
            recList.get(i).setVisible(false);
            textList.get(i).setVisible(false);
        }
    }


    private void initialize() {

        gridPane.get
        updatePlayerGrid();

        slider.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                createPlayerList((int) slider.getValue());
                updatePlayerGrid();
            }
        });
        startGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                MapController mapController = new MapController();
                Parent root = mapController;
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("Chans");
                stage.setScene(scene);
                stage.show();

            }
        });
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                MapController mapController = new MapController();
                Parent root = mapController;
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("Chans");
                stage.setScene(scene);
                stage.show();

            }
        });


        recA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //updatePlayer("#FF0000", "A");
                recA.setFill(Paint.valueOf("#FFFFFF"));
            }
        });
        textA.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FF0000", "A");
            }
        });
        recAE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#BFFF00", "AE");
            }
        });
        textAE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#BFFF00", "AE");
            }
        });
        recD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FF8000", "D");
            }
        });
        textD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FF8000", "D");
            }
        });
        recE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FFFF00", "E");
            }
        });
        textE.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FFFF00", "E");
            }
        });
        recF.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#000000", "F");
            }
        });
        textF.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#000000", "F");
            }
        });
        recH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#DE3163", "H");
            }
        });
        textH.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#DE3163", "H");
            }
        });
        recI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#9F00C5", "I");
            }
        });
        textI.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#9F00C5", "I");
            }
        });
        recIT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //updatePlayer("#09CDDA", "IT");
                recIT.setFill(Paint.valueOf("#A0A0A0"));
                textIT.setText("1");
                rec1.setFill(Paint.valueOf("#09CDDA"));
                text1.setText("Player 1 - IT");
            }
        });
        textIT.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //updatePlayer("#09CDDA", "IT");
                recIT.setFill(Paint.valueOf("#A0A0A0"));
                textIT.setText("1");
                rec1.setFill(Paint.valueOf("#09CDDA"));
                text1.setText("Player 1 - IT");
            }
        });
        recK.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#00C000", "K");
            }
        });
        textK.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#00C000", "K");
            }
        });
        recKfKb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#007000", "KfKb");
            }
        });
        textKfKb.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#007000", "KfKb");
            }
        });
        recM.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#964B00", "M");
            }
        });
        textM.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#964B00", "M");
            }
        });
        recSjo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#000080", "Sjö");
            }
        });
        textSjo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#000080", "Sjö");
            }
        });
        recTB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FFFFFF", "TB");
            }
        });
        textTB.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#FFFFFF", "TB");
            }
        });
        recTD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#5F021F", "TD");
            }
        });
        textTD.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#5F021F", "TD");
            }
        });
        recV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#4141FF", "V");
            }
        });
        textV.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#4141FF", "V");
            }
        });
        recZ.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#808080", "Z");
            }
        });
        textZ.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updatePlayer("#808080", "Z");
            }
        });
    }

}
