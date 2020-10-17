package program.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.controller.ClientController;
import program.controller.LobbyItem;
import program.facade.ModelAttackViewFacade;
import program.model.Attack;
import program.model.ModelDataHandler;
import program.model.Space;


import java.io.IOException;
import java.util.*;

public class AttackView extends AnchorPane {

    private ModelAttackViewFacade model = new ModelAttackViewFacade();

    private List<ImageView> images;


    public AttackView(ArrayList<ImageView> images, ArrayList<ImageView> logoImages) {
        this.images = images;

        setLogoImages(model.getTeamLogo(model.getSelectedSpaceId(1)), model.getTeamLogo(model.getSelectedSpaceId(2)), logoImages);

    }

    public void updateDice(ClientController clientController) throws IOException {

        List<Integer> whiteDices = model.getAttackerDiceResults();
        List<Integer> blackDices = model.getDefenderDiceResults();
        updateDie(whiteDices, blackDices);
        if (clientController != null) {
            clientController.sendObject(new Attack(ModelDataHandler.getModelDataHandler().round.getAttack()));
            clientController.sendObject(new Space(ModelDataHandler.getModelDataHandler().getSelectedSpace()));
            clientController.sendObject(new Space(ModelDataHandler.getModelDataHandler().getSelectedSpace2()));
        }
    }

    public void updateDice(Attack attack) {
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

    public void updateText(Text attackerText, Text defenderText, Text attackerUnits, Text defenderUnits, Button attackButton, Button abortButton, Attack attack) {
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                attackButton.setVisible(false);
                abortButton.setLayoutX(attackButton.getLayoutX());
                abortButton.setLayoutY(attackButton.getLayoutY());
                abortButton.setText("Done");
            }
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

    private void spinDice(ImageView imageView) {
        int timer = 0;
        while (true) {
            updateDie(imageView, new Random().nextInt(7), "White");
            timer++;
            if (timer > 2000) {
                timer = 0;
                break;
            }
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
}
