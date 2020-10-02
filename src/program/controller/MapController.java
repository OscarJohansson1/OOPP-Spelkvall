package program.controller;

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

public class MapController extends AnchorPane {
    @FXML
    public
    Button cubeHubben;
    @FXML
    public
    Button cubeBasen;
    @FXML
    public
    Button cubeKajsabaren;
    @FXML
    public
    Button cubeZaloonen;
    @FXML
    public
    Button cubeWinden;
    @FXML
    public
    Button cubeLofTDet;
    @FXML
    public
    Button cubeRodaRummet;
    @FXML
    public
    Button cubeVerum;
    @FXML
    public
    Button cubeVillan;
    @FXML
    public
    Button cubeADammen;
    @FXML
    public
    Button cubeFocus;
    @FXML
    public
    Button cubeFortNox;
    @FXML
    public
    Button cubeGTSpritis;
    @FXML
    public
    Button cubeGoldenI;
    @FXML
    public
    Button cubeChabo;
    @FXML
    public
    Button cubeWijkanders;
    @FXML
    public
    Button cubeHrum;
    @FXML
    public
    Button cubeAlvan;
    @FXML
    public
    Button cubeSpektrum;
    @FXML
    public
    Button cubeGasquen;
    @FXML
    public
    Button cubeChalmersplatsen;
    @FXML
    public
    Button cubeOlgas;
    @FXML
    public
    Button cubeRunAn;
    @FXML
    public
    Button cubeTagvagnen;
    @FXML
    public
    Button cubeOrigogarden;

    @FXML
    public
    Text textHubben;
    @FXML
    public
    Text textBasen;
    @FXML
    public
    Text textKajsaBaren;
    @FXML
    public
    Text textZaloonen;
    @FXML
    public
    Text textWinden;
    @FXML
    public
    Text textLofTDet;
    @FXML
    public
    Text textRodaRummet;
    @FXML
    public
    Text textVerum;
    @FXML
    public
    Text textVillan;
    @FXML
    public
    Text textAdammen;
    @FXML
    public
    Text textFocus;
    @FXML
    public
    Text textFortNox;
    @FXML
    public
    Text textGTSpritis;
    @FXML
    public
    Text textGoldenI;
    @FXML
    public
    Text textChabo;
    @FXML
    public
    Text textWijkanders;
    @FXML
    public
    Text textHrum;
    @FXML
    public
    Text textAlvan;
    @FXML
    public
    Text textSpektrum;
    @FXML
    public
    Text textGasquen;
    @FXML
    public
    Text textChalmersplatsen;
    @FXML
    public
    Text textOlgas;
    @FXML
    public
    Text textRunAn;
    @FXML
    public
    Text textTagvagnen;
    @FXML
    public
    Text textOrigogården;

    @FXML
    public
    AnchorPane rootpane;
    @FXML
    AnchorPane startMenu;
    @FXML
    Button startButton;
    @FXML
    private
    Button skipAttack;
    @FXML
    private
    Button doneMove;
    @FXML
    private
    Button donedeploy;

    @FXML
    public
    Text playerText;
    @FXML
    public
    Text phaseText;
    @FXML
    public
    Text deployableUnitsText;

    @FXML
    public
    AnchorPane deployPhase;
    @FXML
    public
    AnchorPane attackPhase;
    @FXML
    public
    AnchorPane movePhase;
    @FXML
    private
    Button deployButton;
    @FXML
    private
    Button attackButton;
    @FXML
    private
    Button moveButton;

    @FXML
    private
    Slider moveSlider;
    @FXML
    private
    Button firstMarked;
    @FXML
    private
    Button secondMarked;
    @FXML
    private
    Text showMoveUnitsText;

    @FXML
    private
    Text firstDisplayText;
    @FXML
    private
    Text secondDisplayText;


    private ModelDataHandler modelDataHandler;
    private MapView view = new MapView();
    private List<Button> allButtons;
    private List<Text> allTexts;


    MapController(List<Color> colors) {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("karta.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.modelDataHandler = modelDataHandler;
        allButtons = new ArrayList<>(Arrays.asList(cubeHubben,cubeBasen,cubeKajsabaren,cubeZaloonen,cubeWinden,cubeLofTDet,
                cubeRodaRummet,cubeVerum,cubeVillan,cubeADammen,cubeFocus,cubeFortNox,cubeGTSpritis,cubeGoldenI,cubeChabo,cubeWijkanders,cubeHrum,
                cubeAlvan,cubeSpektrum,cubeGasquen,cubeChalmersplatsen,cubeOlgas,cubeRunAn,cubeTagvagnen,cubeOrigogarden));

        allTexts = new ArrayList<>(Arrays.asList(textHubben, textBasen, textKajsaBaren, textZaloonen, textWijkanders, textLofTDet,
                textRodaRummet,textVerum, textVillan, textAdammen, textFocus, textFortNox,textGTSpritis, textGoldenI, textChabo,textWijkanders,textHrum,
                textAlvan,textSpektrum,textGasquen,textChalmersplatsen,textOlgas,textRunAn, textTagvagnen,textOrigogården));


        modelDataHandler = new ModelDataHandler(allButtons.size(), colors);
        initialize();
    }
    private void initialize() {
        //TODO: Hänvisa till Program.View.View.Program.View.View för att göra en setup av map


        for (int i = 0; i<allButtons.size(); i++){
            int var = i;
            allButtons.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    setSpace(var);
                }
            });
        }
        skipAttack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("MOVE", MapController.this);
                resetColor();
                resetDisplayCubes();
                resetDisplayText();
                sliderVisibility(true);
                addMarkedCube(secondMarked);
            }
        });
        doneMove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("DEPLOY", MapController.this);
                resetColor();
                resetDisplayCubes();
                resetDisplayText();
                view.updatePhasePlayerText(modelDataHandler.getCurrentPlayerName(), "DEPLOY",MapController.this);
                sliderVisibility(true);
                removeMarkedCube(secondMarked);
                view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
            }
        });
        donedeploy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("ATTACK", MapController.this);
                resetColor();
                resetDisplayText();
                sliderVisibility(false);
                addMarkedCube(firstMarked);
                addMarkedCube(secondMarked);

            }
        });
        deployButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
                    view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
                }
            }
        });
        attackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
                    modelDataHandler.resetSelectedSpace();
                }
            }
        });
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
                    modelDataHandler.resetSelectedSpace();
                }
                resetDisplayCubes();
                resetDisplayText();
            }
        });

        moveSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                modelDataHandler.getSliderAmount(newValue.intValue());
                view.updateSliderText(newValue.intValue(), showMoveUnitsText);
            }
        });

        view.setUpStart(this);
        view.updatePhasePlayerText(modelDataHandler.getCurrentPlayerName(), modelDataHandler.getCurrenPhase(), this);
        for(int i = 0; i < allButtons.size(); i++)
        {
            view.updateTextUnits(i, modelDataHandler.findUnitsOnSpace(i), allButtons);
        }
        resetColor();
        view.updateDeployableUnits(deployableUnitsText, modelDataHandler.getDeployableUnits());
        resetDisplayText();
        removeMarkedCube(secondMarked);
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

    private void setSpace(int id) {
        if(modelDataHandler.receiveSelectedSpace(id))
        {
            if(modelDataHandler.getSelectedSpace2() == null)
            {
                resetDisplayText();
                resetColor();
            }
            else {
                resetColor(modelDataHandler.getSelectedSpace().getId());
                resetDisplayText(secondDisplayText);
            }
            view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons);
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
    }
    private void setSpaceEvent(int id) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons);
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), allButtons);
    }
    private void setSpaceEvent(int id, int id2) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons);
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id), allButtons);
        view.updateTextUnits(id2, modelDataHandler.findUnitsOnSpace(id2), allButtons);
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

    private void resetDisplayCubes(){
        view.resetDisplayCubes(firstMarked);
        view.resetDisplayCubes(secondMarked);
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

    private void resetDisplayText(){
        view.resetDisplayTexts(firstDisplayText);
        view.resetDisplayTexts(secondDisplayText);
    }
    private void resetDisplayText(Text text){
        view.resetDisplayTexts(text);
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
}