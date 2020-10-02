package program.model;

import javafx.scene.paint.Color;

/**
 * This class creates a player
 */

public class Player {

    private int units;
    private int id;
    private Color color;

    /**
     * This is a constructor for the player.
     * @param units This is the amount of units that a player has.
     * @param id This is the players id, it is used to find the different player
     * @param color This is used to set different colors for all players.
     */
    Player(int units, int id, Color color) {
        this.units = units;
        this.id = id;
        this.color = color;
    }

    public int getUnits() {
        return units;
    }

    void setUnits(int units) {
        this.units = units;
    }

    int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }


}