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


    private Space selectedSpace;

    private Space space1;
    private Space space2;
    private Space space3;
    private Space space4;

    Player player1 = new Player(10,1, Color.RED);
    Player player2 = new Player(10, 2, Color.BLUE);

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
        space1 = new Space(1, player1, 3, "1",cube1);
        cube1.setFill(player1.getColor());
        space2 = new Space(2, player1, 5, "2",cube2);
        cube2.setFill(player1.getColor());
        space3 = new Space(3, player2, 6, "3",cube3);
        cube3.setFill(player2.getColor());
        space4 = new Space(4, player2, 1, "4",cube4);
        cube4.setFill(player2.getColor());
        Text text = new Text();
        text.setText("1");
        text.setFill(Color.GREEN);
        text.setX(cube1.getX() + (cube1.getWidth() / 2));
        text.setY(cube1.getY() + (cube1.getHeight() / 2));
        rootpane.getChildren().add(text);



        cube1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                vald.setTranslateX(space1.getCube().getX());
                vald.setTranslateY(space1.getCube().getY());
                selectedSpace = space1;
            }
        });
        text1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space1;


            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space2;


            }
        });
        text2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space2;


            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space3;


            }
        });
        text3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space3;


            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                selectedSpace = space4;


            }
        });
        text4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {


                selectedSpace = space4;


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

    public Space getSelectedSpace()
    {
        System.out.println(selectedSpace + " selected");
        return  selectedSpace;
    }

    public Button getSkipButton(){return skip; }

    public boolean getSkipCheck(){return skipCheck; }

    public void update()
    {
        round = new Round(player1, this);
        playerText.setText(round.currentPlayer.getId() + "");
        phaseText.setText(round.currentPhase.name());
        updateText();

    }
    public void updateColor()
    {
        cube1.setFill(space1.getPlayer().getColor());
        cube2.setFill(space2.getPlayer().getColor());
        cube3.setFill(space3.getPlayer().getColor());
        cube4.setFill(space4.getPlayer().getColor());
        Color yes1 = space1.getPlayer().getColor();
        Color yes2 = space2.getPlayer().getColor();
        Color yes3 = space3.getPlayer().getColor();
        Color yes4 = space4.getPlayer().getColor();

        if(yes1 == yes2 && yes1 == yes3 && yes1 == yes4)
        {
            System.out.println("You Win!");
        }
    }
    public void updateText()
    {
        text1.setText(space1.getUnits() + "");
        text2.setText(space2.getUnits() + "");
        text3.setText(space3.getUnits() + "");
        text4.setText(space4.getUnits() + "");

        playerText.setText(round.currentPlayer.getId() + "");
        phaseText.setText(round.currentPhase.name());
    }




}
