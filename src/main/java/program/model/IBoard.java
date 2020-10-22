package program.model;

import java.util.List;

/**
 * An interface that describes what a concrete implementation of a board needs for the more general BoardManager to
 * work properly.
 */
interface IBoard {

    /**
     * Method that takes checks if two spaces are neighbours.
     *
     * @param firstIndex The id of the first space.
     * @param secondIndex The id of the second space.
     * @return If the spaces are neighbours or not.
     */
    boolean isNeighbours(int firstIndex, int secondIndex);

    /**
     * Method that returns a list of areas.
     *
     * @return List of areas.
     */
    List<Area> getAreas();

    /**
     * Method that returns a list of spaces.
     *
     * @return List of spaces.
     */
    List<Space> getSpaces();

}
