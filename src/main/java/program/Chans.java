package program;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.client.Client;
import program.controller.StartController;

import java.awt.*;
import java.io.IOException;

public class Chans extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StartController startController = new StartController(stage);
        Client.getClient().startConnection("95.80.61.51", 6666, startController);
        Parent start = startController;
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
