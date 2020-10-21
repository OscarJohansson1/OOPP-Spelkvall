package program;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import program.client.Client;
import program.controller.StartController;

import java.awt.*;
import java.io.IOException;

public class Chans extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent start = new StartController(stage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setMaxWidth(screenSize.width);
        stage.setMaxHeight(screenSize.height);
        Scene menu = new Scene(start, screenSize.width, screenSize.height);


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.setFullScreen(true);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
