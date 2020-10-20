package program.model;

import java.io.Serializable;

/**
 * The Player class creates players and hold information about a player.
 */

public class Player implements Serializable {

    private int units;
    private int id;
    private final String color;
    private String logoUrl;
    private String name;
    private boolean myTurn = false;
    private boolean ready = false;
    private boolean firstDeployment = true;

    /**
     * This is a constructor for the player.
     *
     * @param units This is the amount of units that a player has.
     * @param id    This is the players id, it is used to find the different player
     * @param color This is used to set different colors for all players.
     */
    public Player(int units, int id, String color, String logoUrl, String name) {
        this.units = units;
        this.id = id;
        this.color = color;
        this.logoUrl = "file:src/main/resources/pictures/logos/" + logoUrl + ".png";
        this.name = name;
    }

    public Player(Player player) {
        units = player.units;
        id = player.id;
        color = player.color;
        logoUrl = player.logoUrl;
        name = player.name;
        myTurn = player.myTurn;
        ready = player.ready;
    }

    //TODO: for tests right now
    public Player(int units, int id, String color) {
        this.units = units;
        this.id = id;
        this.color = color;
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

    public void setFirstDeployment() {
        firstDeployment = false;
    }
    public boolean getFirstDeployment(){
        return firstDeployment;
    }
}