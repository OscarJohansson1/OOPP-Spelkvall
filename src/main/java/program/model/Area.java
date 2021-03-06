package program.model;

import java.io.Serializable;
import java.util.List;

/**
 * This class hold information about an area.
 */
class Area implements Serializable {

    private final String name;
    private final int worth;
    private final List<Space> spaces;

    /**
     * Constructor of the class.
     *
     * @param name   The name of the area.
     * @param worth  The amount of units a player gets for holding the area.
     * @param spaces The spaces that are a part of the area.
     */
    Area(String name, int worth, List<Space> spaces) {
        this.name = name;
        this.worth = worth;
        this.spaces = spaces;
    }

    /**
     * Method that checks if a single player holds all spaces in the area.
     *
     * @param player The player that the area should be checked for.
     * @return If the player holds the entire area.
     */
    boolean checkArea(Player player) {
        for (Space space : spaces) {
            if (player.getId() != space.getPlayerId()) {
                return false;
            }
        }
        return true;
    }

    List<Space> getSpaces() {
        return spaces;
    }

    int getWorth() {
        return worth;
    }

}
