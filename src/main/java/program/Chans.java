package program;

import program.controller.StartController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Chans extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent start = new StartController(stage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Scene menu = new Scene(start, screenSize.width, screenSize.height);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
