package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the controller for setUpGame.fxml
 */
public class SetUpGameController extends AnchorPane {

    @FXML
    private Slider slider;
    @FXML
    private AnchorPane pane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button backButton;
    @FXML
    private Button startGameButton;

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

    @FXML
    private Button rec1;
    @FXML
    private Button rec2;
    @FXML
    private Button rec3;
    @FXML
    private Button rec4;
    @FXML
    private Button rec5;
    @FXML
    private Button rec6;
    @FXML
    private Button rec7;
    @FXML
    private Button rec8;
    @FXML
    private Button rec9;
    @FXML
    private Button rec10;
    @FXML
    private Button rec11;
    @FXML
    private Button rec12;
    @FXML
    private Button rec13;
    @FXML
    private Button rec14;
    @FXML
    private Button rec15;
    @FXML
    private Button rec16;

    private int nextToChoose = 1;
    private final List<ImageView> selectedDivisions = new ArrayList<>();
    private final List<String> colorList = new ArrayList<>();
    private final List<String> logoNameList = new ArrayList<>();
    private List<Button> playerButtonList;

    private int amountOfPlayers = 0;

    private final Stage stage;

    private final ColorAdjust chosen = new ColorAdjust();

    /**
     * this is the constructor for SetUpGameController
     *
     * @param stage the main stage
     */
    SetUpGameController(Stage stage) {
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
        ArrayList<ImageView> divisionList = new ArrayList<>(Arrays.asList(recA, recAE, recD, recE, recF, recH, recI, recIT, recK,
                recKfKb, recM, recSjo, recTB, recTD, recV, recZ));
        updatePlayerGrid((int) slider.getValue());

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> updatePlayerGrid(newValue.intValue()));
        startGameButton.setOnMouseClicked(mouseEvent -> {
            if (colorList.size() >= slider.getValue()) {
                Parent root = null;
                try {
                    root = new MapController(colorList.subList(0, (int) slider.getValue()), logoNameList, stage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert root != null;
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("program.Chans");
                stage.setScene(scene);
                stage.show();
            }
        });
        backButton.setOnMouseClicked(mouseEvent -> {

            Parent root;
            root = new StartController(stage);
            Scene scene = new Scene(root, 1920, 1080);

            stage.setTitle("program.Chans");
            stage.setScene(scene);
            stage.show();
        });
        for (ImageView button : divisionList) {
            button.setOnMouseClicked(mouseEvent -> mouseClicked(button));
        }
    }

    private void mouseClicked(ImageView image) {
        if (!selectedDivisions.contains(image) && nextToChoose <= amountOfPlayers) {
            Color color = Color.web(image.getStyle().substring(22, 29));
            colorList.add(image.getStyle().substring(22, 29));
            logoNameList.add(image.getId().substring(3).toLowerCase() + "_logo");
            Button playerButton = playerButtonList.get(nextToChoose - 1);
            double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
            if (y >= 0.5) {
                playerButton.setTextFill(Color.valueOf("#000000"));
            } else {
                playerButton.setTextFill(Color.valueOf("#ffffff"));
            }
            playerButton.setText("Player " + nextToChoose + " represents " + image.getId().substring(3));
            playerButton.setStyle(image.getStyle());
            chosen.setBrightness(-0.5);
            chosen.setSaturation(-1.0);
            image.setEffect(chosen);

            selectedDivisions.add(image);
            nextToChoose++;
        }
    }

    private void updatePlayerGrid(int players) {

        for (int i = 0; i < players; i++) {
            playerButtonList.get(i).setVisible(true);
        }
        for (int i = players; i < playerButtonList.size(); i++) {
            playerButtonList.get(i).setVisible(false);
        }
        amountOfPlayers = players;
    }
}
