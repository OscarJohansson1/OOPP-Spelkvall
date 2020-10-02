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
import java.util.*;


public class AttackController extends AnchorPane {

    @FXML private ImageView attackerDieImage1;
    @FXML private ImageView attackerDieImage2;
    @FXML private ImageView attackerDieImage3;

    @FXML private ImageView defenderDieImage1;
    @FXML private ImageView defenderDieImage2;

    @FXML private Button attackButton;
    @FXML private Button abortButton;



    private List<ImageView> images;
    private List<ImageView> fakeimages;
    private Timer timer = new Timer();
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
        fakeimages = images;
        this.mapController = mapController;


    }
     public void attackButtonPressed() {
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
     private void spinDices()
     {

         resetTask();
         while(true)
         {
             if(fakeimages.size() != 0)
             {
                 for (ImageView fakeimage : fakeimages) {
                     attackView.updateDie(fakeimage, new Random().nextInt(7), "White");
                 }
             }
             else {
                 break;
             }
         }
     }
     private TimerTask task = new TimerTask() {
         @Override
         public void run() {
             if(fakeimages.size() != 0)
             {
                 fakeimages.remove(fakeimages.size() - 1);

             }
         }
     };
    private void resetTask()
    {
        timer.cancel();
        timer = new Timer();
        TimerTask newtask = task;
        timer.schedule(task,1000);
    }
     public void abortButtonPressed()
     {
         mapController.removeAttackView();
     }
}
