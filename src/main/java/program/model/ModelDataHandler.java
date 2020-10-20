package program.model;



import program.client.Client;

import java.io.IOException;
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
    public final Round round = new Round();
    private Board board;
    private int unitsToUse = 1;
    public boolean firstDeployment = true;
    private Client client;

    public List<String> spaceNames = new ArrayList<>(Arrays.asList("Hubben", "Basen", "KajsaBaren", "Zaloonen", "Winden", "LofTDet",
            "RödaRummet", "Verum", "Villan", "A-dammen", "Focus", "FortNox", "GTSpritis", "GoldenI", "Chabo", "Wijkanders", "Hrum",
            "Alvan", "Spektrum", "Gasquen", "Chalmersplatsen", "Olgas", "RunAn", "Tagvagnen", "Origogarden", "KalleGlader", "Tvargatan"));

    /**
     * Overrides the default constructor to prevent other classes from creating new ModelDataHandlers.
     */
    private ModelDataHandler() {
    }

    /**
     * Private class that holds a single ModelDataHandler and is later used to implement the Singleton Pattern.
     */
    private static class ModelDataHandlerHolder {
        private static final ModelDataHandler modelDataHandler = new ModelDataHandler();
    }

    /**
     * Get the one ModelDataHandler that ModelDataHandlerHolder holds.
     *
     * @return The ModelDataHandler.
     */
    public static ModelDataHandler getModelDataHandler() {
        return ModelDataHandlerHolder.modelDataHandler;
    }

    /**
     * Method that initialize the game board and add players to a list of players. Players are also given spaces that
     * they hold in the beginning of the game.
     *
     * @param amountOfSpaces The amount of spaces on the game board
     * @param colors         A list of colors that should represent each player.
     * @param logoNames      A list of Strings with the logotypes that should represent each player.
     */
    public void initialize(int amountOfSpaces, List<String> colors, List<String> logoNames) {
        for (int i = 0; i < colors.size(); i++) {
            players.add(new Player((50 / colors.size()), i, colors.get(i), logoNames.get(i), i + ""));
        }
        currentPlayer = getRandomPlayer(null, players);
        board = new Board(new ChalmersBoard(randomizeSpaces(amountOfSpaces, players)));
    }
    public void initialize(Client client){
        this.client = client;
    }

    public List<Space> randomizeSpaces(int amountOfSpaces, List<Player> players) {
        List<Space> spaces = new ArrayList<>();
        int player = 0;
        Player lastRandomPlayer;
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < amountOfSpaces; i++) {
            player++;
            if (player > players.size() - 1) {
                player = 0;
                playerList.clear();
            }
            lastRandomPlayer = getRandomPlayer(playerList, players);
            spaces.add(new Space(i, lastRandomPlayer, 1, spaceNames.get(i)));
            playerList.add(lastRandomPlayer);
        }
        return spaces;
    }

    private Player getRandomPlayer(List<Player> lastPickedPlayers, List<Player> players) {
        Random random = new Random();
        Player player;
        while (true) {
            player = players.get(random.nextInt(players.size()));
            if (lastPickedPlayers == null) {
                return player;
            } else if (!lastPickedPlayers.contains(player)) {
                return player;
            }
        }
    }

    /**
     * Method that sets selectedSpace/selectedSpace2 to a specific space based on id.
     * //TODO Add better description/make method clearer.
     *
     * @param id The id of the space to add as a selectedSpace
     * @return True if a the space was added to a selectedSpace, false if unsuccessful
     */
    public boolean receiveSelectedSpace(int id) {
        if (board.findSpace(id).getPlayerId() == currentPlayer.getId() && (board.getSelectedSpace() == null || round.getCurrentPhase().equals("DEPLOY"))) {
            board.setSelectedSpace(board.findSpace(id));
            return true;
        } else if (board.getSelectedSpace() == board.findSpace(id)) {
            board.resetSpaces();
        } else if ((board.getSelectedSpace() != null && round.getCurrentPhase().equals("MOVE") &&
                board.findSpace(id).getPlayerId() == currentPlayer.getId() && board.isNeighbours(board.findSpace(id))) || (board.getSelectedSpace() != null &&
                board.findSpace(id).getPlayerId() != currentPlayer.getId() && round.getCurrentPhase().equals("ATTACK") && board.isNeighbours(board.findSpace(id)))) {
            board.setSelectedSpace2(board.findSpace(id));
            return true;
        }
        return false;
    }

    public void receiveOnlineSelectedSpace(Space space) {
        if (board.getSelectedSpace() != null && !round.getCurrentPhase().equals("DEPLOY")) {
            board.setSelectedSpace2(board.findSpace(space.getId()));
        } else {
            board.setSelectedSpace(board.findSpace(space.getId()));
        }
        board.getSpaces().get(space.getId()).updateSpace(space);

    }

    /**
     * Method that resets the current selected spaces.
     */
    public void resetSelectedSpaces() throws IOException {
        if(client != null){
            sendObject("resetSelectedSpaces");
        }
        else {
            resetSpaces();
        }
    }
    public void resetSpaces(){
        board.resetSpaces();
    }

    /**
     * Method that change the phase to the next one and reset the selectedSpaces. If the round is over the method
     * change the current player to the next player and change the phase to the first phase.
     */
    public void nextPhase() throws IOException {

        if (client != null) {
            sendObject("nextPhase");
        } else {
            round.nextPhase();
        }
        resetSelectedSpaces();
        phaseCount++;
        if (phaseCount > 3) {
            sendObject(new Player(currentPlayer));
            currentPlayer = nextPlayer(players, currentPlayer);
            if (client != null) {
                client.getPlayer().setMyTurn(currentPlayer.getId() == client.getPlayer().getId());
                sendObject(new Player(currentPlayer));
            }
            phaseCount = 1;
        }
    }


    /**
     * Method that controls the phase changes during the first round-robin. During the first round the players takes
     * turn deploying units, and does not attack and move.
     */
    public void firstRoundNextPhase() throws IOException {

        resetSelectedSpaces();
        currentPlayer.setFirstDeployment();
        sendObject(new Player(currentPlayer));
        currentPlayer = nextPlayer(players, currentPlayer);
        if (client != null) {
            client.getPlayer().setMyTurn(currentPlayer.getId() == client.getPlayer().getId());
        }
        roundCount++;
        if (roundCount > players.size()) {
            firstDeployment = false;
            currentPlayer.setFirstDeployment();
        }
        sendObject(roundCount);
        sendObject(new Player(currentPlayer));

    }

    public static Player nextPlayer(List<Player> players, Player currentPlayer) {
        for (int i = 0; i < players.size(); i++) {
            if (currentPlayer.getId() == players.get(i).getId() && i + 1 < players.size()) {
                currentPlayer = new Player(players.get(i + 1));
                return currentPlayer;
            } else if (currentPlayer.getId() == players.get(i).getId()) {
                currentPlayer = new Player(players.get(0));
                return currentPlayer;
            }
        }
        return currentPlayer;
    }

    /**
     * Method that checks if the selectedSpaces aren't null and initiates the phase if that is the case.
     *
     * @return If the phase could start successfully.
     */
    public boolean startPhase() {
        if ((board.getSelectedSpace() != null && round.getCurrentPhase().equals("DEPLOY")) || board.getSelectedSpace2() != null) {
            return round.startPhase(board.getSelectedSpace(), board.getSelectedSpace2(), currentPlayer, unitsToUse);
        }
        return false;
    }

    /**
     * @return Should return the calculated amount. Is hard coded right now and needs to be fixed
     */
    public int calculateDeployableUnits(Player player) {
        if (player.getUnits() != 0) {
            return player.getUnits();
        }
        return board.getUnitsForSpacesHold(player) + board.getUnitsFromAreas(player);
    }

    public List<String> attackResult() {
        return round.attackResults();
    }

    /**
     * Winner 2
     */
    public boolean isWinner() {
        return board.isWinner();
    }

    //TODO Can this be optimized?
    public void removePlayersWithoutSpaces() {
        players.removeIf(player -> board.isPlayerOut(player));
    }

    /**
     * Check if the attack is done by checking if a next attack is possible or not.
     *
     * @return Returns true if a next attack isn't possible.
     */
    public boolean isAttackDone() {
        return !round.isNextAttackPossible();
    }

    public void sendObject(Object message) throws IOException {
        if (client != null) {
            client.sendObject(message);
        }
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
     *
     * @param id Id of the space that the amount of units should be returned.
     * @return Number of units on a space, based on id.
     */
    public int getUnitsOnSpace(int id) {
        return board.findSpace(id).getUnits();
    }

    /**
     * Method that returns the color on a space based on id.
     *
     * @param id Id of the space that the color should be returned.
     * @return Color of the space, based on id
     */
    public String getColorOnSpace(int id) {
        return board.findSpace(id).getPlayer().getColor();
    }

    /**
     * Method that returns the id of a player as a String
     *
     * @return the id of a player as a String.
     */
    public String getCurrentPlayerName() {
        return String.valueOf(currentPlayer.getId() + 1);
    }

    public String getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    public String getCurrentPhase() {
        return round.getCurrentPhase();
    }
    public int getRoundCount(){
        return roundCount;
    }
    public void setRoundCount(int roundCount){
        this.roundCount = roundCount;
    }

    public List<String> getColorOnAllSpaces() {
        return board.getColorOnAllSpaces();
    }

    public int getDeployableUnits() {
        return currentPlayer.getUnits();
    }

    public List<Integer> getAttackerDiceResults() {
        return round.attackerDiceResults();
    }

    public List<Integer> getDefenderDiceResults() {
        return round.defenderDiceResults();
    }

    public Space getSelectedSpace() {
        return board.getSelectedSpace();
    }

    public Space getSelectedSpace2() {
        return board.getSelectedSpace2();
    }

    public Space getSpaceFromId(int id) {
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        for (Player player : players) {
            player.setUnits(50 / players.size());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setSpace(Space receivedSpace) {
        for (Space space : board.getSpaces()) {
            if (receivedSpace.getId() == space.getId()) {
                space.updateSpace(receivedSpace);
                break;
            }
        }
    }

}

