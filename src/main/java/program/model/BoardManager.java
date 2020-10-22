package program.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the board and is keeping track of the board.
 */
public class BoardManager implements Serializable {

    private final IBoard board;
    private Space selectedSpace;
    private Space selectedSpace2;

    /**
     * The constructor.
     *
     * @param board Takes in a board
     */
    public BoardManager(IBoard board) {
        this.board = board;
    }

    /**
     * Method that checks what areas a specific player is controlling and returns the amount of units those areas are
     * worth.
     *
     * @param player The player that areas should be checked for.
     * @return The amount of extra units a player should get when deploying, based on areas controlled.
     */
    int getUnitsFromAreas(Player player) {
        int count = 0;
        for (Area area : board.getAreas()) {
            if (area.checkArea(player)) {
                count += area.getWorth();
            }
        }
        return count;
    }

    /**
     * Method that checks what spaces a specific player holds and returns the amount of units they should get for
     * those spaces.
     *
     * @param player The player that spaces should be checked for.
     * @return The amount of extra units a player should get when deploying, based on spaces hold.
     */
    int getUnitsForSpacesHold(Player player) {
        int count = 0;
        for (Space space : board.getSpaces()) {
            if (space.getPlayerId() == player.getId()) {
                count++;
            }
        }
        return 1 + (count / 2);
    }

    /**
     * Method that checks if a player has won the game
     *
     * @return If a player has won the game.
     */
    boolean isWinner() {
        Player winner = board.getSpaces().get(0).getPlayer();
        for (Space space : board.getSpaces()) {
            if (!(space.getPlayerId() == winner.getId())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if a player is out of the game. A player is out when they no longer hold any spaces.
     *
     * @param player The player that might be out of the game.
     * @return If the player have lost the game or not.
     */
    boolean isPlayerOut(Player player) {
        for (Space space : board.getSpaces()) {
            if (space.getPlayerId() == player.getId()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the a space is a neighbour to the current selected space.
     *
     * @param space A space that is not selectedSpace.
     * @return If the space is a neighbour.
     */
    boolean isNeighbours(Space space) {
        if (selectedSpace != null) {
            return board.isNeighbours(selectedSpace.getId(), space.getId());
        }
        return false;
    }

    /**
     * Method that finds a space on the board based on id.
     *
     * @param id Id-number of the space
     * @return The space if id exists, else null
     */
    Space findSpace(int id) {
        for (Space space : board.getSpaces()) {
            if (space.getId() == id) {
                return space;
            }
        }
        return null;
    }

    /**
     * Method that sets the selected spaces to null.
     */
    void resetSpaces() {
        selectedSpace = null;
        selectedSpace2 = null;
    }

    /**
     * Method that initialises all the colors on the spaces that the board starts with
     *
     * @return It then returns the hex-codes of the colors
     */
    List<String> getColorOnAllSpaces() {
        List<String> colors = new ArrayList<>();
        for (Space space : board.getSpaces()) {
            colors.add(space.getPlayer().getColor());
        }
        return colors;
    }

    Space getSpace(int id) {
        return board.getSpaces().get(id);
    }

    void setSelectedSpace(Space space) {
        selectedSpace = space;
    }

    void setSelectedSpace2(Space space) {
        selectedSpace2 = space;
    }

    Space getSelectedSpace() {
        return selectedSpace;
    }

    Space getSelectedSpace2() {
        return selectedSpace2;
    }

    public List<Space> getSpaces() {
        return board.getSpaces();
    }
}
