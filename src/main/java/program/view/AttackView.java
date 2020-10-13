package program.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.controller.AttackController;
import program.facade.ModelAttackViewFacade;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class AttackView extends AnchorPane {

    private ModelAttackViewFacade model = new ModelAttackViewFacade();

    private List<ImageView> images;
    private List<ImageView> logoImages;
    private List<ImageView> fakeImages;
    private Timer timer = new Timer();


    public AttackView(ArrayList<ImageView> images, ArrayList<ImageView> logoImages) {
        this.images = images;
        fakeImages = new ArrayList<>(images);

        this.logoImages = logoImages;
        setLogoImages(model.getTeamLogo(model.getSelectedSpaceId(1)),model.getTeamLogo(model.getSelectedSpaceId(2)), logoImages);

    }

    public void updateAttackView() {
        List<Integer> dices = model.getDiceResults();
        resetImages(images);
        List<Integer> whiteDices = dices.subList(0, dices.size() - 2);
        List<Integer> blackDices = dices.subList(dices.size() - 2, dices.size());
        sortList(whiteDices);
        sortList(blackDices);
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
        updateText(defenderText,model.getSelectedSpaceName(2) + attackResults.get(1));
        updateText(attackerUnits,  model.getSelectedSpaceName(1) +" units: " + model.getSelectedSpaceUnits(1));
        updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: 0");
        if(model.checkAttack()) {
            attackDone(attackButton, abortButton);
            updateText(attackerUnits, model.getSelectedSpaceName(1) +" units: " + model.getSelectedSpaceUnits(2));
        }
        else {
            updateText(defenderUnits, model.getSelectedSpaceName(2) + " units: " + model.getSelectedSpaceUnits(2));
        }
    }

    public void attackDone(Button attackButton, Button abortButton) {
        attackButton.setVisible(false);
        abortButton.setLayoutX(attackButton.getLayoutX());
        abortButton.setLayoutY(attackButton.getLayoutY());
        abortButton.setText("Done");
    }

    private void updateDie(ImageView imageView, int number, String dicecolor) {
        Image image;
        switch (dicecolor) {
            case "White":
                image = new Image("file:src/main/resources/pictures/dice_white_"+number+".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
            case "Black":
                image = new Image("file:src/main/resources/pictures/dice_black_"+number+".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
        }
    }

    private void spinDices() {
        int timer = 0;
        while(true)
        {
            if(fakeImages.size() != 0)
            {
                for (ImageView fakeImage : fakeImages) {
                    updateDie(fakeImage, new Random().nextInt(7), "White");
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

    private void resetImages(List<ImageView> images)
    {
        for(ImageView image: images)
        {
            image.setVisible(false);
        }
    }

    private void setLogoImages(Image attackerImage, Image defenderImage, List<ImageView> images)
    {
        images.get(0).setImage(attackerImage);
        images.get(1).setImage(defenderImage);
    }

    private void updateText(Text text, String replacestring)
    {
        text.setText(replacestring);
    }
}
