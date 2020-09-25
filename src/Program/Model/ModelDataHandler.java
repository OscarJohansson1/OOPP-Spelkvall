package Program.Model;

import javafx.scene.paint.Color;

public class ModelDataHandler {

    private Player[] players = new Player[] {
            new Player(10,1,Color.RED),
            new Player(10,2,Color.BLUE)
    };
    private Player currentplayer;

    private int roundcount = 1;

    private Round round;

    private Board board;

    public ModelDataHandler()
    {
        startGame();
    }
    private void startGame()
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

    /**
     * Get player color based on player id.
     * @param id id of the player.
     * @return the color that correspond the player.
     */
    public Color findPlayerColor(int id)
    {
        return players[id].getColor();
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

    /**
     * Method that sets selectedSpace/selectedSpace2 to a specific space based on id.
     * @param id The id of the space to add as a selectedSpace
     * @return True if a the space was added to a selectedSpace, false if unsuccessful
     */
    public boolean receiveSelectedSpace(int id)
    {
        if(board.findSpace(id).getPlayer() == currentplayer && (board.selectedSpace == null || round.getCurrentPhase().equals(Round.Phase.DEPLOY))) {
            board.selectedSpace = board.findSpace(id);
            return true;
        }
        else if((board.selectedSpace !=  null && round.getCurrentPhase().equals(Round.Phase.MOVE) &&
                board.findSpace(id).getPlayer() == currentplayer) || board.selectedSpace != null &&
                board.findSpace(id).getPlayer() != currentplayer && round.getCurrentPhase().equals(Round.Phase.ATTACK)){
            board.selectedSpace2 = board.findSpace(id);
            return true;
        }
        return false;
    }
    /**
     * Method that changes the currentPlayer to the next player in the player list. If the player is the last player in
     * the list, the first player in the list is selected as the new currentPlayer.
     * @param player The currentPlayer.
     */
    private void nextPlayer(Player player)
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
    /**
     * Method that returns the id of a player as a String
     * @return the id of a player as a String.
     */
    public String getCurrentPlayerName()
    {
        return String.valueOf(currentplayer.getId());
    }

    /**
     * Method that returns the name of the currentPhase
     * @return the name of the currentPhase as a String
     */
    public String getCurrenPhase()
    {
        return round.getCurrentPhase().name();
    }

    /**
     * Method that change the phase to the next one and reset the selectedSpaces
     */
    public void nextPhase()
    {
        round.nextPhase();
        resetSelectedSpace();
        roundcount++;
        if(roundcount > 3)
        {
            nextPlayer(currentplayer);
            roundcount = 1;
        }
    }

    /**
     * Method that checks if the selectedSpaces aren't null and starts the phase if so.
     * @return if the phase could start successfully.
     */
    public boolean nextMove()
    {
        if((board.selectedSpace != null && round.getCurrentPhase().equals(Round.Phase.DEPLOY)) || board.selectedSpace2 != null)
        {
            return round.startPhase(board.selectedSpace, board.selectedSpace2, currentplayer);
        }
        return false;
    }
    /**
     * Method that returns the units on a space based on id.
     * @param id Id of the space that the amount of units should be returned.
     * @return Number of units on a space, based on id.
     */
    public int findUnitsOnSpace(int id)
    {
        return board.findSpace(id).getUnits();
    }
    /**
     * Method that returns the color on a space based on id.
     * @param id Id of the space that the color should be returned.
     * @return Color of the space, based on id
     */
    public Color getColorOnSpace(int id)
    {
        return board.findSpace(id).getPlayer().getColor();
    }

}
