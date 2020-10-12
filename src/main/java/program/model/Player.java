package program.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * This class creates a player
 */

class Player {
    private int units;
    private int id;
    private Color color;
    private Image logoUrl;

    /**
     * This is a constructor for the player.
     * @param units This is the amount of units that a player has.
     * @param id This is the players id, it is used to find the different player
     * @param color This is used to set different colors for all players.
     */
    Player(int units, int id, Color color, String logoUrl) {
        this.units = units;
        this.id = id;
        this.color = color;
        this.logoUrl = new Image("file:src/program/picures/logos/"+logoUrl+".png");
    }

    //TODO: for tests right now
    Player(int units, int id, Color color){
        this.units = units;
        this.id = id;
        this.color = color;
    }

    int getUnits() {
        return units;
    }

    void setUnits(int units) {
        this.units = units;
    }

    int getId() {
        return id;
    }

    Color getColor() {
        return color;
    }

    Image getLogoUrl() {
        return logoUrl;
    }
}