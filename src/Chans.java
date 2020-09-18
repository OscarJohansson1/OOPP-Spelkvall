import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;

public class Chans extends Application {



    @Override
    public void start(Stage stage) throws Exception {



        StartController startController = new StartController(stage);
        Parent start = startController;

        Scene menu = new Scene(start, 1364, 900);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();





    }


    public static void main(String args[]) {

        //Player player1 = new Player(20, 2, color.BLACK);
        //Player player2 = new Player(20, 2, color.RED);
        //Space mySpace = new Space(1, player1, 5, "Hubba", rect);
        //Space enemySpace = new Space(2, player2, 3,"Basa", rect );

        //attack.findHighestDie(dice.rollNDIce(5));
        //attack.DeclareAttack(mySpace, enemySpace, 3);
        //attack.calculateAttack(3, 1, mySpace, enemySpace);

        launch(args);



    }
}
