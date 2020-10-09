package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.model.ModelDataHandler;
import program.view.AttackView;


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

    @FXML private Text attackerText;
    @FXML private Text defenderText;



    private List<ImageView> images;
    private List<ImageView> fakeImages;
    private Timer timer = new Timer();
    private List<Integer> dices;
    private AttackView attackView = new AttackView();
    private MapController mapController;
    private ModelDataHandler modelDataHandler;

    AttackController(MapController mapController, List<Integer> dices) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attackMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


        this.dices = dices;

        images = new ArrayList<>(Arrays.asList(attackerDieImage1,attackerDieImage2,attackerDieImage3, defenderDieImage1,defenderDieImage2));
        fakeImages = new ArrayList<>(images);
        this.mapController = mapController;
        this.modelDataHandler = ModelDataHandler.getModelDataHandler();
        attack();
    }
     public void attackButtonPressed() {
        if(!modelDataHandler.nextMove()){
            attackDone();
            return;
        }
        dices = modelDataHandler.getDiceResults();
        attack();
    }
    private void attack() {
        attackView.resetImages(images);
        List<Integer> whiteDices = dices.subList(0,dices.size()-2);
        List<Integer> blackDices = dices.subList(dices.size()-2,dices.size());
        sortList(whiteDices);
        sortList(blackDices);
        for(int i = 0; i < whiteDices.size(); i++)
        {
            attackView.updateDie(images.get(i),whiteDices.get(i),"White");
        }
        for(int i = 0; i < blackDices.size(); i++)
        {
            attackView.updateDie(images.get(i+3),blackDices.get(i),"Black");
        }
        List<String> attackResults = modelDataHandler.attackResult();
        attackView.updateText(attackerText,attackResults.get(0));
        attackView.updateText(defenderText,attackResults.get(1));
        if(modelDataHandler.checkAttack()) {
            attackDone();
        }
    }
    private void attackDone() {
        attackButton.setVisible(false);
        abortButton.setLayoutX(attackButton.getLayoutX());
        abortButton.setLayoutY(attackButton.getLayoutY());
        abortButton.setText("Done");
    }
    private void sortList(List<Integer> list) {
        int temp;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size()-1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }

     private void spinDices() {
        int timer = 0;
         while(true)
         {
             if(fakeImages.size() != 0)
             {
                 for (ImageView fakeImage : fakeImages) {
                     attackView.updateDie(fakeImage, new Random().nextInt(7), "White");
                 }
             }
             else {
                 break;
             }
             timer++;
             if(timer > 2000)
             {
                 fakeImages.remove(0);
                 timer = 0;
                 break;
             }
         }
     }
     public void abortButtonPressed() {
         mapController.removeAttackView();
     }
}
