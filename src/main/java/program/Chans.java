package program;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import program.controller.StartController;

import java.awt.*;

/**
 * The main class of the application that starts the application and sets the stage for the game.
 */
public class Chans extends Application {
    @Override
    public void start(Stage stage) {
        Parent start = new StartController(stage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        stage.setMaxWidth(screenSize.width);
        stage.setMaxHeight(screenSize.height);
        Scene menu = new Scene(start, screenSize.width, screenSize.height);


        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setFullScreen(true);

        stage.setTitle("Menu");
        stage.setScene(menu);
        stage.show();
        stage.setOnCloseRequest(we -> {
            System.out.println("Stage is closing");
            Platform.exit();
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
