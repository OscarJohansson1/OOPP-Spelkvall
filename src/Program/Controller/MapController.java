package Program.Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    Button doneMove;
    @FXML
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
    Text vald;

    @FXML
    public
    Button deployButton;
    public
    @FXML
    Button attackButton;
    public
    @FXML
    Button moveButton;

    private boolean skipCheck = false;

    public boolean attack = true;

    ModelDataHandler modelDataHandler = new ModelDataHandler();
    private View view = new View(this);

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
    public void initialize()
    {
        //TODO: Hänvisa till Program.View.View.Program.View.View för att göra en setup av map



        cube1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                modelDataHandler.recieveSelectedSpace(1);
                view.resetColor(cube1,modelDataHandler.getColorOnSpace(1));

            }
        });
        text1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                modelDataHandler.recieveSelectedSpace(1);
                view.resetColor(cube1,modelDataHandler.getColorOnSpace(1));

            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.recieveSelectedSpace(2);
                view.resetColor(cube2,modelDataHandler.getColorOnSpace(2));

            }
        });
        text2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                modelDataHandler.recieveSelectedSpace(2);
                view.resetColor(cube2,modelDataHandler.getColorOnSpace(2));

            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                modelDataHandler.recieveSelectedSpace(3);
                view.resetColor(cube3,modelDataHandler.getColorOnSpace(3));

            }
        });
        text3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                modelDataHandler.recieveSelectedSpace(3);
                view.resetColor(cube3,modelDataHandler.getColorOnSpace(3));

            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                modelDataHandler.recieveSelectedSpace(4);

                view.resetColor(cube4,modelDataHandler.getColorOnSpace(4));

            }
        });
        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                modelDataHandler.recieveSelectedSpace(4);

                view.resetColor(cube4,modelDataHandler.getColorOnSpace(4));


            }
        });

        skipAttack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("ATTACK");


            }
        });
        doneMove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("MOVE");
            }
        });
        donedeploy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextPhase();
                view.updatePhase("DEPLOY");
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
                modelDataHandler.nextMove();
            }
        });
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                modelDataHandler.nextMove();
            }
        });

        view.setUpStart();
    }

    public Button getSkipButton(){return skipAttack; }

    public boolean getSkipCheck(){return skipCheck; }








}
