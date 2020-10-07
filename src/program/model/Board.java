package program.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the board and is keeping track of the board
 */

class Board {
    private List<Space> spaces;
    Space selectedSpace;
    Space selectedSpace2;

    Board(List<Space> spaces)
    {
        this.spaces = spaces;
    }

    /**
     * Method that finds a space on the board based on id.
     * @param id id-number of the space
     * @return The space if id exists, else null
     */
    Space findSpace(int id)
    {
        for (Space space : spaces) {
            if (space.getId() == id) {
                return space;
            }
        }
        return null;
    }
    void resetSpaces()
    {
        selectedSpace = null;
        selectedSpace2 = null;
    }

    /**
     * This method initialises all the colors on the spaces that the board starts with
     * @return It then returns the colors
     */
    List<Color> getColorOnAllSpaces()
    {
        List<Color> colors = new ArrayList<>();
        for (Space space : spaces) {
            colors.add(space.getPlayer().getColor());
        }
        return colors;
    }
}
