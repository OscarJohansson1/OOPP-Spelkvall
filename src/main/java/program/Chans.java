package program;

import program.controller.StartController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chans extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent start = new StartController(stage);

        Scene menu = new Scene(start, 1920, 1080);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }

    public void stop()
    {

    }
}
