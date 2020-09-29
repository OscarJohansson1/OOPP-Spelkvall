package Program.Controller;

import Program.Model.ModelDataHandler;
import Program.View.mapView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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

    private ModelDataHandler modelDataHandler;
    private mapView view = new mapView();
    private List<Button> allButtons;


    MapController() {


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
                cubeAlvan,cubeSpektrum,cubeGasquen,cubeChalmersplatsen,cubeOlgas,cubeRunAn,cubeTagvagnen, cubeOrigogarden));

        modelDataHandler = new ModelDataHandler(allButtons.size());
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
            }
        });
        doneMove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("DEPLOY", MapController.this);
                resetColor();
                view.updatePhasePlayerText(modelDataHandler.getCurrentPlayerName(), "DEPLOY",MapController.this);
            }
        });
        donedeploy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("ATTACK", MapController.this);
                resetColor();
            }
        });
        deployButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId());
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
            }
        });
        view.setUpStart(this);
        view.updatePhasePlayerText(modelDataHandler.getCurrentPlayerName(), modelDataHandler.getCurrenPhase(), this);
        for(int i = 0; i < allButtons.size(); i++)
        {
            view.updateTextUnits(i, modelDataHandler.findUnitsOnSpace(i), allButtons);
        }
        resetColor();
    }
    private void setSpace(int id) {
        if(modelDataHandler.receiveSelectedSpace(id))
        {
            if(modelDataHandler.getSelectedSpace2() == null)
            {
                resetColor();
            }
            else {
                resetColor(modelDataHandler.getSelectedSpace().getId());
            }
            view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id), allButtons);
            view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), allButtons);
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
}
