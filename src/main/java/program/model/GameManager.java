package program.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class controls the initialisation of the game and general methods for the flow of the game. GameManager also
 * holds most of the general methods that later is used by other packages.
 */
public class GameManager implements IObservable {

    private final List<IObserver> observers = new ArrayList<>();
    private List<Player> players;
    public final Round round = new Round();
    private Player currentPlayer;
    private BoardManager board;
    private AttackPhase attack;
    private final int totalStartingUnits = 50;
    private int roundCount = 1;
    private int phaseCount = 1;
    private int unitsToUse = 1;
    public boolean firstDeployment = true;

    public List<String> spaceNames = new ArrayList<>(Arrays.asList("Hubben", "Basen", "KajsaBaren", "Zaloonen", "Winden", "LofTDet",
            "RödaRummet", "Verum", "Villan", "A-dammen", "Focus", "FortNox", "GTSpritis", "GoldenI", "Chabo", "Wijkanders", "Hyddan",
            "Alvan", "Spektrum", "Gasquen", "Chalmersplatsen", "Olgas", "RunAn", "Tagvagnen", "Origogården", "KalleGlader", "Tvargatan"));

    /**
     * Overrides the default constructor to prevent other classes from creating a new GameManager.
     */
    private GameManager() {
    }

    /**
     * Private class that holds a single GameManager and is used to implement the Singleton Pattern.
     */
    private static class GameManagerHolder {
        private static final GameManager gameManager = new GameManager();
    }

    /**
     * Method that returns the single GameManager that GameManagerHolder holds.
     *
     * @return The GameManager.
     */
    public static GameManager getGameManager() {
        return GameManagerHolder.gameManager;
    }

    /**
     * Method that initialize the game board and add players to a list of players. The phases are set up and players are
     * given spaces that they hold in the beginning of the game.
     *
     * @param amountOfSpaces The amount of spaces on the game board
     * @param colors         A list of colors that should represent each player.
     * @param logoNames      A list of Strings with the logotypes that should represent each player.
     */
    public void initialize(int amountOfSpaces, List<String> colors, List<String> logoNames) {
        setUpPhase();
        players = new ArrayList<>();
        for (int i = 0; i < colors.size(); i++) {
            players.add(new Player(totalStartingUnits / colors.size(), i, colors.get(i), logoNames.get(i), i + 1 + ""));
        }
        currentPlayer = getRandomPlayer(null, players);
        board = new BoardManager(new ChalmersBoard(randomizeSpaces(amountOfSpaces, players)));
    }

    /**
     * Method that initialize the phases, used by the online part of the game.
     */
    public void initialize() {
        setUpPhase();
    }

    private void setUpPhase() {
        DeployPhase deploy = new DeployPhase();
        attack = new AttackPhase();
        MovePhase move = new MovePhase();
        round.setCurrentPhase(deploy);
        deploy.setNextPhase(attack);
        attack.setNextPhase(move);
        move.setNextPhase(deploy);
    }

    /**
     * Method that randomize the spaces between the players.
     *
     * @param amountOfSpaces The amount of spaces on the board.
     * @param players        A list of players.
     * @return A list with spaces that are controlled by players in the player list.
     */
    public List<Space> randomizeSpaces(int amountOfSpaces, List<Player> players) {
        List<Space> spaces = new ArrayList<>();
        int player = 0;
        Player lastRandomPlayer;
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < amountOfSpaces; i++) {
            lastRandomPlayer = getRandomPlayer(playerList, players);
            spaces.add(new Space(i, lastRandomPlayer, 1, spaceNames.get(i)));
            playerList.add(lastRandomPlayer);
            player++;
            if (player > players.size() - 1) {
                player = 0;
                playerList.clear();
            }
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
     * Method that sets a specific space, based on id, to either the first or second selected space based on if they're
     * already selected spaces or not.
     *
     * @param id The id of the space to add as a selected space.
     * @return True if a the space was added as a selected space, false if unsuccessful.
     */
    public boolean setSelectedSpace(int id) throws IOException {
        if (board.findSpace(id).getPlayerId() == currentPlayer.getId() && (board.getSelectedSpace() == null || round.getCurrentPhase().equals("DEPLOY"))) {
            board.setSelectedSpace(board.findSpace(id));
            return true;
        } else if (board.getSelectedSpace() == board.findSpace(id)) {
            resetSelectedSpaces();
        } else if ((board.getSelectedSpace() != null && round.getCurrentPhase().equals("MOVE") &&
                board.findSpace(id).getPlayerId() == currentPlayer.getId() && board.isNeighbours(board.findSpace(id))) || (board.getSelectedSpace() != null &&
                board.findSpace(id).getPlayerId() != currentPlayer.getId() && round.getCurrentPhase().equals("ATTACK") && board.isNeighbours(board.findSpace(id)))) {
            board.setSelectedSpace2(board.findSpace(id));
            return true;
        }
        return false;
    }

    /**
     * Method that sets a specific space to either the first or second selected space based on if they're already
     * selected spaces or not. Used for the online part of the game.
     *
     * @param space The space to add as a selected space.
     */
    public void receiveOnlineSelectedSpace(Space space) {
        if (board.getSelectedSpace() != null && !round.getCurrentPhase().equals("DEPLOY") && board.getSelectedSpace().getId() != space.getId()) {
            board.setSelectedSpace2(board.findSpace(space.getId()));
        } else {
            board.setSelectedSpace(board.findSpace(space.getId()));
        }
        board.getSpaces().get(space.getId()).updateSpace(space);

    }

    /**
     * Method that resets the current selected spaces and notifies the observers.
     */
    public void resetSelectedSpaces() throws IOException {
        notifyObservers("resetSelectedSpaces");
        resetSpaces();
    }

    /**
     * Method that resets the current selected spaces.
     */
    public void resetSpaces() {
        board.resetSpaces();
    }

    /**
     * Method that change the phase to the next one and reset the selected spaces. If the round is over the method
     * change the current player to the next player and change the phase to the first phase.
     */
    public void nextPhase() throws IOException {
        if (!observers.isEmpty()) {
            notifyObservers("nextPhase");
        } else {
            round.nextPhase();
        }
        resetSelectedSpaces();
        phaseCount++;
        if (phaseCount > 3) {
            currentPlayer = nextPlayer(players, currentPlayer);
            notifyObservers(new Player(currentPlayer));
            phaseCount = 1;
        }
    }

    /**
     * Method that controls the phase changes during the first round-robin. During the first round the players takes
     * turn deploying units, and does not attack and move.
     */
    public void firstRoundNextPhase() throws IOException {
        resetSelectedSpaces();
        notifyObservers(new Player(currentPlayer));
        currentPlayer = nextPlayer(players, currentPlayer);
        roundCount++;
        if (roundCount > players.size()) {
            firstDeployment = false;
        }
        notifyObservers(roundCount);
        notifyObservers(new Player(currentPlayer));
    }

    /**
     * Method that change the current player to the next player that should play.
     *
     * @param players       A list of the players in the game.
     * @param currentPlayer The current player.
     * @return The new current player.
     */
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
     * Method that checks if the selected spaces aren't null and initiates the phase if that is the case.
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
     * Method that returns the amount of extra units that a player gets to deploy.
     *
     * @return The extra amount of units.
     */
    public int calculateDeployableUnits(Player player) {
        return board.getUnitsForSpacesHold(player) + board.getUnitsFromAreas(player);
    }

    /**
     * Method that checks if any player has won the game.
     *
     * @return If any player has won.
     */
    public boolean isWinner() {
        return board.isWinner();
    }

    /**
     * Method that removes any player from the player list that doesn't have any spaces left
     */
    public void removePlayersWithoutSpaces() {
        players.removeIf(player -> board.isPlayerOut(player));
    }

    /**
     * Method that checks if the attack is done by checking if a next attack is possible or not.
     *
     * @return Returns true if a next attack isn't possible.
     */
    boolean isAttackDone() {
        return !attack.nextAttackPossible;
    }

    /**
     * Method that gives the players the correct amount of units at the start of the game.
     *
     * @param players A list of players in the game.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
        for (Player player : players) {
            player.setUnits(totalStartingUnits / players.size());
        }
    }


    /**
     * Method that sets the amount of units the current player can deploy.
     *
     * @param units The amount of deployable units.
     */
    public void setDeployableUnits(int units) {
        currentPlayer.setUnits(units);
    }

    public void setSliderAmount(int unitsToUse) {
        this.unitsToUse = unitsToUse;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
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
        return currentPlayer.getName();
    }

    public String getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    public String getCurrentPhase() {
        return round.getCurrentPhase();
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setAttack(AttackPhase attack) {
        this.attack = attack;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    public List<String> getColorOnAllSpaces() {
        return board.getColorOnAllSpaces();
    }

    public int getDeployableUnits() {
        return currentPlayer.getUnits();
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

    List<Integer> getAttackerDiceResults() {
        return attack.attackerDiceResults();
    }

    List<Integer> getDefenderDiceResults() {
        return attack.defenderDiceResults();
    }

    List<String> attackResult() {
        return attack.attackResults();
    }

    public AttackPhase getAttack() {
        return attack;
    }

    public void setBoard(BoardManager board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Object object) throws IOException {
        for (IObserver observer : observers) {
            observer.sendObject(object);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }
}