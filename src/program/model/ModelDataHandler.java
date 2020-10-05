package program.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class
 */

public class ModelDataHandler {

    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int roundCount = 1;
    private Round round;
    private Board board;
    private int unitsToUse = 1;

    /**
     *
     * @param amountOfSpaces This is all the spaces on the board
     * @param colors This is a list of all the colors for the players that has been chosen
     */
    public ModelDataHandler(int amountOfSpaces, List<Color> colors)
    {
        for(int i = 0; i < colors.size(); i++)
        {
            players.add(new Player((100/colors.size()),i,colors.get(i)));
        }
        currentPlayer = getRandomPlayer(null);
        board = new Board(randomizeSpaces(amountOfSpaces));
        round = new Round();
    }

    /**
     * Get player color based on player id.
     * @param id id of the player.
     * @return the color that correspond the player.
     */
    public Color findPlayerColor(int id)
    {
        return players.get(id).getColor();
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
        if(board.findSpace(id).getPlayer() == currentPlayer && (board.selectedSpace == null || round.getCurrentPhase().equals(Round.Phase.DEPLOY))) {
            board.selectedSpace = board.findSpace(id);
            return true;
        }
        else if((board.selectedSpace !=  null && round.getCurrentPhase().equals(Round.Phase.MOVE) &&
                board.findSpace(id).getPlayer() == currentPlayer) || board.selectedSpace != null &&
                board.findSpace(id).getPlayer() != currentPlayer && round.getCurrentPhase().equals(Round.Phase.ATTACK)){
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
        for(int i = 0; i < players.size(); i++)
        {
            if(player == players.get(i) && i + 1 <  players.size())
            {
                currentPlayer = players.get(i+1);
                break;
            }
            else if(player == players.get(i)){
                currentPlayer = players.get(0);
                break;
            }
        }
    }


    private List<Space> randomizeSpaces(int amountOfSpaces)
    {
        int player = 0;
        Player lastrandomplayer = players.get(0);
        List<Space> spaces = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        for(int i = 0; i < amountOfSpaces; i++)
        {
            player++;
            if(player > players.size() - 1){
                player = 0;
                playerList.clear();
            }
            lastrandomplayer = getRandomPlayer(playerList);
            spaces.add(new Space(i,lastrandomplayer,10,i + ""));
            playerList.add(lastrandomplayer);
        }
        return spaces;
    }

    /**
     * This method is used to randomise a player from the playerList that needs to get a space,
     * this is done by excluding the players that already got a space.
     * @param lastPickedPlayers This list is used to know which players need to be excluded from the next handout of spaces
     * @return It then returns the player that's going to get a space
     */
    private Player getRandomPlayer(List<Player> lastPickedPlayers)
    {
        Random random = new Random();
        Player player;
        while(true)
        {
            player = players.get(random.nextInt(players.size()));
            if(lastPickedPlayers == null)
            {
                return player;
            }
            else if(!lastPickedPlayers.contains(player)){
                return player;
            }
        }
    }
    /**
     * Method that returns the id of a player as a String
     * @return the id of a player as a String.
     */
    public String getCurrentPlayerName()
    {
        return String.valueOf(currentPlayer.getId() + 1);
    }

    public Color getCurrentPlayerColor(){return currentPlayer.getColor();}

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
        roundCount++;
        if(roundCount > 3)
        {
            nextPlayer(currentPlayer);
            roundCount = 1;
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
            return round.startPhase(board.selectedSpace, board.selectedSpace2, currentPlayer, unitsToUse);
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
    public List<Color> getColorOnAllSpaces(){return board.getColorOnAllSpaces();}

    public void getSliderAmount(int unitsToUse){
            this.unitsToUse = unitsToUse;
    }

    public int getDeployableUnits(){
        return currentPlayer.getUnits();
    }
    public List<Integer> getDiceResults()
    {
        return round.diceresults();
    }

}
