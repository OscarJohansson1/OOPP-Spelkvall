import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chans extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        MapController mapController = new MapController();
        Parent root = mapController;

        Scene scene = new Scene(root, 1600, 900);

        stage.setTitle("Chans");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String args[]) {launch(args);


    }
}
