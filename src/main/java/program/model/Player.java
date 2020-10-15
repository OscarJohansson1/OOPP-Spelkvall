package program.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * The Player class creates players and hold information about a player.
 */

public class Player {

    private int units;
    private int id;
    private Color color;
    private Image logoUrl;
    private String name;

    /**
     * This is a constructor for the player.
     * @param units This is the amount of units that a player has.
     * @param id This is the players id, it is used to find the different player
     * @param color This is used to set different colors for all players.
     */
    Player(int units, int id, Color color, String logoUrl, String name) {
        this.units = units;
        this.id = id;
        this.color = color;
        //TODO Remove dependency to JavaFX (Image)
        this.logoUrl = new Image("file:src/main/resources/pictures/logos/"+logoUrl+".png");
        this.name = name;
    }

    //TODO: for tests right now
    Player(int units, int id, Color color) {
        this.units = units;
        this.id = id;
        this.color = color;
    }

    void setUnits(int units) {
        this.units = units;
    }

    int getUnits() {
        return units;
    }

    int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    Image getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }
}