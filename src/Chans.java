import Program.Controller.StartController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chans extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        StartController startController = new StartController(stage);
        Parent start = startController;

        Scene menu = new Scene(start, 1920, 1080);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
    }


    public static void main(String args[]) {

        //Program.View.View.Model.Player player1 = new Program.View.View.Model.Player(20, 2, color.BLACK);
        //Program.View.View.Model.Player player2 = new Program.View.View.Model.Player(20, 2, color.RED);
        //Program.View.View.Model.Space mySpace = new Program.View.View.Model.Space(1, player1, 5, "Hubba", rect);
        //Program.View.View.Model.Space enemySpace = new Program.View.View.Model.Space(2, player2, 3,"Basa", rect );

        //attack.findHighestDie(dice.rollNDIce(5));
        //attack.DeclareAttack(mySpace, enemySpace, 3);
        //attack.calculateAttack(3, 1, mySpace, enemySpace);

        launch(args);



    }
}
