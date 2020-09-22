import javafx.scene.paint.Color;

public class modelDataHandler {

    public Color findColor(Player player)
    {
        if(player == player1)
        {
            return Color.RED;
        }
        else if(player == player2)
        {
            return Color.BLUE;
        }
        return null;
    }
    public Color findHighLightColor(Player player)
    {
        if(player == player1)
        {
            return Color.rgb(168, 10, 10  );
        }
        else if(player == player2)
        {
            return Color.rgb(37, 54, 135 );
        }
        return null;
    }

    private void selectSpace(Space space)
    {
        if(selectedSpace != null)
        {
            selectedSpace2 = space;
        }
        else {
            selectedSpace = space;
        }
        if(selectedSpace != null)
        {
            getSelectedSpace();
        }
        if(selectedSpace2 != null)
        {
            getSelectedSpace2();
        }
    }
    public Space getSelectedSpace()
    {
        return  selectedSpace;
    }
    public Space getSelectedSpace2()
    {
        return  selectedSpace2;
    }
    public void resetSelectedSpace()
    {
        selectedSpace = null;
        selectedSpace2 = null;
        resetColor(null, null);
    }

}
