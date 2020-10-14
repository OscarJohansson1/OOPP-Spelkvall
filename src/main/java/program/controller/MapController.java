package program.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import program.model.ModelDataHandler;
import program.view.MapView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
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
public class MapController extends AnchorPane {

    @FXML private Button cubeHubben;
    @FXML private Button cubeBasen;
    @FXML private Button cubeKajsabaren;
    @FXML private Button cubeZaloonen;
    @FXML private Button cubeWinden;
    @FXML private Button cubeLofTDet;
    @FXML private Button cubeRodaRummet;
    @FXML private Button cubeVerum;
    @FXML private Button cubeVillan;
    @FXML private Button cubeADammen;
    @FXML private Button cubeFocus;
    @FXML private Button cubeFortNox;
    @FXML private Button cubeGTSpritis;
    @FXML private Button cubeGoldenI;
    @FXML private Button cubeChabo;
    @FXML private Button cubeWijkanders;
    @FXML private Button cubeHrum;
    @FXML private Button cubeAlvan;
    @FXML private Button cubeSpektrum;
    @FXML private Button cubeGasquen;
    @FXML private Button cubeChalmersplatsen;
    @FXML private Button cubeOlgas;
    @FXML private Button cubeRunAn;
    @FXML private Button cubeTagvagnen;
    @FXML private Button cubeOrigogarden;
    @FXML private Button cubeKalleGlader;
    @FXML private Button cubeTvargatan;

    @FXML private Text textHubben;
    @FXML private Text textBasen;
    @FXML private Text textKajsaBaren;
    @FXML private Text textZaloonen;
    @FXML private Text textWinden;
    @FXML private Text textLofTDet;
    @FXML private Text textRodaRummet;
    @FXML private Text textVerum;
    @FXML private Text textVillan;
    @FXML private Text textAdammen;
    @FXML private Text textFocus;
    @FXML private Text textFortNox;
    @FXML private Text textGTSpritis;
    @FXML private Text textGoldenI;
    @FXML private Text textChabo;
    @FXML private Text textWijkanders;
    @FXML private Text textHrum;
    @FXML private Text textAlvan;
    @FXML private Text textSpektrum;
    @FXML private Text textGasquen;
    @FXML private Text textChalmersplatsen;
    @FXML private Text textOlgas;
    @FXML private Text textRunAn;
    @FXML private Text textTagvagnen;
    @FXML private Text textOrigogarden;
    @FXML private Text textKalleGlader;
    @FXML private Text textTvargatan;

    @FXML public AnchorPane rootpane;
    @FXML AnchorPane startMenu;

    @FXML public AnchorPane deployPhase;
    @FXML public AnchorPane attackPhase;
    @FXML public AnchorPane movePhase;

    @FXML private Slider moveSlider;

    @FXML private Button startButton;
    @FXML private Button skipAttack;
    @FXML private Button doneMove;
    @FXML private Button donedeploy;
    @FXML private Button deployButton;
    @FXML private Button attackButton;
    @FXML private Button moveButton;
    @FXML private Button firstMarked;
    @FXML private Button secondMarked;
    @FXML public Button showCurrentPlayer;

    @FXML private Text showMoveUnitsText;
    @FXML private Text firstDisplayText;
    @FXML private Text secondDisplayText;
    @FXML public Text playerText;
    @FXML public Text phaseText;
    @FXML private Text deployableUnitsText;

    @FXML public Pane phasePane;
    @FXML public Pane sliderPane;

    @FXML public ImageView imageTeamLogo;

    ModelDataHandler modelDataHandler;
    private MapView view = new MapView();
    private AttackController attackController;
    private List<Button> allButtons;
    private List<Text> allTexts;
    private Stage stage;
    private PauseController pauseController;


    MapController(List<Color> colors, List<String> logoNames, Stage stage) throws IOException {

        this.stage = stage;
        pauseController = new PauseController(stage, this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("karta.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        allButtons = new ArrayList<>(Arrays.asList(cubeHubben,cubeBasen,cubeKajsabaren,cubeZaloonen,cubeWinden,cubeLofTDet,
                cubeRodaRummet,cubeVerum,cubeVillan,cubeADammen,cubeFocus,cubeFortNox,cubeGTSpritis,cubeGoldenI,cubeChabo,cubeWijkanders,cubeHrum,
                cubeAlvan,cubeSpektrum,cubeGasquen,cubeChalmersplatsen,cubeOlgas,cubeRunAn,cubeTagvagnen,cubeOrigogarden, cubeKalleGlader, cubeTvargatan));

        allTexts = new ArrayList<>(Arrays.asList(textHubben, textBasen, textKajsaBaren, textZaloonen, textWinden, textLofTDet,
                textRodaRummet,textVerum, textVillan, textAdammen, textFocus, textFortNox,textGTSpritis, textGoldenI, textChabo,textWijkanders,textHrum,
                textAlvan,textSpektrum,textGasquen,textChalmersplatsen,textOlgas,textRunAn, textTagvagnen,textOrigogarden, textKalleGlader, textTvargatan));

        modelDataHandler = ModelDataHandler.getModelDataHandler();
        modelDataHandler.initialize(allButtons.size(), colors, logoNames);
        initialize();

    }
    private void initialize() throws IOException {
        //TODO: Hänvisa till Program.View.View.Program.View.View för att göra en setup av map
        //EchoClient.getEchoClient().recieveController(this);
        //EchoClient client = EchoClient.getEchoClient();
        //client.startConnection("95.80.61.51", 6666);
        //client.deploy();
        for (int i = 0; i<allButtons.size(); i++){
            int var = i;
            allButtons.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    setSpace(var);
                }
            });
        }

        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent t) {
                if(t.getCode()== KeyCode.ESCAPE)
                {
                    checkPauseController();
                }
            }
        });

        skipAttack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("MOVE", MapController.this);
                resetColor();
                resetDisplayCubes();
                sliderVisibility(true);
                addMarkedCube(secondMarked);
                moveSlider.setMax(25);
            }
        });
        doneMove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("DEPLOY", MapController.this);
                resetColor();
                resetDisplayCubes();
                view.updatePhaseText("DEPLOY",MapController.this);
                view.updateCurrentPlayer(modelDataHandler.getCurrentPlayerColor(), MapController.this, modelDataHandler.getCurrentPlayerName());
                sliderVisibility(true);
                removeMarkedCube(secondMarked);
                view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
                donedeploy.setDisable(true);
                donedeploy.setStyle("-fx-background-color: #000000");
                modelDataHandler.setDeployableUnits(modelDataHandler.calculateDeployableUnits());
                view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
                moveSlider.setMax(modelDataHandler.getDeployableUnits());
            }
        });
        donedeploy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (modelDataHandler.firstDeployment){
                    modelDataHandler.nextPlayer();
                    view.updateCurrentPlayer(modelDataHandler.getCurrentPlayerColor(), MapController.this, modelDataHandler.getCurrentPlayerName());
                    view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
                    moveSlider.setMax(modelDataHandler.getDeployableUnits());
                    resetDisplayCubes();
                    resetColor();
                }
                else{
                    modelDataHandler.nextPhase();
                    view.updatePhase("ATTACK", MapController.this);
                    view.updateCurrentPlayer(modelDataHandler.getCurrentPlayerColor(), MapController.this, modelDataHandler.getCurrentPlayerName());
                    resetColor();
                    resetDisplayCubes();
                    sliderVisibility(false);
                    addMarkedCube(firstMarked);
                    addMarkedCube(secondMarked);
                }
            }
        });
        deployButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deploy();
            }
        });
        attackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                attack();
            }
        });
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                move();
            }
        });
        moveSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                modelDataHandler.setSliderAmount(newValue.intValue());
                view.updateSliderText(newValue.intValue(), showMoveUnitsText);
            }
        });
        view.updatePhaseText(modelDataHandler.getCurrenPhase(), this);
        view.updateCurrentPlayer(modelDataHandler.getCurrentPlayerColor(), this, modelDataHandler.getCurrentPlayerName());
        for(int i = 0; i < allButtons.size(); i++)
        {
            view.updateTextUnits(i, modelDataHandler.findUnitsOnSpace(i), allButtons, this);
        }
        resetColor();
        view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
        resetDisplayCubes();
        removeMarkedCube(secondMarked);
        donedeploy.setDisable(true);
        donedeploy.setStyle("-fx-background-color: #000000");
        moveSlider.setMax(modelDataHandler.getDeployableUnits());
    }
    public void deploy(){
        if(modelDataHandler.nextMove())
        {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
            view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
            moveSlider.setMax(modelDataHandler.getDeployableUnits());
        }
        if (modelDataHandler.getDeployableUnits() == 0){
            donedeploy.setDisable(false);
            donedeploy.setStyle(null);
        }
    }
    public void attack() {
        System.out.println("Attacking");
        view.updatePhase("ATTACK",this);
        if(modelDataHandler.nextMove())
        {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
            changeToAttackView();
        }
    }
    public void move(){
        if(modelDataHandler.nextMove())
        {
            setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
            modelDataHandler.resetSelectedSpaces();
        }
        resetDisplayCubes();
    }
    private void changeToAttackView() {
        attackController = new AttackController(this, stage);
        rootpane.getChildren().add(attackController);
    }
    void removeAttackView() {
        rootpane.getChildren().remove(attackController);
        for(int i = 0; i < allButtons.size(); i++)
        {
            view.updateTextUnits(i,modelDataHandler.findUnitsOnSpace(i),allButtons, this);
            view.setColor(allButtons.get(i), modelDataHandler.getColorOnAllSpaces().get(i),allButtons);
        }
        modelDataHandler.resetSelectedSpaces();
        resetDisplayCubes();

    }
    private void sliderVisibility(Boolean visible){
        if(visible){
            moveSlider.setVisible(true);
            firstMarked.setVisible(true);
            secondMarked.setVisible(true);
            showMoveUnitsText.setVisible(true);
        }
        else{
            moveSlider.setVisible(false);
            firstMarked.setVisible(false);
            secondMarked.setVisible(false);
            showMoveUnitsText.setVisible(false);
        }
    }

    void checkPauseController(){
        if(rootpane.getChildren().contains(pauseController)) {
            rootpane.getChildren().remove(pauseController);
        }else{
            rootpane.getChildren().add(pauseController);
        }
    }

    private void setSpace(int id) {
        if(modelDataHandler.receiveSelectedSpace(id))
        {
            if(modelDataHandler.getSelectedSpace2() == null)
            {
                resetDisplayCubes();
                resetColor();
            }
            else {
                resetColor(modelDataHandler.getSelectedSpace().getId());
                resetDisplayCubes(secondMarked,secondDisplayText);
                moveSlider.setMax(modelDataHandler.findUnitsOnSpace(modelDataHandler.getSelectedSpace().getId())-1);
                moveSlider.setMin(1);
            }
            view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons, this);
            view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), allButtons);
            displayCubes(id);
            if(firstDisplayText.getText().isEmpty())
            {
                displayText(firstDisplayText, getTextFromList(id));
            }
            else if(secondDisplayText.getText().isEmpty()){
                displayText(secondDisplayText, getTextFromList(id));
            }
        }
        else{
            if(modelDataHandler.getSelectedSpace() == null && modelDataHandler.getSelectedSpace2() == null){
                resetDisplayCubes();
                resetColor();
            }
        }
    }

    private void setSpaceEvent(int id) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons, this);
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), allButtons);
    }

    private void setSpaceEvent(int id, int id2) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons, this);
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id), allButtons);
        view.updateTextUnits(id2, modelDataHandler.findUnitsOnSpace(id2), allButtons, this);
        view.setColor(getCube(id2), modelDataHandler.getColorOnSpace(id2), allButtons);
    }

    private List<Color> getColors()
    {
        return modelDataHandler.getColorOnAllSpaces();
    }

    private void resetColor() {
        view.resetColor(getColors(),allButtons);
    }

    private void resetColor(int id) {
        List<Color> colors = getColors();
        for(int i = 1; i < allButtons.size(); i++)
        {
            if(i == id) {
                colors.set(i,colors.get(i).darker().darker());
            }
        }
        view.resetColor(colors,allButtons);
    }

    private Button getCube(int id) {
        for (Button allButton : allButtons) {
            if (allButtons.get(id) == allButton) {
                return allButton;
            }
        }
        return null;
    }

    private void displayCubes(int id){
        if (firstMarked.getStyle().isEmpty()){
            view.updateDisplayCubes(firstMarked, modelDataHandler.getColorOnSpace(id));
        }
        else{
            view.updateDisplayCubes(secondMarked, modelDataHandler.getColorOnSpace(id));
        }
    }

    private void resetDisplayCubes(Button button, Text text){
        view.resetDisplayCubes(button,text);
    }
    private void resetDisplayCubes(){
        view.resetDisplayCubes(firstMarked,firstDisplayText);
        view.resetDisplayCubes(secondMarked,secondDisplayText);
    }

    private void removeMarkedCube(Button button)
    {
        if(button == firstMarked)
        {
            firstDisplayText.setVisible(false);
            firstMarked.setVisible(false);
        }
        else {
            secondDisplayText.setVisible(false);
            secondMarked.setVisible(false);
        }

    }
    private void addMarkedCube(Button button)
    {
        if(button == firstMarked)
        {
            firstDisplayText.setVisible(true);
            firstMarked.setVisible(true);
        }
        else {
            secondDisplayText.setVisible(true);
            secondMarked.setVisible(true);
        }
    }

    private void displayText(Text displayText, Text cubeText){
       view.updateDisplayTexts(displayText, cubeText);
    }

    private Text getTextFromList(int id)
    {
        for (Text allText : allTexts) {
            if (allText == allTexts.get(id)) {
                return allText;
            }
        }
        return null;
    }

    public Image getTeamLogo(int id){
        return modelDataHandler.getTeamLogo(id);
    }
    public Image getTeamLogo(){
        return modelDataHandler.getTeamLogo();
    }

    public Color getSpaceColor(int id){
        return modelDataHandler.getColorOnSpace(id);
    }
}