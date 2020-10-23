package program.view;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import program.controller.MapController;

import java.util.List;

/**
 * View for MapController.
 */
public class MapView extends AnchorPane {

    /**
     * Method that updates the player and phase texts.
     *
     * @param currentPhase  The current phase.
     * @param mapController The mapController used in the project.
     */
    public void updatePhaseText(String currentPhase, MapController mapController) {
        Platform.runLater(() -> mapController.phaseText.setText(currentPhase));
    }

    /**
     * Method that updates the visual representation of the current player.
     *
     * @param currentPlayerColor color of current player
     * @param playerName         the name of the current player
     */
    public void updateCurrentPlayer(String currentPlayerColor, MapController mapController, String playerName) {
        Platform.runLater(() -> {
            mapController.showCurrentPlayer.setStyle("-fx-background-color: " + currentPlayerColor + ";" +
                    "-fx-text-fill: " + getContrastColor(Color.valueOf(currentPlayerColor)) + ";" +
                    "-fx-border-color: " + getContrastColor(Color.valueOf(currentPlayerColor)) + ";" +
                    "-fx-border-width: 3 " + ";");
            mapController.showCurrentPlayer.setText(playerName);
            mapController.imageTeamLogo.setImage(new Image(mapController.getTeamLogo()));

        });
    }

    /**
     * Updates the slider's text to show desired value.
     *
     * @param units Desired number of units shown by text.
     * @param text  Instance of text to be changed.
     */
    public void updateSliderText(int units, Text text) {
        Platform.runLater(() -> text.setText(units + ""));
    }

    /**
     * Method that update the amount of units-text based on id of space.
     *
     * @param id    Id of space that the amount of units should be updated on.
     * @param units The new amount of units.
     */
    public void updateTextUnits(int id, int units, List<Button> allButtons, MapController mapController) {
        Platform.runLater(() -> {
            for (Button allButton : allButtons) {
                if (allButton == allButtons.get(id)) {
                    allButton.setText(units + "");
                    allButton.setTextFill(Color.valueOf(getContrastColor(Color.web(mapController.getSpaceColor((id))))));
                    break;
                }
            }
        });
    }

    private static String getContrastColor(Color color) {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 0.5 ? "#000000" : "#ffffff";
    }

    /**
     * Method that sets the color an the spaces.
     *
     * @param button     The cube representing a space.
     * @param color      The color of the space.
     * @param allButtons A list of all the buttons
     */
    public void setColor(Button button, Color color, List<Button> allButtons) {
        Platform.runLater(() -> {
            for (Button allButton : allButtons) {
                if (button == allButton) {
                    allButton.setStyle(colorToHex(color));
                }
            }
        });
    }

    /**
     * Sets a given map space to the desired color.
     *
     * @param button Map space to be changed.
     * @param color  Desired color.
     */
    public void updateDisplayCubes(Button button, String color) {
        Platform.runLater(() -> button.setStyle("-fx-background-color: " + color + ";"));
    }

    /**
     * Removes color from a map space.
     *
     * @param button Map space to edit.
     */
    public void resetDisplayCubes(Button button, Text text) {
        Platform.runLater(() -> {
            button.setStyle(null);
            text.setText("");
        });
    }

    /**
     * Sets desired text to be equal to another text.
     *
     * @param displayText Text to be changed.
     * @param cubeText    Text to be copied.
     */
    public void updateDisplayTexts(Text displayText, Text cubeText) {
        Platform.runLater(() -> displayText.setText(cubeText.getText()));
    }

    /**
     * Sets given text to show desired amount of units.
     *
     * @param text  Text to be changed.
     * @param units Amount of units to display.
     */
    public void updateDeployableUnits(Text text, int units) {
        Platform.runLater(() -> text.setText("Deployable units: " + units + ""));
    }

    /**
     * Method that resets the colors to standard colors.
     *
     * @param colors An array with colors.
     */
    public void resetColor(List<Color> colors, List<Button> allButtons) {
        Platform.runLater(() -> {
            for (int i = 0; i < allButtons.size(); i++) {
                allButtons.get(i).setStyle(colorToHex(colors.get(i)));
            }
        });

    }

    private String colorToHex(Color color) {
        String hex = String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
        return "-fx-background-color: " + hex + ";";
    }

    /**
     * Method that makes the correct phase-pane visible.
     *
     * @param string        The name of the phase that should be displayed.
     * @param mapController The mapController used in the project.
     */
    public void updatePhase(String string, MapController mapController) {
        switch (string) {
            case "DEPLOY":
                Platform.runLater(() -> {
                    mapController.toggleVisibilityDeployableUnits(true);
                    mapController.deployPhase.toFront();
                    mapController.sliderPane.toFront();
                });
                break;
            case "ATTACK":
                Platform.runLater(() -> {
                    mapController.toggleVisibilityDeployableUnits(false);
                    mapController.attackPhase.toFront();
                });
                break;
            case "MOVE":
                Platform.runLater(() -> {
                    mapController.movePhase.toFront();
                    mapController.sliderPane.toFront();
                });

                break;
            case "END":
                break;
        }
        mapController.phaseText.setText(string);
    }

    /**
     * Adds visibility of all phase anchorpanes and related texts
     */
    public void myturn(MapController mapController) {
        Platform.runLater(() -> {
            mapController.deployPhase.setVisible(true);
            mapController.attackPhase.setVisible(true);
            mapController.movePhase.setVisible(true);
            mapController.moveSlider.setVisible(true);
            mapController.showMoveUnitsText.setVisible(true);
        });

    }

    /**
     * Removes visibility of all phase anchorpanes and related texts
     */
    public void otherPlayerPlaying(MapController mapController) {
        Platform.runLater(() -> {
            mapController.deployPhase.setVisible(false);
            mapController.attackPhase.setVisible(false);
            mapController.movePhase.setVisible(false);
            mapController.moveSlider.setVisible(false);
            mapController.showMoveUnitsText.setVisible(false);
        });

    }
}