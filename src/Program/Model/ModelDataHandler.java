package Program.Model;

import javafx.scene.paint.Color;

public class ModelDataHandler {

    private Player[] players = new Player[] {
            new Player(10,1,Color.RED),
            new Player(10,2,Color.BLUE)
    };
    private Player currentplayer;

    private Space selectedSpace;
    private Space selectedSpace2;

    private Round round;

    private Board board;

    public Color findHighLightColor(Player player)
    {
        for(int i = 0; i < players.length; i++)
        {
            if(player == players[i])
            {
                return players[i].getColor().darker();
            }
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
    }

    public void recieveSelectedSpace(int id)
    {
        if(board.findSpace(id).getPlayer() == currentplayer)
        selectedSpace = board.findSpace(id);
    }
    public void recieveSelectedSpace2(int id)
    {
        if(board.findSpace(id).getPlayer() == currentplayer)
            selectedSpace2 = board.findSpace(id);
    }

    public void nextPlayer(Player player)
    {
        for(int i = 0; i < players.length; i++)
        {
            if(player == players[i] && i + 1 <  players.length)
            {
                currentplayer = players[i+1];
            }
            else if(i + 1 < players.length){
                currentplayer = players[0];
            }
        }
    }
    public Player getCurrentPlayer()
    {
        return currentplayer;
    }

    public void nextPhase()
    {
        round.nextPhase();
        resetSelectedSpace();
        nextPlayer(currentplayer);
    }
    public void nextMove()
    {
        round.startPhase(selectedSpace,selectedSpace2);
    }

    public Space findUnitsOnSpace(int id)
    {
        return board.findSpace(id);
    }
    public Color getColorOnSpace(int id)
    {
        return board.findSpace(id).getPlayer().getColor();
    }
    public void startGame()
    {
        currentplayer = players[0];
        board = new Board(new Space[] {
                new Space(1,players[0],10,"1"),
                new Space(2,players[0],10,"2"),
                new Space(3,players[1],10,"3"),
                new Space(4,players[1],10,"4")
        });
    }
}
