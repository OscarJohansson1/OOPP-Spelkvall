package program.view;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import program.controller.MapController;

import java.util.List;

/**
 * the view for karta.fxml
 */
public class MapView extends AnchorPane {

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
     * Updates the slider's text to show desired value.
     * @param units Desired number of units shown by text.
     * @param text Instance of text to be changed.
     */
    public void updateSliderText(int units, Text text){
        text.setText(units + "");
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
     * Sets a given map space to the desired color.
     * @param button Map space to be changed.
     * @param color Desired color.
     */
    public void updateDisplayCubes(Button button, Color color){
        button.setStyle(colorToHex(color));
    }

    /**
     * Removes color from a map space.
     * @param button Map space to edit.
     */
    public void resetDisplayCubes(Button button){
        button.setStyle(null);
    }

    /**
     * Sets desired text to be equal to another text.
     * @param displayText Text to be changed.
     * @param cubeText Text to be copied.
     */
    public void updateDisplayTexts(Text displayText, Text cubeText){
        displayText.setText(cubeText.getText());
    }

    /**
     * Sets given text to an empty string.
     * @param text
     */
    public void resetDisplayTexts(Text text){
        text.setText("");
    }

    /**
     * Sets given text to show desired amount of units.
     * @param text Text to be changed.
     * @param units Amount of units to display.
     */
    public void updateDeployableUnits(Text text, int units){
        text.setText("Deployable units: " + units + "");
    }

    /**
     * Method that resets the colors to standard colors.
     * @param colors An array with colors.
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