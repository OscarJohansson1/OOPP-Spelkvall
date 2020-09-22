import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MapController extends AnchorPane {
    @FXML
    private
    Rectangle cube1;
    @FXML
    private
    Rectangle cube2;
    @FXML
    private
    Rectangle cube3;
    @FXML
    private
    Rectangle cube4;
    @FXML
    AnchorPane rootpane;
    @FXML
    AnchorPane startMenu;
    @FXML
    Button startButton;
    @FXML
    private
    Button skip;
    @FXML
    Button done;
    @FXML
    Button donedeploy;

    @FXML
    private
    Text playerText;
    @FXML
    Text phaseText;

    @FXML

    AnchorPane deployPhase;
    @FXML

    AnchorPane attackPhase;
    @FXML

    AnchorPane movePhase;

    @FXML
    Text text1;
    @FXML
    Text text2;
    @FXML
    Text text3;
    @FXML
    Text text4;
    @FXML
    Text vald;

    @FXML
    Button deployButton;

    @FXML
    Button attackButton;

    @FXML
    Button moveButton;

    private boolean skipCheck = false;

    public boolean attack = true;

    private Round round;

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
        //TODO: Hänvisa till View för att göra en setup av map



        cube1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                selectSpace(space1);
                resetColor(cube1,selectedSpace);

            }
        });
        text1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space1);
                resetColor(cube1,selectedSpace);

            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space2);
                resetColor(cube2,selectedSpace);

            }
        });
        text2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space2);
                resetColor(cube2,selectedSpace);

            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space3);
                resetColor(cube3,selectedSpace);

            }
        });
        text3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space3);
                resetColor(cube3,selectedSpace);

            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                selectSpace(space4);
                resetColor(cube4,selectedSpace);

            }
        });
        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectSpace(space4);
                resetColor(cube4,selectedSpace);


            }
        });

        skip.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextPhase();

            }
        });
        done.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextPhase();
            }
        });
        donedeploy.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextPhase();
            }
        });
        deployButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextMove();
            }
        });
        attackButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextMove();
            }
        });
        moveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                round.nextMove();
            }
        });


        update();

    }

    public Button getSkipButton(){return skip; }

    public boolean getSkipCheck(){return skipCheck; }








}
