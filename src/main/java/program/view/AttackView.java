package program.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.facade.ModelAttackViewFacade;
import program.model.*;


import java.io.IOException;
import java.util.*;

public class AttackView extends AnchorPane implements IObservable {

    public final List<IObserver> observers = new ArrayList<>();

    private final ModelAttackViewFacade model = new ModelAttackViewFacade();

    private final List<ImageView> images;

   // @FXML
    //private ImageView image;

    public AttackView(ArrayList<ImageView> images, ArrayList<ImageView> logoImages) {
        this.images = images;
        setLogoImages(model.getTeamLogo(model.getSelectedSpaceId(1)), model.getTeamLogo(model.getSelectedSpaceId(2)), logoImages);
    }
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object object) throws IOException {
        for (IObserver observer : observers) {
            observer.sendObject(object);
        }
    }
    public void updateDice() throws IOException {
        List<Integer> whiteDices = model.getAttackerDiceResults();
        List<Integer> blackDices = model.getDefenderDiceResults();
        updateDie(whiteDices, blackDices);
        notifyObservers(new AttackPhase(GameManager.getModelDataHandler().round.getAttack()));
        notifyObservers(new Space(GameManager.getModelDataHandler().getSelectedSpace()));
        notifyObservers(new Space(GameManager.getModelDataHandler().getSelectedSpace2()));
    }

    public void updateDice(AttackPhase attack) {
        List<Integer> whiteDices = attack.attackerDiceResults();
        List<Integer> blackDices = attack.defenderDiceResults();
        updateDie(whiteDices, blackDices);
    }

    public void updateDie(List<Integer> whiteDices, List<Integer> blackDices) {
        resetImages(images);
        for (int i = 0; i < whiteDices.size(); i++) {
            updateDie(images.get(i), whiteDices.get(i), "White");
        }
        for (int i = 0; i < blackDices.size(); i++) {
            updateDie(images.get(i + 3), blackDices.get(i), "Black");
        }
    }

    public void updateText(Text attackerText, Text defenderText, Text attackerUnits, Text defenderUnits, Button attackButton, Button abortButton) {
        List<String> attackResults = model.getAttackResults();
        updateText(attackerText, model.getSelectedSpaceName(1) + attackResults.get(0));
        updateText(defenderText, model.getSelectedSpaceName(2) + attackResults.get(1));
        updateText(attackerUnits, model.getSelectedSpaceName(1) + " units: " + model.getSelectedSpaceUnits(1));
        updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: 0");
        if (model.isAttackDone()) {
            attackDone(attackButton, abortButton);
            updateText(attackerUnits, model.getSelectedSpaceName(1) + " units: " + model.getSelectedSpaceUnits(2));
        } else {
            updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: " + model.getSelectedSpaceUnits(2));
        }
    }

    public void updateText(Text attackerText, Text defenderText, Text attackerUnits, Text defenderUnits, Button attackButton, Button abortButton, AttackPhase attack) {
        updateText(attackerText, model.getSelectedSpaceName(1) + attack.attackResults().get(0));
        updateText(defenderText, model.getSelectedSpaceName(2) + attack.attackResults().get(1));
        updateText(attackerUnits, model.getSelectedSpaceName(1) + " units: " + model.getSelectedSpaceUnits(1));
        updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: 0");
        if (!attack.nextAttackPossible) {
            attackDone(attackButton, abortButton);
            updateText(attackerUnits, model.getSelectedSpaceName(1) + " units: " + model.getSelectedSpaceUnits(2));
        } else {
            updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: " + model.getSelectedSpaceUnits(2));
        }

    }

    public void attackDone(Button attackButton, Button abortButton) {
        Platform.runLater(() -> {
            attackButton.setVisible(false);
            abortButton.setLayoutX(attackButton.getLayoutX());
            abortButton.setLayoutY(attackButton.getLayoutY());
            abortButton.setText("Done");
        });

    }

    private void updateDie(ImageView imageView, int number, String dicecolor) {
        Image image;
        switch (dicecolor) {
            case "White":
                image = new Image("file:src/main/resources/pictures/dice_white_" + number + ".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
            case "Black":
                image = new Image("file:src/main/resources/pictures/dice_black_" + number + ".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
        }
    }

    private void resetImages(List<ImageView> images) {
        for (ImageView image : images) {
            image.setVisible(false);
        }
    }

    private void setLogoImages(Image attackerImage, Image defenderImage, List<ImageView> images) {
        images.get(0).setImage(attackerImage);
        images.get(1).setImage(defenderImage);
    }

    private void updateText(Text text, String replacestring) {
        text.setText(replacestring);
    }

    public void updatePicture(Space space, ImageView imageView){
        imageView.setImage(new Image("file:src/main/resources/pictures/places/" + space.getName() + ".jpg"));
    }
}
