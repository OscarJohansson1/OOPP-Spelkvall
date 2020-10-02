package program.model;

/**
 * This class holds methods that´s controlling the spaces. Mostly creating them and updating them.
 */

public class Space {
    private final int id;
    private Player player;
    private int units;
    private final String name;

    /**
     * This is a constructor for a space
     * @param id This is the id for a space, it is used to find a space
     * @param player This is the player that owns the space
     * @param units This is the amount of units in a space
     * @param name This is the name of the space
     */
    public Space(int id, Player player, int units, String name){
        this.id = id;
        this.player = player;
        this.units = units;
        this.name = name;
    }

    /**
     * Method that updates the state of a space.
     * @param player The new player that controls the space.
     * @param units The new amount of units on the space.
     */
    void updateSpace(Player player, int units) {
        setPlayer(player);
        setUnits(units);
    }

    /**
     * Method that updates the amount of units on a space, but the player that controls the space stays the same.
     * @param units The new amount of units on the space.
     */
    void updateSpace(int units) {
        updateSpace(this.player, units);
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private void setUnits(int n) {
        units = n;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public int getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }
}
