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

    /**
     * Method that creates a list with the texts on spaces.
     * @param mapController The mapController used in the project.
     */
    public void setUpStart(MapController mapController){

        texts = new Text[] { null,mapController.text1,mapController.text2,mapController.text3,mapController.text4};
    }

    /**
     * Method that updates the player and phase texts.
     * @param playername The name of the currentPlayer.
     * @param currentPhase The current phase.
     * @param mapController The mapController used in the project.
     */
    public void updatePhasePlayerText(String playername, String currentPhase, MapController mapController)
    {
        mapController.playerText.setText("Currentplayer: " + playername + "");
        mapController.phaseText.setText(currentPhase);
    }

    /**
     * Method that update the amount of units-text based on id of space.
     * @param id Id of space that the amount of units should be updated on.
     * @param units The new amount of units.
     */
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

    /**
     * Method that sets the color an the spaces.
     * @param cube The cube representing a space.
     * @param color The color of the space.
     * @param mapController The mapController used in the project.
     */
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

    /**
     * Method that resets the colors to a standard colors.
     * @param colors An array with colors.
     * @param mapController The mapController used in the project.
     */
    public void resetColor(Color[] colors, MapController mapController)
    {
        mapController.cube1.setFill(colors[1]);
        mapController.cube2.setFill(colors[2]);
        mapController.cube3.setFill(colors[3]);
        mapController.cube4.setFill(colors[4]);
    }

    /**
     * Method that makes the correct phase-pane visible.
     * @param string The name of the phase that should be displayed.
     * @param mapController The mapController used in the project.
     */
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
