package program;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.controller.StartController;

import java.awt.*;

public class Chans extends Application {
    @Override
    public void start(Stage stage) {
        Parent start = new StartController(stage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setMaxWidth(screenSize.width);
        stage.setMaxHeight(screenSize.height);
        Scene menu = new Scene(start, screenSize.width, screenSize.height);

        stage.setFullScreen(true);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
