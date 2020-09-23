package Program.Model;

import javafx.scene.paint.Color;

public class ModelDataHandler {

    private Player[] players = new Player[] {
            new Player(10,1,Color.RED),
            new Player(10,2,Color.BLUE)
    };
    private Player currentplayer;

    private Round round;

    private Board board;

    public ModelDataHandler()
    {
        startGame();
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
        round = new Round();
    }
    public Color findPlayerColor(int id)
    {
        return players[id].getColor();
    }
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
    public Space getSelectedSpace()
    {
        return  board.selectedSpace;
    }
    public Space getSelectedSpace2()
    {
        return  board.selectedSpace2;
    }
    public void resetSelectedSpace()
    {
        board.selectedSpace = null;
        board.selectedSpace2 = null;
    }
    public void recieveSelectedSpace(int id)
    {
        if(board.findSpace(id).getPlayer() == currentplayer && board.selectedSpace == null)
        board.selectedSpace = board.findSpace(id);
        else if(board.findSpace(id).getPlayer() != currentplayer){
            board.selectedSpace2 = board.findSpace(id);
        }
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

    public String getCurrentPlayerName()
    {
        return String.valueOf(currentplayer.getId());
    }
    public String getCurrenPhase()
    {
        return round.getCurrentPhase().name();
    }
    public void nextPhase()
    {
        round.nextPhase();
        resetSelectedSpace();
    }
    public boolean nextMove()
    {
        if(board.selectedSpace != null && board.selectedSpace2 != null)
        {
            if(round.startPhase(board.selectedSpace,board.selectedSpace2, currentplayer)){
                return true;
            }
        }
        return false;
    }

    public int findUnitsOnSpace(int id)
    {
        return board.findSpace(id).getUnits();
    }
    public Color getColorOnSpace(int id)
    {
        return board.findSpace(id).getPlayer().getColor();
    }

}
