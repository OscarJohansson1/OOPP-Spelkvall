package program.model;

import java.io.Serializable;

/**
 * This class holds methods that´s controlling the spaces. Mostly creating them and updating them.
 */
public class Space implements Serializable {

    private int id;
    private Player player;
    private int units;
    private String name;

    /**
     * A constructor for a space
     *
     * @param id     This is the id for a space, it is used to find a space
     * @param player This is the player that owns the space
     * @param units  This is the amount of units in a space
     * @param name   This is the name of the space
     */
    public Space(int id, Player player, int units, String name) {
        this.id = id;
        this.player = player;
        this.units = units;
        this.name = name;
    }

    /**
     * A constructor for copying of a space
     *
     * @param space The space that should be copied.
     */
    public Space(Space space) {
        id = space.id;
        player = space.player;
        units = space.units;
        name = space.name;
    }

    /**
     * Method that updates the state of a space.
     *
     * @param player The new player that controls the space.
     * @param units  The new amount of units on the space.
     */
    void updateSpace(Player player, int units) {
        this.player = player;
        this.units = units;
    }

    /**
     * Method that updates the amount of units on a space, but the player that controls the space stays the same.
     *
     * @param units The new amount of units on the space.
     */
    public void updateSpace(int units) {
        updateSpace(this.player, units);
    }

    /**
     * Method that updates the entire space
     *
     * @param space The space that information should be copied from.
     */
    void updateSpace(Space space) {
        id = space.id;
        player = space.player;
        units = space.units;
        name = space.name;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return new Player(player);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    int getPlayerId() {
        return player.getId();
    }

    public int getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }

}
