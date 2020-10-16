package program.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the board and is keeping track of the board.
 */
public class Board {

    private List<Space> spaces;
    private List<Area> areas;

    Space selectedSpace;
    Space selectedSpace2;

    /**
     * Indexes in the neighbours-matrix represent the following spaces:
     * 0 Hubben
     * 1 Basen
     * 2 Kajsabaren
     * 3 Zaloonen
     * 4 Winden
     * 5 LofTDet
     * 6 Röda Rummet
     * 7 Verum
     * 8 Villan
     * 9 A-dammen
     * 10 Focus
     * 11 Fort NOx
     * 12 GT Spritis
     * 13 Golden I
     * 14 Chabo
     * 15 Wijkanders
     * 16 Hyddan
     * 17 11:an
     * 18 Spektrum
     * 19 Gasquen
     * 20 Chalmersplatsen
     * 21 Olgas
     * 22 RunAn
     * 23 Tågvagnen
     * 24 Origogården
     * 25 Kalle Glader
     * 26 Tvärgatan
     */
    private int[][] neighbours = {
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
    };

    public Board(List<Space> spaces) {
        this.spaces = spaces;
    }

    /**
     * Hardcoded as of now
     */
    public void createAreas() {

        Area karhuset = new Area("Kårhuset", 7,
                Arrays.asList(spaces.get(19), spaces.get(20), spaces.get(21), spaces.get(22), spaces.get(23)));
        Area maskinhuset = new Area("Maskinhuset", 5,
                Arrays.asList(spaces.get(3), spaces.get(4), spaces.get(5), spaces.get(26)));
        Area edit = new Area("EDIT", 3,
                Arrays.asList(spaces.get(0), spaces.get(1), spaces.get(2)));
        Area vasa = new Area("Vasa", 5,
                Arrays.asList(spaces.get(13), spaces.get(14), spaces.get(15), spaces.get(25)));
        Area lindholmen = new Area("Lindholmen", 3,
                Arrays.asList(spaces.get(16), spaces.get(17), spaces.get(18)));
        Area kemigarden = new Area("Kemigården", 5,
                Arrays.asList(spaces.get(10), spaces.get(11), spaces.get(12), spaces.get(24)));
        Area sb = new Area("SB", 5,
                Arrays.asList(spaces.get(6), spaces.get(7), spaces.get(8), spaces.get(9)));

        areas = Arrays.asList(karhuset, maskinhuset, edit, vasa, lindholmen, kemigarden, sb);
    }

    /**
     * Method that checks what areas a specific player is controlling and returns the amount of units those areas are
     * worth.
     * @param player The player that areas should be checked for.
     * @return The amount of extra units a player should get when deploying, based on areas controlled.
     */
    int getUnitsFromAreas(Player player) {
        int count = 0;
        for(Area area : areas){
            if(area.checkArea(player)) {
                count += area.getWorth();
            }
        }
        return count;
    }

    /**
     * Method that checks what spaces a specific player holds and returns the amount of units they should get for
     * those spaces.
     * @param player The player that spaces should be checked for.
     * @return The amount of extra units a player should get when deploying, based on spaces hold.
     */
    int getUnitsForSpacesHold(Player player) {
        int count = 0;
        for(Space space : spaces){
            if(space.getPlayer() == player) {
                count++;
            }
        }
        return 1 + (count / 2);
    }

    /**
     * Method that checks if a player has won the game
     * @return If a player has won the game.
     */
    boolean isWinner() {
        Player winner = spaces.get(0).getPlayer();
        for(Space space : spaces){
            if(!(space.getPlayer() == winner)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method that checks if a player is out of the game. A player is out when they no longer hold any spaces.
     * @param player The player that might be out of the game.
     * @return If the player have lost the game or not.
     */
    boolean isPlayerOut(Player player) {
        for(Space space : spaces){
            if(space.getPlayer() == player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the a space is a neighbour to the current selected space.
     * @param space A space that is not selectedSpace.
     * @return If the space is a neighbour.
     */
    boolean isNeighbours(Space space) {
        if (selectedSpace != null){
            return neighbours[selectedSpace.getId()][space.getId()] == 1;
        }
        return false;
    }

    /**
     * Get amount of neighbours of a space. Mainly used for tests.
     * @param id Which space to get amount of neighbours from.
     * @return Amount of neighbours
     */
    int getNumberOfNeighbours(int id) {
        int count = 0;
        for(int i = 0; i < neighbours[id].length; i++) {
            count += neighbours[id][i];
        }
        return count;
    }

    /**
     * Method that finds a space on the board based on id.
     * @param id id-number of the space
     * @return The space if id exists, else null
     */
    Space findSpace(int id) {
        for (Space space : spaces) {
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
     * @return It then returns the colors
     */
    List<Color> getColorOnAllSpaces() {
        List<Color> colors = new ArrayList<>();
        for (Space space : spaces) {
            colors.add(space.getPlayer().getColor());
        }
        return colors;
    }

    Space getSpace(int id) {
        return spaces.get(id);
    }
}
