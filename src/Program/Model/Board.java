package Program.Model;

import javafx.scene.paint.Color;

class Board {

    private Space[] spaces;
    Space selectedSpace;
    Space selectedSpace2;

    Board(Space[] spaces)
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
        for(int i = 0; i < spaces.length; i++)
        {
            if(spaces[i].getId() == id)
            {
                return spaces[i];
            }

        }
        return null;
    }
}
