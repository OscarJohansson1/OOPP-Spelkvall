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

    private Text[] texts;

    public void setUpMap(){
    }
    public void setUpStart(MapController mapController){

        texts = new Text[] { null,mapController.text1,mapController.text2,mapController.text3,mapController.text4};
    }
    public void updatePhasePlayerText(String playername, String currentPhase, MapController mapController)
    {
        mapController.playerText.setText("Currentplayer: " + playername + "");
        mapController.phaseText.setText(currentPhase);
    }
    public void updateTextUnits(int id, int units)
    {
        for(int i = 0; i < texts.length; i++)
        {
            if(texts[i] == texts[id])
            {
                texts[i].setText(units + "");
                texts[i].setFill(Color.WHITE);
                break;
            }
        }
    }
    public void setColor(Rectangle cube, Color color, MapController mapController)
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
    public void resetColor(Color[] colors, MapController mapController)
    {
        mapController.cube1.setFill(colors[1]);
        mapController.cube2.setFill(colors[2]);
        mapController.cube3.setFill(colors[3]);
        mapController.cube4.setFill(colors[4]);
    }
    public void updatePhase(String string, MapController mapController)
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
}
