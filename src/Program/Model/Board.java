package Program.Model;

import javafx.scene.paint.Color;

class Board {

    private Space[] spaces;
    Space selectedSpace;
    Space selectedSpace2;

    public Board(Space[] spaces)
    {
        this.spaces = spaces;
    }
    public Space findSpace(int id)
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
    void selectSpace(Space space)
    {
        if(selectedSpace != null)
        {
            selectedSpace2 = space;
        }
        else {
            selectedSpace = space;
        }
    }
}
