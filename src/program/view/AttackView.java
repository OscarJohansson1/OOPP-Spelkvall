package program.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.controller.AttackController;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class AttackView extends AnchorPane {


    public void updateDie(ImageView imageView, int number, String dicecolor) {
        Image image;
        switch (dicecolor) {
            case "White":
                image = new Image("file:src/program/picures/dice_white_"+number+".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
            case "Black":
                image = new Image("file:src/program/picures/dice_black_"+number+".png");
                imageView.setImage(image);
                imageView.setVisible(true);
                break;
        }
    }
    public void resetImages(List<ImageView> images)
    {
        for(ImageView image: images)
        {
            image.setVisible(false);
        }
    }
    public void setLogoImages(Image attackerImage, Image defenderImage, List<ImageView> images)
    {
        images.get(0).setImage(attackerImage);
        images.get(1).setImage(defenderImage);
    }
    public void updateText(Text text, String replacestring)
    {
        text.setText(replacestring);
    }
}
