package Program.Model;

import javafx.scene.paint.Color;

import java.util.List;

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
        for(int i = 0; i < spaces.size(); i++)
        {
            if(spaces.get(i).getId() == id)
            {
                return spaces.get(i);
            }
        }
        return null;
    }
}
