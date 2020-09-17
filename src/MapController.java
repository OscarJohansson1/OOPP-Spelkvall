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
    private
    AnchorPane rootpane;
    @FXML
    private
    Button skip;

    private Space selectedSpace;

    private Space space1;
    private Space space2;
    private Space space3;
    private Space space4;

    private Player player1 = new Player(10,1, Color.RED);
    private Player player2 = new Player(10, 2, Color.BLUE);

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
        space1 = new Space(1, player1, 1, "1",cube1);
        cube1.setFill(player1.getColor());
        space2 = new Space(2, player1, 1, "2",cube2);
        cube2.setFill(player1.getColor());
        space3 = new Space(3, player2, 1, "3",cube3);
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
                cube1.setFill(Color.BLACK);
                selectedSpace = space1;

            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube2.setFill(Color.BLACK);
                selectedSpace = space2;
            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube3.setFill(Color.BLACK);
                selectedSpace = space3;
            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube4.setFill(Color.BLACK);
                selectedSpace = space4;
            }
        });

    }

    public Space getSelectedSpace()
    {
        return  selectedSpace;
    }

    public Button getSkipButton(){return skip; }




}
