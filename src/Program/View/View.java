package Program.View;

import com.sun.javafx.collections.MappingChange;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import Program.Controller.MapController;

import java.io.IOException;

public class View extends AnchorPane {

    private MapController mapController;

    private Text[] texts;

    public View(MapController mapController)
    {
        this.mapController = mapController;
    }
    public void setUpMap(){


    }
    public void setUpStart(){

        texts = new Text[] { mapController.text1,mapController.text2,mapController.text3,mapController.text4};

    }
    public void updateText(String playername, String currentPhase, int id, int units)
    {
        for(int i = 0; i < texts.length; i++)
        {
            if(i == id)
            {
                texts[i].setText(units + "");
                texts[i].setFill(Color.WHITE);
                break;
            }
        }
        mapController.playerText.setText("Currentplayer: " + playername + "");
        mapController.phaseText.setText(currentPhase);
    }

    public void resetColor(Rectangle cube, Color color)
    {
        if(cube == mapController.cube1)
        {
            mapController.cube1.setFill(color);
        }
        else if(cube == mapController.cube2){
            mapController.cube2.setFill(color);
        }
        else if(cube == mapController.cube3){
            mapController.cube3.setFill(color);
        }
        else if(cube == mapController.cube4){
            mapController.cube4.setFill(color);
        }

    }
    public void updatePhase(String string)
    {
        switch (string)
        {
            case "DEPLOY":
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.deployPhase)).toFront();
                break;
            case "ATTACK":
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.attackPhase)).toFront();
                break;
            case "MOVE":
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.movePhase)).toFront();
                break;
            case "END":
                break;
        }
        mapController.phaseText.setText(string);
    }

    //Initialize i mapController, gör till en setUpMap()
    /*
    space1 = new Program.View.View.Model.Space(1, player1, 3, "1",cube1);
        cube1.setFill(player1.getColor());
    space2 = new Program.View.View.Model.Space(2, player1, 5, "2",cube2);
        cube2.setFill(player1.getColor());
    space3 = new Program.View.View.Model.Space(3, player2, 6, "3",cube3);
        cube3.setFill(player2.getColor());
    space4 = new Program.View.View.Model.Space(4, player2, 1, "4",cube4);
        cube4.setFill(player2.getColor());
    Text text = new Text();
        text.setText("1");
        text.setFill(Color.GREEN);
        text.setX(cube1.getX() + (cube1.getWidth() / 2));
        text.setY(cube1.getY() + (cube1.getHeight() / 2));
        rootpane.getChildren().add(text);

     */
}
