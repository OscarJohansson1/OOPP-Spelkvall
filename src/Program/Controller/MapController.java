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
    Rectangle cube1;
    @FXML
    public
    Rectangle cube2;
    @FXML
    public
    Rectangle cube3;
    @FXML
    public
    Rectangle cube4;
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
    public Text text1;
    @FXML
    public Text text2;
    @FXML
    public Text text3;
    @FXML
    public Text text4;
    @FXML
    private
    Button deployButton;
    @FXML
    private
    Button attackButton;
    @FXML
    private
    Button moveButton;

    private boolean skipCheck = false;

    public boolean attack = true;

    private ModelDataHandler modelDataHandler = new ModelDataHandler();
    private View view = new View();

    public MapController() {

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

    public void initialize() {
        //TODO: Hänvisa till Program.View.View.Program.View.View för att göra en setup av map

        cube1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(1);
            }
        });
        text1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(1);
            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(2);
            }
        });
        text2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(2);
            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(3);
            }
        });
        text3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(3);
            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setSpace(4);
            }
        });
        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                modelDataHandler.nextMove();
            }
        });
        attackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
                }
            }
        });
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(modelDataHandler.nextMove())
                {
                    setSpaceEvent(modelDataHandler.getSelectedSpace().getId(),modelDataHandler.getSelectedSpace2().getId());
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
            if (modelDataHandler.getSelectedSpace() != null) {
                view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id));
                view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id).darker().darker(), this);
            }
        }
    }
    private void setSpaceEvent(int id, int id2) {
        view.updateTextUnits(id, modelDataHandler.findUnitsOnSpace(id));
        view.setColor(getCube(id), modelDataHandler.getColorOnSpace(id), this);
        view.updateTextUnits(id2, modelDataHandler.findUnitsOnSpace(id2));
        view.setColor(getCube(id2), modelDataHandler.getColorOnSpace(id2), this);
    }
    private void resetColor()
    {
        Color[] colors = new Color[] {
                null,
                modelDataHandler.getColorOnSpace(1),
                modelDataHandler.getColorOnSpace(2),
                modelDataHandler.getColorOnSpace(3),
                modelDataHandler.getColorOnSpace(4)

        };
        view.resetColor(colors,this);
    }

    private Rectangle getCube(int id) {

        if (id == 1) {
            return cube1;
        } else if (id == 2) {
            return cube2;
        } else if (id == 3) {
            return cube3;
        } else if (id == 4) {
            return cube4;
        }
        return null;
    }
}
