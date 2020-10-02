package program.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import program.controller.AttackController;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class AttackView extends AnchorPane {


    public void updateDie(ImageView imageView, int number, String dicecolor) throws FileNotFoundException {
        Image image;
        String string;

        switch (dicecolor) {
            case "White":
                image = new Image("file:C:/Users/William/Documents/GitHub/clone/OOPP-Spelkvall/src/program/picures/dice_white_"+number+".png");
                imageView.setImage(image);
                System.out.println(imageView.getImage().getUrl());
                break;
            case "Black":
                image = new Image("file:C:/Users/William/Documents/GitHub/clone/OOPP-Spelkvall/src/program/picures/dice_black_"+number+".png");
                imageView.setImage(image);
                break;
        }
    }
}
