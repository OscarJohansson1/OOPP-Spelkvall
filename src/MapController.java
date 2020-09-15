import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

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

    private Space selectedSpace;

    private Space space1;
    private Space space2;
    private Space space3;
    private Space space4;

    private Player player1 = new Player(10,1, "röd");
    private Player player2 = new Player(10, 2, "blå");

    public MapController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("karta.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    public void initialize()
    {
        space1 = new Space(1, player1, 1, "1",cube1);
        space2 = new Space(2, player1, 1, "2",cube2);
        space3 = new Space(3, player2, 1, "3",cube3);
        space4 = new Space(4, player2, 1, "4",cube4);
        cube1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube1.setFill(Color.RED);
                selectedSpace = space1;

            }
        });
        cube2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube2.setFill(Color.RED);
                selectedSpace = space2;
            }
        });
        cube3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube3.setFill(Color.RED);
                selectedSpace = space3;
            }
        });
        cube4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cube4.setFill(Color.RED);
                selectedSpace = space4;
            }
        });

    }

}
