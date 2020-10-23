package program.model;

import java.io.Serializable;

/**
 * The Player class creates players and hold information about a player.
 */
public class Player implements Serializable {

    private int units;
    private int id;
    private final String color;
    private final String logoUrl;
    private final String name;
    private boolean myTurn = false;
    private boolean ready = false;

    /**
     * A constructor for a player.
     *
     * @param units     The amount of units that a player has.
     * @param id        The players id.
     * @param color     The players color.
     * @param logoUrl   The name of the picture of the players logotype, without file-path or .png.
     * @param name      The name of the player.
     */
    public Player(int units, int id, String color, String logoUrl, String name) {
        this.units = units;
        this.id = id;
        this.color = color;
        this.logoUrl = "file:src/main/resources/pictures/logos/" + logoUrl + ".png";
        this.name = name;
    }

    /**
     * A constructor for copying a player.
     *
     * @param player The player that should be copied.
     */
    public Player(Player player) {
        units = player.units;
        id = player.id;
        color = player.color;
        logoUrl = player.logoUrl;
        name = player.name;
        myTurn = player.myTurn;
        ready = player.ready;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getUnits() {
        return units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public boolean getMyTurn() {
        return myTurn;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }
}