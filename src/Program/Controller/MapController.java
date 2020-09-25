package Program.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import Program.Model.ModelDataHandler;
import Program.View.View;

import java.io.IOException;

public class MapController extends AnchorPane {
    @FXML
    public
    Rectangle cubeHubben;
    @FXML
    public
    Rectangle cubeBasen;
    @FXML
    public
    Rectangle cubeKajsabaren;
    @FXML
    public
    Rectangle cubeZaloonen;
    @FXML
    public
    Rectangle cubeWinden;
    @FXML
    public
    Rectangle cubeLoftTDet;
    @FXML
    public
    Rectangle cubeRodaRummet;
    @FXML
    public
    Rectangle cubeVerum;
    @FXML
    public
    Rectangle cubeVillan;
    @FXML
    public
    Rectangle cubeADammen;
    @FXML
    public
    Rectangle cubeFocus;
    @FXML
    public
    Rectangle cubeFortNox;
    @FXML
    public
    Rectangle cubeGTSpritis;
    @FXML
    public
    Rectangle cubeGoldenI;
    @FXML
    public
    Rectangle cubeChabo;
    @FXML
    public
    Rectangle cubeWijkanders;
    @FXML
    public
    Rectangle cubeHrum;
    @FXML
    public
    Rectangle cubeAlvan;
    @FXML
    public
    Rectangle cubeSpektrum;
    @FXML
    public
    Rectangle cubeGasquen;
    @FXML
    public
    Rectangle cubeChalmersplatsen;
    @FXML
    public
    Rectangle cubeOlgas;
    @FXML
    public
    Rectangle cubeRunAn;
    @FXML
    public
    Rectangle cubeTågvagnen;



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
    public Text textHubben;
    @FXML
    public Text textBasen;
    @FXML
    public Text textKajsabaren;
    @FXML
    public Text textZaloonen;
    @FXML
    private
    Button deployButton;
    @FXML
    private
    Button attackButton;
    @FXML
    private
    Button moveButton;

    private ModelDataHandler modelDataHandler = new ModelDataHandler();
    private View view = new View();

    MapController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("karta.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        initialize();
    }
    private void initialize() {
        //TODO: Hänvisa till Program.View.View.Program.View.View för att göra en setup av map

        cubeHubben.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(1);
            }
        });
        textHubben.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(1);
            }
        });
        cubeBasen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(2);
            }
        });
        textBasen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(2);
            }
        });
        cubeKajsabaren.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(3);
            }
        });
        textKajsabaren.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(3);
            }
        });
        cubeZaloonen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(4);
            }
        });
        textZaloonen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(4);
            }
        });

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
        view.updateTextUnits(1, modelDataHandler.findUnitsOnSpace(1));
        view.updateTextUnits(2, modelDataHandler.findUnitsOnSpace(2));
        view.updateTextUnits(3, modelDataHandler.findUnitsOnSpace(3));
        view.updateTextUnits(4, modelDataHandler.findUnitsOnSpace(4));
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
            view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id));
            view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), this);
        }
    }
    private void setSpaceEvent(int id) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id));
    }
    private void setSpaceEvent(int id, int id2) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id));
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id), this);
        view.updateTextUnits(id2, modelDataHandler.findUnitsOnSpace(id2));
        view.setColor(getCube(id2), modelDataHandler.getColorOnSpace(id2), this);
    }
    private void resetColor() {
        Color[] colors = new Color[] {
                null,
                modelDataHandler.getColorOnSpace(1),
                modelDataHandler.getColorOnSpace(2),
                modelDataHandler.getColorOnSpace(3),
                modelDataHandler.getColorOnSpace(4)

        };
        view.resetColor(colors,this);
    }
    private void resetColor(int id) {
        Color[] colors = new Color[] {
                null,
                modelDataHandler.getColorOnSpace(1),
                modelDataHandler.getColorOnSpace(2),
                modelDataHandler.getColorOnSpace(3),
                modelDataHandler.getColorOnSpace(4)

        };
        for(int i = 1; i < colors.length; i++)
        {
            if(i == id)
            {
                colors[i] = colors[i].darker().darker();
            }
        }
        view.resetColor(colors,this);
    }
    private Rectangle getCube(int id) {
        if (id == 1) {
            return cubeHubben;
        } else if (id == 2) {
            return cubeBasen;
        } else if (id == 3) {
            return cubeKajsabaren;
        } else if (id == 4) {
            return cubeZaloonen;
        }
        return null;
    }
}
