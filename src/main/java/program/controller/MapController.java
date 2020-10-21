package program.controller;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import program.model.*;
import program.view.MapView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the controller for karta.fxml
 */
public class MapController extends AnchorPane implements IObservable {

    @FXML
    private Button cubeHubben;
    @FXML
    private Button cubeBasen;
    @FXML
    private Button cubeKajsabaren;
    @FXML
    private Button cubeZaloonen;
    @FXML
    private Button cubeWinden;
    @FXML
    private Button cubeLofTDet;
    @FXML
    private Button cubeRodaRummet;
    @FXML
    private Button cubeVerum;
    @FXML
    private Button cubeVillan;
    @FXML
    private Button cubeADammen;
    @FXML
    private Button cubeFocus;
    @FXML
    private Button cubeFortNox;
    @FXML
    private Button cubeGTSpritis;
    @FXML
    private Button cubeGoldenI;
    @FXML
    private Button cubeChabo;
    @FXML
    private Button cubeWijkanders;
    @FXML
    private Button cubeHrum;
    @FXML
    private Button cubeAlvan;
    @FXML
    private Button cubeSpektrum;
    @FXML
    private Button cubeGasquen;
    @FXML
    private Button cubeChalmersplatsen;
    @FXML
    private Button cubeOlgas;
    @FXML
    private Button cubeRunAn;
    @FXML
    private Button cubeTagvagnen;
    @FXML
    private Button cubeOrigogarden;
    @FXML
    private Button cubeKalleGlader;
    @FXML
    private Button cubeTvargatan;

    @FXML
    private Text textHubben;
    @FXML
    private Text textBasen;
    @FXML
    private Text textKajsabaren;
    @FXML
    private Text textZaloonen;
    @FXML
    private Text textWinden;
    @FXML
    private Text textLofTDet;
    @FXML
    private Text textRodaRummet;
    @FXML
    private Text textVerum;
    @FXML
    private Text textVillan;
    @FXML
    private Text textAdammen;
    @FXML
    private Text textFocus;
    @FXML
    private Text textFortNox;
    @FXML
    private Text textGTSpritis;
    @FXML
    private Text textGoldenI;
    @FXML
    private Text textChabo;
    @FXML
    private Text textWijkanders;
    @FXML
    private Text textHrum;
    @FXML
    private Text textAlvan;
    @FXML
    private Text textSpektrum;
    @FXML
    private Text textGasquen;
    @FXML
    private Text textChalmersplatsen;
    @FXML
    private Text textOlgas;
    @FXML
    private Text textRunAn;
    @FXML
    private Text textTagvagnen;
    @FXML
    private Text textOrigogarden;
    @FXML
    private Text textKalleGlader;
    @FXML
    private Text textTvargatan;

    @FXML
    public AnchorPane rootpane;
    @FXML
    AnchorPane startMenu;

    @FXML
    public AnchorPane deployPhase;
    @FXML
    public AnchorPane attackPhase;
    @FXML
    public AnchorPane movePhase;

    @FXML
    public Slider moveSlider;

    @FXML
    private Button startButton;
    @FXML
    private Button skipAttack;
    @FXML
    private Button doneMove;
    @FXML
    private Button donedeploy;
    @FXML
    private Button deployButton;
    @FXML
    private Button attackButton;
    @FXML
    private Button moveButton;
    @FXML
    private Button firstMarked;
    @FXML
    public Button secondMarked;
    @FXML
    public Button showCurrentPlayer;

    @FXML
    public Text showMoveUnitsText;
    @FXML
    private Text firstDisplayText;
    @FXML
    private Text secondDisplayText;
    @FXML
    public Text playerText;
    @FXML
    public Text phaseText;
    @FXML
    public Text deployableUnitsText;

    @FXML
    public Pane phasePane;
    @FXML
    public Pane sliderPane;

    @FXML
    public ImageView imageTeamLogo;

    private final List<IObserver> observers = new ArrayList<>();

    public GameManager modelDataHandler;
    public MapView view = new MapView();
    public AttackController attackController;
    public List<Button> allButtons;
    private List<Text> allTexts;
    private Stage stage;
    private PauseController pauseController;
    private final boolean localmode;


    MapController(List<String> colors, List<String> logoNames, Stage stage) throws IOException {
        localmode = true;
        firstInitialize(stage);
        modelDataHandler.initialize(allButtons.size(), colors, logoNames);
        secondInitialize();
    }

    public MapController(Stage stage) throws IOException {
        localmode = false;
        firstInitialize(stage);
        secondInitialize();
    }

    private void firstInitialize(Stage stage) {
        this.stage = stage;
        Platform.runLater(() -> stage.setFullScreen(true));
        pauseController = new PauseController(stage, this);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("karta.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        allButtons = new ArrayList<>(Arrays.asList(cubeHubben, cubeBasen, cubeKajsabaren, cubeZaloonen, cubeWinden, cubeLofTDet,
                cubeRodaRummet, cubeVerum, cubeVillan, cubeADammen, cubeFocus, cubeFortNox, cubeGTSpritis, cubeGoldenI, cubeChabo, cubeWijkanders, cubeHrum,
                cubeAlvan, cubeSpektrum, cubeGasquen, cubeChalmersplatsen, cubeOlgas, cubeRunAn, cubeTagvagnen, cubeOrigogarden, cubeKalleGlader, cubeTvargatan));

        allTexts = new ArrayList<>(Arrays.asList(textHubben, textBasen, textKajsabaren, textZaloonen, textWinden, textLofTDet,
                textRodaRummet, textVerum, textVillan, textAdammen, textFocus, textFortNox, textGTSpritis, textGoldenI, textChabo, textWijkanders, textHrum,
                textAlvan, textSpektrum, textGasquen, textChalmersplatsen, textOlgas, textRunAn, textTagvagnen, textOrigogarden, textKalleGlader, textTvargatan));
        modelDataHandler = GameManager.getGameManager();
    }

    private void secondInitialize() throws IOException {
        for (int i = 0; i < allButtons.size(); i++) {
            int var = i;
            allButtons.get(i).setOnMouseClicked(mouseEvent -> {
                try {
                    if (!localmode) {
                        if (modelDataHandler.getCurrentPlayer().getMyTurn()) {

                            setSpace(var);
                        }
                    } else {
                        setSpace(var);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        stage.addEventHandler(KeyEvent.KEY_PRESSED, t -> {
            if (t.getCode() == KeyCode.ESCAPE) {
                checkPauseController();
            }
        });

        skipAttack.setOnMouseClicked(mouseEvent -> {
            modelDataHandler.removePlayersWithoutSpaces();
            try {
                modelDataHandler.nextPhase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            updatePhase("MOVE");
            try {
                resetColor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            resetDisplayCubes();
            sliderVisibility(true);
            addMarkedCube(secondMarked);
        });
        doneMove.setOnMouseClicked(mouseEvent -> {
            try {
                modelDataHandler.nextPhase();
            } catch (IOException e) {
                e.printStackTrace();
            }
            updatePhase("DEPLOY");
            try {
                resetColor();
            } catch (IOException e) {
                e.printStackTrace();
            }
            modelDataHandler.setDeployableUnits(modelDataHandler.calculateDeployableUnits(modelDataHandler.getCurrentPlayer()));
            moveSlider.setMax(modelDataHandler.getDeployableUnits());
            updateCurrentPlayer();
            sliderVisibility(true);
            removeMarkedCube(secondMarked);
            setDeployButton(true);
            moveSlider.setMax(modelDataHandler.getDeployableUnits());
        });
        donedeploy.setOnMouseClicked(mouseEvent -> {
            if (modelDataHandler.firstDeployment) {
                updatePhase("DEPLOY");
                try {
                    modelDataHandler.firstRoundNextPhase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(!modelDataHandler.firstDeployment){
                    modelDataHandler.setDeployableUnits(modelDataHandler.calculateDeployableUnits(modelDataHandler.getCurrentPlayer()));
                }
                updateCurrentPlayer();
                moveSlider.setMax(modelDataHandler.getDeployableUnits());
                try {
                    resetColor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setDeployButton(true);
            } else {
                try {
                    modelDataHandler.nextPhase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                moveSlider.setMax(modelDataHandler.getDeployableUnits());
                try {
                    resetColor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                updatePhase("ATTACK");
                updateCurrentPlayer();
                sliderVisibility(false);
                addMarkedCube(firstMarked);
                addMarkedCube(secondMarked);
                skipAttack.setVisible(true);
            }
        });
        deployButton.setOnMouseClicked(mouseEvent -> {
            try {
                deploy();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        attackButton.setOnMouseClicked(mouseEvent -> {
            try {
                attack();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        moveButton.setOnMouseClicked(mouseEvent -> {
            try {
                move();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        moveSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            modelDataHandler.setSliderAmount(newValue.intValue());
            view.updateSliderText(newValue.intValue(), showMoveUnitsText);
        });
        view.updatePhaseText(modelDataHandler.getCurrentPhase(), this);
        updateCurrentPlayer();
        for (int i = 0; i < allButtons.size(); i++) {
            view.updateTextUnits(i, modelDataHandler.getUnitsOnSpace(i), allButtons, this);
        }
        resetColor();
        removeMarkedCube(secondMarked);
        setDeployButton(true);
        moveSlider.setMax(modelDataHandler.getDeployableUnits());
        skipAttack.setVisible(false);
    }

    public void updateCurrentPlayer() {
        view.updateCurrentPlayer(modelDataHandler.getCurrentPlayerColor(), this, modelDataHandler.getCurrentPlayerName());
        view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
    }
    public void updatePhase(String phase){
        view.updatePhase(phase, MapController.this);
        view.updatePhaseText(phase, MapController.this);
    }

    private void setDeployButton(boolean b){
        donedeploy.setDisable(b);
        if(b){
            donedeploy.setStyle("-fx-background-color: #000000");
        }
        else {
            donedeploy.setStyle(null);
        }
    }

    public void deploy() throws IOException {
        if (modelDataHandler.startPhase()) {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
            view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
            moveSlider.setMax(modelDataHandler.getDeployableUnits());
            notifyObservers(new Space(modelDataHandler.getSelectedSpace()));
        }
        if (modelDataHandler.getDeployableUnits() == 0) {
            setDeployButton(false);
        }
    }

    public void attack() throws IOException {
        if (modelDataHandler.startPhase()) {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
            setSpaceEvent(modelDataHandler.getSelectedSpace2().getId());
            changeToAttackView();
            notifyObservers(new AttackPhase(GameManager.getGameManager().round.getAttack()));
            notifyObservers(new Space(GameManager.getGameManager().getSelectedSpace()));
            notifyObservers(new Space(GameManager.getGameManager().getSelectedSpace2()));

        }
    }

    public void move() throws IOException {
        if (modelDataHandler.startPhase()) {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
            setSpaceEvent(modelDataHandler.getSelectedSpace2().getId());
            notifyObservers(new Space(modelDataHandler.getSelectedSpace()));
            notifyObservers(new Space(modelDataHandler.getSelectedSpace2()));
            modelDataHandler.resetSelectedSpaces();
        }
        resetColor();
    }

    public void changeToAttackView() throws IOException {
        if (attackController == null) {
            attackController = new AttackController(this);
            if (localmode) {
                attackController.attack();
            }
            Platform.runLater(() -> rootpane.getChildren().add(attackController));
        }
    }

    public void removeAbortAndAttack() {
        attackController.abortButton.setVisible(false);
        attackController.attackButton.setVisible(false);
    }

    void removeAttackView() throws IOException {
        Platform.runLater(() -> {
            if (modelDataHandler.isWinner()) {
                Parent root = new EndController(stage);
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("End");
                stage.setScene(scene);
                stage.show();
            }
        });
        rootpane.getChildren().remove(attackController);
        for (int i = 0; i < allButtons.size(); i++) {
            view.updateTextUnits(i, modelDataHandler.getUnitsOnSpace(i), allButtons, this);
            view.setColor(allButtons.get(i), Color.web(modelDataHandler.getColorOnAllSpaces().get(i)), allButtons);
        }

        modelDataHandler.getSelectedSpace().updateSpace(1);
        notifyObservers(new Space(modelDataHandler.getSelectedSpace()));
        notifyObservers(new Space(modelDataHandler.getSelectedSpace2()));
        notifyObservers("removeAttackView");

        modelDataHandler.resetSelectedSpaces();
        attackController = null;
        resetColor();

    }

    public void removeOnlineAttackView() throws IOException {
        Platform.runLater(() -> {
            if (modelDataHandler.isWinner()) {
                Parent root = new EndController(stage);
                Scene scene = new Scene(root, 1920, 1080);

                stage.setTitle("End");
                stage.setScene(scene);
                stage.show();
            }
            rootpane.getChildren().remove(attackController);
            attackController = null;
        });
        modelDataHandler.resetSelectedSpaces();
        resetColor();
        view.resetColor(getColors(), allButtons);
    }

    private void sliderVisibility(Boolean visible) {
        if (visible) {
            moveSlider.setVisible(true);
            firstMarked.setVisible(true);
            secondMarked.setVisible(true);
            showMoveUnitsText.setVisible(true);
        } else {
            moveSlider.setVisible(false);
            firstMarked.setVisible(false);
            secondMarked.setVisible(false);
            showMoveUnitsText.setVisible(false);
        }
    }

    void checkPauseController() {
        if (rootpane.getChildren().contains(pauseController)) {
            rootpane.getChildren().remove(pauseController);
        } else {
            rootpane.getChildren().add(pauseController);
        }
    }

    private void setSpace(int id) throws IOException {
        if (modelDataHandler.receiveSelectedSpace(id)) {
            if (modelDataHandler.getSelectedSpace2() == null) {
                resetColor();
            } else {
                resetColor(modelDataHandler.getSelectedSpace().getId());
                moveSlider.setMax(modelDataHandler.getUnitsOnSpace(modelDataHandler.getSelectedSpace().getId()) - 1);
                moveSlider.setMin(0);
                moveSlider.setDisable(false);
            }
            view.updateTextUnits(id, modelDataHandler.getUnitsOnSpace(id), allButtons, this);
            view.setColor(getCube(id), Color.web(modelDataHandler.getColorOnSpace(id)).darker().darker(), allButtons);
            notifyObservers(new Space(modelDataHandler.getSpaceFromId(id)));
            if (localmode) {
                displayCubes(id);
            }
        } else {
            if (modelDataHandler.getSelectedSpace() == null && modelDataHandler.getSelectedSpace2() == null) {
                resetColor();
            }
        }
    }

    void setSpaceEvent(int id) {
        view.updateTextUnits(id, modelDataHandler.getUnitsOnSpace(id), allButtons, this);
        view.setColor(getCube(id), Color.web(modelDataHandler.getColorOnSpace(id)).darker().darker(), allButtons);
    }

    private List<Color> getColors() {
        List<Color> colors = new ArrayList<>();
        for (String color : modelDataHandler.getColorOnAllSpaces()) {
            colors.add(Color.web(color));
        }
        return colors;
    }

    void resetColor() throws IOException {
        view.resetColor(getColors(), allButtons);
        resetDisplayCubes();
        notifyObservers("resetColor");
    }

    public void resetColorOnline() {
        view.resetColor(getColors(), allButtons);
        resetDisplayCubes();
    }

    void resetColor(int id) {
        List<Color> colors = getColors();
        for (int i = 1; i < allButtons.size(); i++) {
            if (i == id) {
                colors.set(i, (colors.get(i).darker().darker()));
            }
        }
        view.resetColor(colors, allButtons);
    }

    public Button getCube(int id) {
        for (Button allButton : allButtons) {
            if (allButtons.get(id) == allButton) {
                return allButton;
            }
        }
        return null;
    }

    public void displayCubes(int id) {
        Platform.runLater(() -> {
            if (firstMarked.getStyle().isEmpty()) {
                view.updateDisplayCubes(firstMarked, modelDataHandler.getColorOnSpace(id));
            } else {
                view.updateDisplayCubes(secondMarked, modelDataHandler.getColorOnSpace(id));
            }
            if (firstDisplayText.getText().isEmpty()) {
                view.updateDisplayTexts(firstDisplayText, getTextFromList(id));
            } else {
                view.updateDisplayTexts(secondDisplayText, getTextFromList(id));
            }
        });
    }

    private void resetDisplayCubes() {
        view.resetDisplayCubes(firstMarked, firstDisplayText);
        view.resetDisplayCubes(secondMarked, secondDisplayText);
    }

    public void removeMarkedCube(Button button) {
        if (button == firstMarked) {
            firstDisplayText.setVisible(false);
            firstMarked.setVisible(false);
        } else {
            secondDisplayText.setVisible(false);
            secondMarked.setVisible(false);
        }

    }

    public void addMarkedCube(Button button) {
        if (button == firstMarked) {
            firstDisplayText.setVisible(true);
            firstMarked.setVisible(true);
        } else {
            secondDisplayText.setVisible(true);
            secondMarked.setVisible(true);
        }
    }

    private Text getTextFromList(int id) {
        for (Text allText : allTexts) {
            if (allText == allTexts.get(id)) {
                return allText;
            }
        }
        return null;
    }

    public String getTeamLogo() {
        return modelDataHandler.getTeamLogo();
    }

    public String getSpaceColor(int id) {
        return modelDataHandler.getColorOnSpace(id);
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
}