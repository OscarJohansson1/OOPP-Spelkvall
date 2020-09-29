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
import java.util.List;

public class mapView extends AnchorPane {

    private Text[] texts;

    public void setUpMap(){
    }

    /**
     * Method that creates a list with the texts on spaces.
     * @param mapController The mapController used in the project.
     */
    public void setUpStart(MapController mapController){

        mapController.cubeHubben.setText("10");
        //texts = new Text[] { null,mapController.textHubben,mapController.textBasen,mapController.textKajsabaren,mapController.textZaloonen};
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
    public void updateTextUnits(int id, int units, List<Button> allButtons)
    {
        for (Button allButton : allButtons) {
            if (allButton == allButtons.get(id)) {
                allButton.setText(units + "");
                allButton.setStyle("-fx-color: #FFFFFF");
                allButton.setTextFill(Color.WHITE);
                break;
            }
        }
    }

    /**
     * Method that sets the color an the spaces.
     * @param button The cube representing a space.
     * @param color The color of the space.
     * @param allButtons A list of all the buttons
     */
    public void setColor(Button button, Color color, List<Button> allButtons)
    {
        for (Button allButton : allButtons) {
            if (button == allButton) {
                allButton.setStyle(colorToHex(color));
            }
        }
    }

    /**
     * Method that resets the colors to a standard colors.
     * @param colors An array with colors.
     * @param mapController The mapController used in the project.
     */
    public void resetColor(List<Color> colors, List<Button> allButtons)
    {
        for(int i = 0; i < allButtons.size(); i++)
        {
           allButtons.get(i).setStyle(colorToHex(colors.get(i)));
        }
    }
    private String colorToHex(Color color)
    {
        String hex = String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ));
        return "-fx-background-color: " +  hex + ";";
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
