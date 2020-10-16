package program.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class controls everything at the moment. //TODO Make the class smaller
 */

public class ModelDataHandler {

    private List<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private int roundCount = 1;
    private int phaseCount = 1;
    private Round round;
    private Board board;
    private int unitsToUse = 1;
    public boolean firstDeployment = true;

    public List<String> spaceNames = new ArrayList<>(Arrays.asList("Hubben", "Basen", "KajsaBaren", "Zaloonen", "Winden", "LofTDet",
            "RödaRummet","Verum", "Villan", "A-dammen", "Focus", "FortNox","GTSpritis", "GoldenI", "Chabo","Wijkanders","Hrum",
            "Alvan","Spektrum","Gasquen","Chalmersplatsen","Olgas","RunAn", "Tagvagnen","Origogarden", "KalleGlader", "Tvargatan"));

    /**
     * Overrides the default constructor to prevent other classes from creating new ModelDataHandlers.
     */
    private ModelDataHandler(){}

    /**
     * Private class that holds a single ModelDataHandler and is later used to implement the Singleton Pattern.
     */
    private static class ModelDataHandlerHolder {
        private static ModelDataHandler modelDataHandler = new ModelDataHandler();
    }

    /**
     * Get the one ModelDataHandler that ModelDataHandlerHolder holds.
     * @return The ModelDataHandler.
     */
    public static ModelDataHandler getModelDataHandler() {
        return ModelDataHandlerHolder.modelDataHandler;
    }

    /**
     * Method that initialize the game board and add players to a list of players. Players are also given spaces that
     * they hold in the beginning of the game.
     * @param amountOfSpaces The amount of spaces on the game board
     * @param colors A list of colors that should represent each player.
     * @param logoNames A list of Strings with the logotypes that should represent each player.
     */
    public void initialize(int amountOfSpaces, List<Color> colors, List<String> logoNames) {
        for(int i = 0; i < colors.size(); i++)
        {
            players.add(new Player((50/colors.size()), i, colors.get(i), logoNames.get(i),i+""));
        }
        currentPlayer = getRandomPlayer(null);
        board = new Board(randomizeSpaces(amountOfSpaces));
        board.createAreas();
        round = new Round();
    }

     public List<Space> randomizeSpaces(int amountOfSpaces) {
        int player = 0;
        Player lastRandomPlayer;
        List<Space> spaces = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        for(int i = 0; i < amountOfSpaces; i++)
        {
            player++;
            if(player > players.size() - 1){
                player = 0;
                playerList.clear();
            }
            lastRandomPlayer = getRandomPlayer(playerList);
            spaces.add(new Space(i,lastRandomPlayer,1,spaceNames.get(i)));
            playerList.add(lastRandomPlayer);
        }
        return spaces;
    }

    public Player getRandomPlayer(List<Player> lastPickedPlayers) {
        Random random = new Random();
        Player player;
        while(true)
        {
            player = players.get(random.nextInt(players.size()));
            if(lastPickedPlayers == null) {
                return player;
            } else if(!lastPickedPlayers.contains(player)){
                return player;
            }
        }
    }

    /**
     * Method that sets selectedSpace/selectedSpace2 to a specific space based on id.
     * //TODO Add better description/make method clearer.
     * @param id The id of the space to add as a selectedSpace
     * @return True if a the space was added to a selectedSpace, false if unsuccessful
     */
    public boolean receiveSelectedSpace(int id) {
        if(board.findSpace(id).getPlayer() == currentPlayer && (board.selectedSpace == null || round.getCurrentPhase().equals("DEPLOY"))) {
            board.selectedSpace = board.findSpace(id);
            return true;
        }
        else if(board.selectedSpace == board.findSpace(id)){
            board.resetSpaces();
        }
        else if((board.selectedSpace !=  null && round.getCurrentPhase().equals("MOVE") &&
                board.findSpace(id).getPlayer() == currentPlayer && board.isNeighbours(board.findSpace(id))) || (board.selectedSpace != null &&
                board.findSpace(id).getPlayer() != currentPlayer && round.getCurrentPhase().equals("ATTACK") && board.isNeighbours(board.findSpace(id)))){
            board.selectedSpace2 = board.findSpace(id);
            return true;
        }
        return false;
    }

    /**
     * Method that resets the current selected spaces.
     */
    public void resetSelectedSpaces() {
        board.resetSpaces();
    }

    /**
     * Method that change the phase to the next one and reset the selectedSpaces. If the round is over the method
     * change the current player to the next player and change the phase to the first phase.
     */
    public void nextPhase() {
        round.nextPhase();
        resetSelectedSpaces();
        phaseCount++;
        if(phaseCount > 3) {
            nextPlayer();
            phaseCount = 1;
        }
    }

    /**
     * Method that controls the phase changes during the first round-robin. During the first round the players takes
     * turn deploying units, and does not attack and move.
     */
    public void firstRoundNextPhase() {
        resetSelectedSpaces();
        nextPlayer();
        roundCount++;
        if(roundCount > players.size()) {
            firstDeployment = false;
        }
    }

    private void nextPlayer() {
        for(int i = 0; i < players.size(); i++)
        {
            if(currentPlayer == players.get(i) && i + 1 <  players.size()) {
                currentPlayer = players.get(i+1);
                break;
            } else if(currentPlayer == players.get(i)) {
                currentPlayer = players.get(0);
                break;
            }
        }
    }

    /**
     * Method that checks if the selectedSpaces aren't null and initiates the phase if that is the case.
     * @return If the phase could start successfully.
     */
    public boolean startPhase() {
        if((board.selectedSpace != null && round.getCurrentPhase().equals("DEPLOY")) || board.selectedSpace2 != null) {
            return round.startPhase(board.selectedSpace, board.selectedSpace2, currentPlayer, unitsToUse);
        }
        return false;
    }

    /**
     *
     * @return Should return the calculated amount. Is hard coded right now and needs to be fixed
     */
    public int calculateDeployableUnits(){
        if(currentPlayer.getUnits() != 0){
            return currentPlayer.getUnits();
        }
        return board.getUnitsForSpacesHold(currentPlayer) + board.getUnitsFromAreas(currentPlayer);
    }

    public List<String> attackResult()
    {
        return round.attackResults();
    }

    /**
     * Winner 2
     * @return
     */
    public boolean isWinner() {
        return board.isWinner();
    }

    public boolean isAttackedPlayerOut(){
        return board.isPlayerOut(getSelectedSpace2().getPlayer());
    }

    //TODO Can this be optimized?
    public void removePlayersWithoutSpaces(){
        List<Player> tempPlayers = players;
        for(Player player : tempPlayers){
            if(board.isPlayerOut(player)){
                players.remove(player);
            }
        }
    }
    /**
     * Check if the attack is done by checking if a next attack is possible or not.
     * @return Returns true if a next attack isn't possible.
     */
    public boolean isAttackDone() {
        return !round.isNextAttackPossible();
    }

    public void setSliderAmount(int unitsToUse) {
        this.unitsToUse = unitsToUse;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setDeployableUnits(int units) {
        currentPlayer.setUnits(units);
    }

    /**
     * Method that returns the amount of units on a space based on id.
     * @param id Id of the space that the amount of units should be returned.
     * @return Number of units on a space, based on id.
     */
    public int getUnitsOnSpace(int id) {
        return board.findSpace(id).getUnits();
    }

    /**
     * Method that returns the color on a space based on id.
     * @param id Id of the space that the color should be returned.
     * @return Color of the space, based on id
     */
    public Color getColorOnSpace(int id) {
        return board.findSpace(id).getPlayer().getColor();
    }

    /**
     * Method that returns the id of a player as a String
     * @return the id of a player as a String.
     */
    public String getCurrentPlayerName() {
        return String.valueOf(currentPlayer.getId() + 1);
    }

    public Color getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    public String getCurrentPhase() {
        return round.getCurrentPhase();
    }

    public List<Color> getColorOnAllSpaces() {
        return board.getColorOnAllSpaces();
    }

    public int getDeployableUnits() {
        return currentPlayer.getUnits();
    }

    public List<Integer> getDiceResults() {
        return round.diceresults();
    }

    public Space getSelectedSpace() {
        return  board.selectedSpace;
    }

    public Space getSelectedSpace2() {
        return  board.selectedSpace2;
    }

    public Space getSpaceFromId(int id){
        return board.getSpace(id);
    }

    public String getTeamLogo(int id) {
        return board.findSpace(id).getPlayer().getLogoUrl();
    }

    public String getTeamLogo() {
        return currentPlayer.getLogoUrl();
    }

    public String getWinnerLogo() {
        return board.getSpace(0).getPlayer().getLogoUrl();
    }

    public void setBoard(Board board){
        this.board = board;
    }
    public void setPlayers(List<Player> players){
        this.players = players;
    }
}

