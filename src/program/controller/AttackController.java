package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import program.view.AttackView;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AttackController extends AnchorPane {

    @FXML private ImageView attackerDieImage1;
    @FXML private ImageView attackerDieImage2;
    @FXML private ImageView attackerDieImage3;

    @FXML private ImageView defenderDieImage1;
    @FXML private ImageView defenderDieImage2;

    @FXML private Button attackButton;
    @FXML private Button abortButton;



    private List<ImageView> images;

    private List<Integer> dices;
    private Stage stage;
    private AttackView attackView = new AttackView();
    private MapController mapController;
    AttackController(Stage stage, MapController mapController, List<Integer> dices) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attackMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.stage = stage;

        this.dices = dices;

        images = new ArrayList<>(Arrays.asList(attackerDieImage1,attackerDieImage2,attackerDieImage3, defenderDieImage1,defenderDieImage2));

        this.mapController = mapController;

    }
     public void attackButtonPressed() throws FileNotFoundException {
         Random random = new Random();
         System.out.println(dices.size());
         for(int i = 0; i < dices.size(); i++)
         {
             if(i > 2)
             {
                 attackView.updateDie(images.get(i),dices.get(i),"Black");
             }
             else {
                 attackView.updateDie(images.get(i),dices.get(i),"White");
             }
         }
         attackButton.setVisible(false);
         abortButton.setText("Attack done");
     }
     public void abortButtonPressed()
     {
         mapController.removeAttackView();
     }
}
